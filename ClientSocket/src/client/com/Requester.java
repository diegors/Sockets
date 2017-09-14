package client.com;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Requester {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Scanner inText;

	Requester() {
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

				do {

					System.out.println("server>" + message);
					// prompt for the Enter Project Folder Address
					System.out.print("Enter message to send to Server: ");

					// create a scanner so we can read the command-line input
					inText = new Scanner(System.in);
					// get their input as a String
					String sendMessage = inText.nextLine();
					sendMessage(sendMessage);
					if (sendMessage.equals("bye")) {
						message = (String) in.readObject();

					}

					//

					/*
					 * sendMessage("Hi my server"); message = "bye";
					 * sendMessage(message);
					 */

				} while (!message.equals("bye"));
			} catch (ClassNotFoundException classNot) {
				System.err.println("data received in unknown format");
			}

		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {

				inText.close();

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

	public static void main(String args[]) {
		Requester client = new Requester();
		client.run(args);
	}
}