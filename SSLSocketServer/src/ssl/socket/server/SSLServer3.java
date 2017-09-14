package ssl.socket.server;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Arrays;

import javax.net.ssl.*;

public class SSLServer3 {
	public static void main(String args[]) throws Exception {
		try {

			KeyStore ks = KeyStore.getInstance("JKS");
			InputStream ksIs = new FileInputStream(
					"D:/DevelopmentWork/Repositories/GIT/Others/Demos/Sockets/SSLSocketServer/src/ssl/socket/server/xyzkeystore");
			try {
				ks.load(ksIs, "pass123".toCharArray());
			} finally {
				if (ksIs != null) {
					ksIs.close();
				}
			}

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, "pass123".toCharArray());

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(kmf.getKeyManagers(), trustAllCerts, new SecureRandom());
			SSLServerSocket ssl = (SSLServerSocket) sc.getServerSocketFactory().createServerSocket(2005);
			// Got rid of:
			// ssl.setEnabledCipherSuites(sc.getServerSocketFactory().getSupportedCipherSuites());
			ssl.setEnabledProtocols(new String[] { "TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3" });
			//ssl.setRenegotiationAllowed(true);
			
			//ssl.set

			System.out.println(Arrays.toString(ssl.getEnabledCipherSuites()));

			SSLSocket sslsocket = (SSLSocket) ssl.accept();

			DataInputStream is = new DataInputStream(sslsocket.getInputStream());
			PrintStream os = new PrintStream(sslsocket.getOutputStream());
			while (true) {

				String input = is.readUTF();
				String ketqua = input.toUpperCase();
				os.println(ketqua);
			}

			// ssl = new ServerSocket(DownloadFilelist.PORT);
			// ssl.setSoTimeout(1000000);

			/*
			 * // Create a SSLServersocket SSLServerSocketFactory factory =
			 * (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			 * SSLServerSocket sslserversocket = (SSLServerSocket)
			 * factory.createServerSocket(2005);
			 * 
			 * SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();
			 * 
			 * DataInputStream is = new
			 * DataInputStream(sslsocket.getInputStream()); PrintStream os = new
			 * PrintStream(sslsocket.getOutputStream()); while (true) {
			 * 
			 * String input = is.readUTF(); String ketqua = input.toUpperCase();
			 * os.println(ketqua); }
			 */

		} catch (IOException e) {
			System.out.print(e);
		}
	}
}