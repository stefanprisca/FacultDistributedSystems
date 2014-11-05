package ro.stefanprisca.distsystems.app3.server;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	private static Job testJob;

	private EntityManager em;

	@BeforeClass
	public static void setUpClass() {
		factory = Persistence
				.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
		testJob = generateTestJob();
		initCategories();
	}

	private static void initCategories() {
		EntityManager lem = factory.createEntityManager();
		lem.getTransaction().begin();

		// Query q = lem.createQuery("Select jc from JobCategory jc");
		//
		// if (q.getResultList().size() == 0) {
		// JobCategory cat1 = new JobCategory();
		// cat1.setName("IT");
		// lem.persist(cat1);
		// JobCategory cat2 = new JobCategory();
		// cat2.setName("Economy");
		// lem.persist(cat2);
		// JobCategory cat3 = new JobCategory();
		// cat3.setName("Management");
		// lem.persist(cat3);
		// }

		lem.getTransaction().commit();
		lem.close();
	}

	@Before
	public void setUp() {
		em = factory.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() {

		em.close();
		em.getTransaction().commit();
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
	public void testDBInsertJob() {

		List<JobCategory> cats = em
				.createQuery("select jc from JobCategory jc").getResultList();

		testJob.setCategories(cats);
		em.persist(testJob);

		Query q = em.createQuery("select j from Job j where j.title = :title");
		q.setParameter("title", testJob.getTitle());

		assertNotNull(q.getResultList().size() > 0);
		assertTrue(((Job) q.getResultList().get(0)).getCategories()
				.containsAll(cats));
	}

	@Test
	public void testDBDeleteJob() {
		Query q = em.createQuery("Select j from Job j where j.title = :title");
		q.setParameter("title", testJob.getTitle());

		int firstSize = q.getResultList().size();
		if (firstSize > 0) {
			em.remove(q.getResultList().get(0));

			q = em.createQuery("Select j from Job j where j.title = :title");
			q.setParameter("title", testJob.getTitle());
			assertTrue(firstSize > q.getResultList().size());
		}

	}

	private static Job generateTestJob() {
		Job newJ = null;
		newJ = new Job();
		newJ.setCompName("BigComp");
		newJ.setContactDetails("bigComp@comp.com");
		newJ.setDeadline("tomorrow");
		newJ.setJobSpecification("easy job for whom ever is qualified enough");
		newJ.setTitle("jobfortestingpurpoasesoftheprojectnotforanythingelse");
		newJ.setId((long) 999999999);
		newJ.setTaken(false);
		return newJ;
	}
}
