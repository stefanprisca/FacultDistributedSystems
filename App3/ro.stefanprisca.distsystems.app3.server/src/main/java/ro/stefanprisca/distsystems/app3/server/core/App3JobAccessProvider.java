package ro.stefanprisca.distsystems.app3.server.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;
import ro.stefanprisca.distsystems.app3.server.dataaccess.DBJobAccess;

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

	public List<IJob> getJobs(String... jobCategories) throws RemoteException {
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
}
