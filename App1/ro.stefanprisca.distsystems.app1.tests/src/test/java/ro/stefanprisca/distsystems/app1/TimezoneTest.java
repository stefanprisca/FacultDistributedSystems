package test.java.ro.stefanprisca.distsystems.app1;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app1.restful.RestfulClient;

@RunWith(JUnit4.class)
public class TimezoneTest {

	RestfulClient client;

	@Before
	public void setUp() {
		client = new RestfulClient();
	}

	@Test
	public void testTimeZone() {
		String result = client.getTimeZone("39.6034810", "-119.6822510");

		assertTrue(result.contains("America/Los_Angeles"));
	}
}
