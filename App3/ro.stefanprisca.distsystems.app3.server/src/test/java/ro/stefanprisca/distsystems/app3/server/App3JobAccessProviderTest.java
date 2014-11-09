package ro.stefanprisca.distsystems.app3.server;

import static org.junit.Assert.assertTrue;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app3.common.Constants;
import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;
import ro.stefanprisca.distsystems.app3.server.dataaccess.DBJobAccess;

@RunWith(JUnit4.class)
public class App3JobAccessProviderTest {

	private static IJobAccessProvider jaProvider;
	private static DBJobAccess jobDbAccess;

	@BeforeClass
	public static void setupClass() {
		try {
			jaProvider = (IJobAccessProvider) Naming
					.lookup(Constants.JOB_ACCESS_PROVIDER_REGPOINT);
		} catch (Exception e) {
			System.err.println(Messages.JOBPROVIDER_RETRIEVAL_ERROR);
		}
		jobDbAccess = new DBJobAccess();
	}

	@Test
	public void testJobTakeAction() throws RemoteException {
		List<IJob> jobs = jaProvider.getJobs();
		Long id = jobs.get(0).getId();
		System.out.println(id);
		jaProvider.takeJobAction(id);

		assertTrue(jobDbAccess.getJobById(id).getTaken());
	}
}
