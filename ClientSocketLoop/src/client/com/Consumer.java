package client.com;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Consumer {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Scanner inText;
	String jvmName;

	Consumer() {
		jvmName = ManagementFactory.getRuntimeMXBean().getName();
	}

	void run(String param[]) {
		try {
			// out.close();
			// 1. creating a socket to connect to the server
			requestSocket = new Socket(param[0], Integer.parseInt(param[1]));
			System.out.println("Connected Server to " + param[0] + " in port " + param[1]);
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3: Communicating with the server
			try {
				message = (String) in.readObject();
				System.out.println("server>" + message);

				while (true) {

					String TimeMessage = getTime();

					sendMessage(TimeMessage);
					//System.out.print("client>" + TimeMessage);
					
					TimeUnit.SECONDS.sleep(5);

				}
			} catch (ClassNotFoundException classNot) {
				System.err.println("data received in unknown format");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {

				in.close();
				out.close();
				requestSocket.close();

				System.out.println("****Disconnection****");

			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	String getTime() {
		String timeStamp = jvmName + "---" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		return timeStamp;

	}

	public static void main(String args[]) {
		Consumer client = new Consumer();
		client.run(args);
	}
}