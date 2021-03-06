package ro.stefanprisca.distsystems.app3.server;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.server.dataaccess.DBJobAccess;
import ro.stefanprisca.distsystems.app3.server.models.JobCategory;

@RunWith(JUnit4.class)
public class DBJobAccessTest {

	private static DBJobAccess jobAccess;

	@BeforeClass
	public static void setUpClass() {
		jobAccess = new DBJobAccess();
	}

	@Test
	public void testCategoryJobSearch() {
		String category = "Management";
		List<IJob> jobs = jobAccess.getJobs(category);

		assertTrue(jobs.size() > 0);
	}

	@Test
	public void testGetCategories() {
		for (JobCategory cat : jobAccess.getJobCategories()) {
			System.out.println(cat.getName());
		}
		assertTrue(jobAccess.getJobCategories().size() > 0);
	}

}
