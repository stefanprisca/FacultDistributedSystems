package ro.stefanprisca.distsystems.app3.internal.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.JobRMIObject;
import ro.stefanprisca.distsystems.app3.common.Messages;
import ro.stefanprisca.distsystems.app3.server.dataaccess.DBJobAccess;
import ro.stefanprisca.distsystems.app3.server.models.Job;
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

		List<IJob> jobs = getJobs();

		if (jobCategories != null && !jobCategories.isEmpty()) {
			for (String jobCategory : jobCategories) {
				jobs.removeIf(j -> !getNoExceptionCategoriesWrapper(j)
						.contains(jobCategory));
			}
		}
		if (startDate != null) {
			jobs.removeIf(j -> getNoExceptionDateWrapper(j).before(startDate));
		}
		if (endDate != null) {
			jobs.removeIf(j -> getNoExceptionDateWrapper(j).after(endDate));
		}
		return jobs;
	}

	private Date getNoExceptionDateWrapper(IJob j) {
		try {
			return j.getDeadline();
		} catch (RemoteException e) {
			return null;
		}
	}

	private String getNoExceptionCategoriesWrapper(IJob j) {
		try {
			return j.getDisplayCategories();
		} catch (RemoteException e) {
			return "";
		}
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

	public void takeJobAction(Long jobId) throws RemoteException {
		Job job = dbJobAccess.getJobById(jobId);
		dbJobAccess.startUpdate();
		job.setTaken(true);
		dbJobAccess.endUpdate();
	}

	@Override
	public void addNewJob(IJob newJob, List<String> categories)
			throws RemoteException {
		Job job = new Job();
		setupJob(job, newJob, categories);

		dbJobAccess.startUpdate();
		dbJobAccess.addJob(job);
		dbJobAccess.endUpdate();
	}

	private void setupJob(Job job, IJob newJob, List<String> categories)
			throws RemoteException {
		job.setCompName(newJob.getCompName());
		job.setContactDetails(newJob.getContactDetails());
		job.setDeadline(newJob.getDeadline() == null ? new Date(System
				.currentTimeMillis()) : newJob.getDeadline());
		job.setJobSpecification(newJob.getJobSpecification());
		job.setTitle(newJob.getTitle());
		job.setTaken(false);

		List<JobCategory> jobCats = new ArrayList<JobCategory>();
		List<JobCategory> dbCats = dbJobAccess.getJobCategories();

		for (String cat : categories) {

			dbCats.forEach(jc -> {
				if (jc.getName().equals(cat))
					jobCats.add(jc);
			});

		}

		job.setCategories(jobCats);
	}
}
