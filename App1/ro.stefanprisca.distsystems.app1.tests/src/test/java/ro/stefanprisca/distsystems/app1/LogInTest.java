package test.java.ro.stefanprisca.distsystems.app1;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app1.beans.ApplicationUserManager;
import ro.stefanprisca.distsystems.app1.beans.NavigationBean;
import ro.stefanprisca.distsystems.app1.models.ApplicationUser;

@RunWith(JUnit4.class)
public class LogInTest {
	private static EntityManagerFactory factory;

	private ApplicationUserManager usermanager;
	private NavigationBean navbean;

	@Before
	public void setup() {
		usermanager = new ApplicationUserManager();
		navbean = new NavigationBean();
		usermanager.setNavigationBean(navbean);

		factory = Persistence
				.createEntityManagerFactory(ApplicationUserManager.PERSISTENCE_UNIT_NAME);
		initDB();
	}

	private void initDB() {
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select u from ApplicationUser u");
		// Persons should be empty

		// do we have entries?
		boolean createNewEntries = (q.getResultList().size() < 2);

		// No, so lets create new entries
		if (createNewEntries) {

			ApplicationUser user = new ApplicationUser();
			user.setBirthDate("12");
			user.setHomeAddress("asda");
			user.setName("Stefan");
			user.setLatitude("0");
			user.setLongitude("0");
			user.setType(ApplicationUserManager.REGULAR_TYPE);
			user.setLoginName("user");
			user.setPassword("user");

			em.persist(user);

			ApplicationUser admin = new ApplicationUser();
			user.setBirthDate("12");
			user.setHomeAddress("asda");
			user.setName("Stefan");
			user.setLatitude("0");
			user.setLongitude("0");
			user.setType(ApplicationUserManager.ADMINISTRATOR_TYPE);
			user.setLoginName("admin");
			user.setPassword("admin");

			em.persist(admin);
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

	}

	@Test(expected = NullPointerException.class)
	public void testLogIn() {
		usermanager.setLoginReqID("thisisnotauser");
		usermanager.setLoginReqPW("nouserpw");
		usermanager.doLogin();
	}

	@Test
	public void testValidLogIn() {
		usermanager.setLoginReqID("user");
		usermanager.setLoginReqPW("user");
		assertTrue(usermanager.doLogin().equals(navbean.toRegularPage()));
	}

	@Test
	public void testValidAdminLogIn() {
		usermanager.setLoginReqID("admin");
		usermanager.setLoginReqPW("admin");
		assertTrue(usermanager.doLogin().equals(navbean.toAdminPage()));
	}

	@Test
	public void testUserLogout() {
		usermanager.setLoginReqID("admin");
		usermanager.setLoginReqPW("admin");
		usermanager.doLogin();
		String response = usermanager.doLogout();
		assertTrue(response.equals(navbean.toLogIn()));
	}
}
