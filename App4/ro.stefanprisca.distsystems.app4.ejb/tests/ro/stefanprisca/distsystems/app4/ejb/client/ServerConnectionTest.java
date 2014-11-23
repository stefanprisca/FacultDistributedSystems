package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.client.utils.ClientUtility;
import ro.stefanprisca.distsystems.app4.ejb.common.Constants;
import ro.stefanprisca.distsystems.app4.ejb.common.Messages;
import ro.stefanprisca.distsystems.utils.login.LoginUtilsServiceFactory;

@RunWith(JUnit4.class)
public class ServerConnectionTest {

	@Test
	public void testEjbConnection() {
		BookProviderRemote provider = ClientUtility.doLookup();
		assertTrue(provider.getServerConfirmation().equals(
				Messages.EJB_CONNECTION_TEST));
	}

	@Test
	public void testTomcatWebServiceConnection() {
		String conf = LoginUtilsServiceFactory.provideLoginUtilsServiceAccess(
				Constants.DB_CONNECTION_DBNAME).getConfString();

		assertTrue(conf
				.equals(ro.stefanprisca.distsystems.utils.login.Constants.CONNECTION_CONF_STRING));
	}
}
