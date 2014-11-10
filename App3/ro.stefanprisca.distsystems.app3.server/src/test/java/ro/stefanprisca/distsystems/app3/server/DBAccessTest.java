package ro.stefanprisca.distsystems.app3.server;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app3.internal.server.Constants;
import ro.stefanprisca.distsystems.app3.server.models.Job;
import ro.stefanprisca.distsystems.app3.server.models.JobCategory;

@RunWith(JUnit4.class)
public class DBAccessTest {

	private static EntityManagerFactory factory;

	private EntityManager em;

	@BeforeClass
	public static void setUpClass() {
		factory = Persistence
				.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
		// initDatabase();
	}

	private static void initDatabase() throws RemoteException {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("Select jc from JobCategory jc");

		if (q.getResultList().size() == 0) {
			JobCategory cat1 = new JobCategory();
			cat1.setName("IT");
			em.persist(cat1);
			JobCategory cat2 = new JobCategory();
			cat2.setName("Economy");
			em.persist(cat2);
			JobCategory cat3 = new JobCategory();
			cat3.setName("Management");
			em.persist(cat3);
		}
		List<JobCategory> cats = em
				.createQuery("select jc from JobCategory jc").getResultList();

		em.persist(generateTestJob("Test Job 2",
				new Date(System.currentTimeMillis()), cats.subList(0, 1)));

		em.persist(generateTestJob("Test Job 3",
				new Date(System.currentTimeMillis()), cats.subList(1, 2)));

		em.persist(generateTestJob("Test Job 4",
				new Date(System.currentTimeMillis()), cats.subList(2, 3)));

		em.getTransaction().commit();
		em.close();
	}

	@Before
	public void setUp() {
		em = factory.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() {

		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDBReadJobs() {

		// read the existing entries and write to console
		Query q = em.createQuery("select j from Job j");
		List<Job> jobList = q.getResultList();
		for (Job job : jobList) {
			System.out.println(job);
		}

		assertTrue(jobList.size() >= 0);

	}

	@Test
	public void testDBInsertJob() throws RemoteException {

		List<JobCategory> cats = em
				.createQuery("select jc from JobCategory jc").getResultList();

		Job testJob1 = generateTestJob("Test Job 1",
				new Date(System.currentTimeMillis()), cats);
		em.persist(testJob1);

		Query q = em.createQuery("select j from Job j where j.title = :title");
		q.setParameter("title", testJob1.getTitle());

		assertNotNull(q.getResultList().size() > 0);

	}

	@Test
	public void testDBDeleteJob() {
		Query q = em.createQuery("Select j from Job j where j.title = :title");
		q.setParameter("title", "Test Job 1");

		int firstSize = q.getResultList().size();
		if (firstSize > 0) {
			em.remove(q.getResultList().get(0));

			q = em.createQuery("Select j from Job j where j.title = :title");
			q.setParameter("title", "Test Job 1");
			assertTrue(firstSize > q.getResultList().size());
		}

	}

	private static Job generateTestJob(String title, Date deadline,
			List<JobCategory> categories) throws RemoteException {
		Job newJ = null;
		newJ = new Job();
		newJ.setCompName("Test Com");
		newJ.setContactDetails("bigComp@comp.com");
		newJ.setDeadline(deadline);
		newJ.setJobSpecification("easy job for whom ever is qualified enough");
		newJ.setTitle(title);
		newJ.setId((long) 999999999);
		newJ.setTaken(false);
		newJ.setCategories(categories);
		return newJ;
	}
}
