package ssl.socket.client;

import java.net.*;
import java.security.cert.Certificate;
import java.io.*;
import javax.net.ssl.*;

public class SSLClient {

	static SSLSocket sslsocket;

	public static void main(String args[]) {
		try {

			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			sslsocket= (SSLSocket) factory.createSocket("127.0.0.1",2005);

			//sslsocket = (SSLSocket) factory.createSocket("10.64.50.182", 2005);
			
		
			sslsocket.setEnabledCipherSuites(sslsocket.getSupportedCipherSuites());
			
			//sslsocket.startHandshake();
			
			printSocketInfo();

			DataOutputStream os = new DataOutputStream(sslsocket.getOutputStream());
			DataInputStream is = new DataInputStream(sslsocket.getInputStream());

			String str = "helloworld";
			os.writeBytes(str);

			String responseStr;
			if ((responseStr = is.readUTF()) != null) {
				System.out.println(responseStr);
			}

			os.close();
			is.close();
			sslsocket.close();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printSocketInfo() {
		System.out.println("Socket class: " + sslsocket.getClass());
		System.out.println("   Remote address = " + sslsocket.getInetAddress().toString());
		System.out.println("   Remote port = " + sslsocket.getPort());
		System.out.println("   Local socket address = " + sslsocket.getLocalSocketAddress().toString());
		System.out.println("   Local address = " + sslsocket.getLocalAddress().toString());
		System.out.println("   Local port = " + sslsocket.getLocalPort());
		System.out.println("   Need client authentication = " + sslsocket.getNeedClientAuth());
		SSLSession ss = sslsocket.getSession();
		System.out.println("   Cipher suite = " + ss.getCipherSuite());
		System.out.println("   Protocol = " + ss.getProtocol());
		System.out.println();
		
		/*
		Certificate[] serverCerts = null;

		try {
			serverCerts = sslsocket.getSession().getPeerCertificates();
		} catch (SSLPeerUnverifiedException ex) {
			ex.printStackTrace();
		}

		System.out.println("Retreived Server's Certificate Chain");

		System.out.println(serverCerts.length + "Certifcates Found\n\n\n");
		for (int i = 0; i < serverCerts.length; i++) {
			Certificate myCert = serverCerts[i];
			System.out.println("====Certificate:" + (i + 1) + "====");
			System.out.println("-Public Key-\n" + myCert.getPublicKey());
			System.out.println("-Certificate Type-\n " + myCert.getType());

			System.out.println();
		}
		*/
	}
}