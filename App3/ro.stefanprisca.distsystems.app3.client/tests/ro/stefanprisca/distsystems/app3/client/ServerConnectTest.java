package ro.stefanprisca.distsystems.app3.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app3.client.beans.JobAccessBean;
import ro.stefanprisca.distsystems.app3.common.Messages;

@RunWith(JUnit4.class)
public class ServerConnectTest {

	// @Test
	public void testServerConnection() {
		JobAccessBean jab = new JobAccessBean();
		assertTrue(jab.getServerMsg().equals(
				Messages.REMOTE_RESPONSE_CONFIRMATION_MSG));
	}

	// @Test
	public void testJobAccessProvider() {
		JobAccessBean jab = new JobAccessBean();
		assertTrue(jab.getJobs() != null);
	}

	@Test
	public void testLoginServiceConnection() {
		JobAccessBean jab = new JobAccessBean();
		assertTrue(jab.getLoginServiceResponse() != null);
	}
}
