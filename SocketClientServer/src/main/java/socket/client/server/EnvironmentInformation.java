package socket.client.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EnvironmentInformation {

	private InetAddress ip;
	private String hostname;
	private String hostaddress;

	public EnvironmentInformation() {
		try {
			ip = InetAddress.getLocalHost();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

	}

	public String EnvironmentGetHostAddress() {

		hostaddress = ip.getHostAddress();

		System.out.println("Your current IP address : " + hostaddress);

		return hostaddress;

	}

	public String EnvironmentGetHostname() {

		hostname = ip.getHostName();

		System.out.println("Your current Hostname : " + hostname);

		return hostname;

	}
}
