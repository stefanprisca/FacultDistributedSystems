package ro.stefanprisca.distsystems.app3.client;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app3.client.beans.JobAccessBean;
import ro.stefanprisca.distsystems.app3.client.beans.JobBean;
import ro.stefanprisca.distsystems.app3.common.Messages;

@RunWith(JUnit4.class)
public class RMIServerConnectTest {

	private JobAccessBean jab;

	@Before
	public void setUp() {
		jab = new JobAccessBean();
	}

	@Test
	public void testServerConnection() {
		assertTrue(jab.getServerMsg().equals(
				Messages.REMOTE_RESPONSE_CONFIRMATION_MSG));
	}

	@Test
	public void testJobAccessProvider() {
		assertTrue(jab.getJobs() != null);
	}

	@Test
	public void testGetJobCategories() {
		for (String cat : jab.getJobCategories()) {
			System.out.println(cat);
		}
		assertTrue(jab.getJobCategories().size() > 0);
	}

	@Test
	public void testJobCategorySearch() throws RemoteException {
		List<String> selectedCats = new ArrayList<String>();
		selectedCats.add("IT");
		selectedCats.add("Management");

		jab.setSelectedCategories(selectedCats);
		String filterResult = jab.filterJobs();

		assertNull(filterResult);

		assertTrue(jab.getJobs().size() > 0);

		for (JobBean job : jab.getJobs()) {
			System.out.println(job.getDisplayCategories());
			assertTrue(job.getDisplayCategories().contains("IT")
					|| job.getDisplayCategories().contains("Management"));
		}
	}
}
