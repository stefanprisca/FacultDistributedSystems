package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.client.beans.ApplicationUserManagerBean;
import ro.stefanprisca.distsystems.app4.ejb.client.beans.NavigationBean;
import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@RunWith(JUnit4.class)
public class LoginWebServiceTest {
	private ApplicationUserManagerBean usermanager;
	private NavigationBean navbean;

	@Before
	public void setup() {
		usermanager = new ApplicationUserManagerBean();
		navbean = new NavigationBean();
		usermanager.setNavigationBean(navbean);
	}

	@Test
	public void testLogIn() {
		usermanager.setLoginReqID("thisisnotauser");
		usermanager.setLoginReqPW("nouserpw");
		assertTrue(usermanager.doLogin().equals(navbean.toLogIn()));
	}

	@Test
	public void testValidLogIn() {
		usermanager.setLoginReqID("user");
		usermanager.setLoginReqPW("user");
		assertTrue(usermanager.doLogin().equals(navbean.toRegularPage()));
	}

	@Test
	public void testRegisterAction() {
		ApplicationUser u = usermanager.getUser();
		u.setLoginID("NewTestUserAdmin");
		u.setLoginPW("NewTestUserAdmin");
		usermanager.setNewAdmin(true);
		assertTrue(usermanager.addUser().equals(navbean.toAdminPage()));
	}

	// @Test
	public void testValidAdminLogIn() {
		usermanager.setLoginReqID("admin");
		usermanager.setLoginReqPW("admin");
		assertTrue(usermanager.doLogin().equals(navbean.toAdminPage()));
	}

	// @Test
	public void testUserLogout() {
		usermanager.setLoginReqID("admin");
		usermanager.setLoginReqPW("admin");
		usermanager.doLogin();
		String response = usermanager.doLogout();
		assertTrue(response.equals(navbean.toLogIn()));
	}
}
