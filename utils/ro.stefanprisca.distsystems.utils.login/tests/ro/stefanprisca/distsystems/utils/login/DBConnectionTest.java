package ro.stefanprisca.distsystems.utils.login;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@RunWith(JUnit4.class)
public class DBConnectionTest {

	private static final String PERSISTENCE_UNIT_NAME = "applicationUsers";
	private static EntityManagerFactory factory;

	@BeforeClass
	public static void setUpClass() {
		LoginUtilsServiceFactory.setDbName("ds_assign4");

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
				LoginUtilsServiceFactory.provideDefaultConnectionProperties());
	}

	@Before
	public void setUp() throws Exception {
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select u from ApplicationUser u");
		// Persons should be empty

		// do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);
			ApplicationUser user = new ApplicationUser();
			user.setBirthDate("12");
			user.setHomeAddress("asda");
			user.setName("Stefan");
			user.setEmail("stefan.prisca@gmail.com");
			user.setType(Constants.REGULAR_TYPE);
			user.setLoginName("user");
			user.setPassword("user");

			em.persist(user);

		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

	}

	@Test
	public void checkAvailableUsers() {

		// now lets check the database and see if the created entries are there
		// create a fresh, new EntityManager
		EntityManager em = factory.createEntityManager();

		// Perform a simple query for all the Message entities
		Query q = em.createQuery("select u from ApplicationUser u");

		assertTrue(q.getResultList().size() > 0);

		em.close();
	}

	// @Test(expected = javax.persistence.NoResultException.class)
	// public void deletePerson() {
	// EntityManager em = factory.createEntityManager();
	// // Begin a new local transaction so that we can persist a new entity
	// em.getTransaction().begin();
	// Query q = em
	// .createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName");
	// q.setParameter("firstName", "Jim_1");
	// q.setParameter("lastName", "Knopf_!");
	// Person user = (Person) q.getSingleResult();
	// em.remove(user);
	// em.getTransaction().commit();
	// Person person = (Person) q.getSingleResult();
	// // Begin a new local transaction so that we can persist a new entity
	//
	// em.close();
	// }
}