package socket.client.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnvironmentInformationTest {

	EnvironmentInformation enviromentValues = new EnvironmentInformation();

	@Test
	@DisplayName("Test IP and Hostname values ")
	void TestIPandHostname() {
		String hostAddress = enviromentValues.EnvironmentGetHostAddress();
		assertNotNull(hostAddress, "Host Address Fail");

		String hostName = enviromentValues.EnvironmentGetHostname();
		assertNotNull(hostName, "Host Name Fail");

	}

}
