package ro.stefanprisca.distsystems.app3.server;

import static org.junit.Assert.*;

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

import ro.stefanprisca.distsystems.app3.server.core.Constants;
import ro.stefanprisca.distsystems.app3.server.models.Job;

@RunWith(JUnit4.class)
public class DBAccessTest {

	private static EntityManagerFactory factory;

	private EntityManager em;

	@BeforeClass
	public static void setUpClass() {
		factory = Persistence
				.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);

	}

	@Before
	public void setUp() {
		em = factory.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() {

		em.close();
		em.getTransaction().rollback();
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
	public void testDBInsertDeleteJob() {
		// TODO
		Job newJ = generateTestJob();
		em.persist(newJ);

		Query q = em.createQuery("select j from Job j where j.title = :title");
		q.setParameter("title", newJ.getTitle());

		assertNotNull(q.getSingleResult());
		em.remove(newJ);
		assertTrue(q.getResultList().size() == 0);
	}
	
	private static Job generateTestJob() {
		Job newJ = new Job();
		newJ.setCompName("BigComp");
		newJ.setContactDetails("bigComp@comp.com");
		newJ.setDeadline("tomorrow");
		newJ.setJobSpecification("easy job for whom ever is qualified enough");
		newJ.setTitle("jobfortestingpurpoasesoftheprojectnotforanythingelse");
		newJ.setId((long) 999999999);
		return newJ;
	}
}
