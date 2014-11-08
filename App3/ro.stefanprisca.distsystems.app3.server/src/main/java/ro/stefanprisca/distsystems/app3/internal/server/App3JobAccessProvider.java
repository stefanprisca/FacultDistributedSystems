package ro.stefanprisca.distsystems.app3.internal.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;
import ro.stefanprisca.distsystems.app3.server.dataaccess.DBJobAccess;
import ro.stefanprisca.distsystems.app3.server.models.JobCategory;

public class App3JobAccessProvider extends UnicastRemoteObject implements
		IJobAccessProvider {
	private static final long serialVersionUID = 2152967318134529091L;

	private final DBJobAccess dbJobAccess;

	protected App3JobAccessProvider() throws RemoteException {
		super();
		dbJobAccess = new DBJobAccess();
	}

	public String getServerStartupConfirm() throws RemoteException {
		return Messages.REMOTE_RESPONSE_CONFIRMATION_MSG;
	}

	public List<IJob> getJobs(List<String> jobCategories, Date startDate,
			Date endDate) throws RemoteException {
		final List<IJob> jobs = new ArrayList<IJob>();

		List<IJob> dbJobs;

		for (String jobCategory : jobCategories) {
			dbJobs = dbJobAccess.getJobs(jobCategory);
			for (IJob iJob : dbJobs) {
				jobs.add(new JobRMIObject(iJob));
			}
		}

		return jobs;
	}

	public List<IJob> getJobs() throws RemoteException {
		final List<IJob> jobs = new ArrayList<IJob>();

		List<IJob> dbJobs;

		dbJobs = dbJobAccess.getJobs();
		for (IJob iJob : dbJobs) {
			jobs.add(new JobRMIObject(iJob));
		}
		return jobs;
	}

	public List<String> getJobCategories() throws RemoteException {
		List<String> jobCategories = new ArrayList<String>();
		for (JobCategory jc : dbJobAccess.getJobCategories()) {
			jobCategories.add(jc.getName());
		}
		return jobCategories;
	}
}
