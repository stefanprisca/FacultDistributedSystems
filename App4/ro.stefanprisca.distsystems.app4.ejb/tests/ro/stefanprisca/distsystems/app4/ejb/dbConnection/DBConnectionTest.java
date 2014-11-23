package ro.stefanprisca.distsystems.app4.ejb.dbConnection;

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

import ro.stefanprisca.distsystems.app4.ejb.common.Constants;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

@RunWith(JUnit4.class)
public class DBConnectionTest {
	private static EntityManagerFactory factory;

	@BeforeClass
	public static void setUpClass() {
		factory = Persistence
				.createEntityManagerFactory(Constants.DB_PERSIST_UNIT_NAME);
	}

	@Before
	public void setUp() throws Exception {
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q1 = em.createQuery("select p from Publisher p");
		boolean createNewEntries = (q1.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			assertTrue(q1.getResultList().size() == 0);
			Publisher p = new Publisher();
			p.setName("Stefan");

			em.persist(p);
		}

		Query q2 = em.createQuery("select b from Book b");
		createNewEntries = (q2.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			assertTrue(q2.getResultList().size() == 0);
			Book b = new Book();
			b.setName("Stefan's ds assignment");
			b.setAuthor("Stefan");
			b.setPub((Publisher) q1.getResultList().get(0));

			em.persist(b);
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

		EntityManager em = factory.createEntityManager();

		// Perform a simple query for all the Message entities
		Query q = em.createQuery("select b from Book b");

		assertTrue(q.getResultList().size() > 0);

		em.close();
	}

}