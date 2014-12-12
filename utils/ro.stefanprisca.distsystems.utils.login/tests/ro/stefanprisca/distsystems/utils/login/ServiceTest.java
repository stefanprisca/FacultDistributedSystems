package ro.stefanprisca.distsystems.utils.login;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@RunWith(JUnit4.class)
public class ServiceTest {

	private ILoginUtils client;

	@Before
	public void setUp() {
		client = LoginUtilsServiceFactory
				.provideLoginUtilsServiceAccess();
	}

	@Test
	public void testConnections() {

		assertTrue(client.getConfString().equals(
				Constants.CONNECTION_CONF_STRING));

		List<ApplicationUser> users = client.getUsers();
		assertNotNull(users);

		System.out.println("Showing app users");
		for (ApplicationUser u : users) {
			System.out.println(u);
		}

	}

	@Test
	public void testUserModification() {

		List<ApplicationUser> users = client.getUsers();

		String newName = "ModifiedUserName";
		users.get(0).setName(newName);
		client.saveUsers(users);

		assertTrue(client.getUsers().get(0).getName().equals(newName));

	}

	@Test
	public void testLoginFunction() {
		String loginId = "admin";
		String loginPw = "admin";

		// assertTrue(client.doLogin(loginId, loginPw).equals(
		// Constants.LOGIN_ADMINISTRATOR));

		loginId = "user";
		loginPw = "user";

		assertTrue(client.doLogin(loginId, loginPw).equals(
				Constants.LOGIN_REGULAR_USER));

		loginId = loginPw = "nonexistantwierduser";

		assertTrue(client.doLogin(loginId, loginPw).equals(
				Constants.LOGIN_ERROR));
	}
}
