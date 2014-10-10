package ro.stefanprisca.distsystems.app1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app1.models.ApplicationUser;

@RunWith(JUnit4.class)
public class DBConnectionTest {

	private static final String PERSISTENCE_UNIT_NAME = "applicationUsers";
	private static EntityManagerFactory factory;

	@Test
	public void testDB() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select t from ApplicationUser t");
		List<ApplicationUser> userList = q.getResultList();
		for (ApplicationUser todo : userList) {
			System.out.println(todo);
		}
		System.out.println("Size: " + userList.size());

		em.getTransaction().begin();
		ApplicationUser user = new ApplicationUser();
		user.setName("Stefan");
		user.setBirthDate("20.20.20");
		user.setHomeAddress("vitei");
		user.setLatitude("10");
		user.setLongitude("20");
		em.persist(user);
		em.getTransaction().commit();

		em.close();
	}
}
