package ro.stefanprisca.distsystems.app3.client.beans;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app3.common.Constants;
import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;

@SessionScoped
@ManagedBean(name = "jobAccess", eager = true)
public class JobAccessBean {

	private final IJobAccessProvider jobAccessProvider;
	private List<JobBean> jobs;

	public JobAccessBean() {
		IJobAccessProvider partProvider;
		try {
			partProvider = (IJobAccessProvider) Naming
					.lookup(Constants.JOB_ACCESS_PROVIDER_REGPOINT);
		} catch (Exception e) {
			partProvider = null;
			System.err.println(Messages.JOBPROVIDER_RETRIEVAL_ERROR);
		}

		jobAccessProvider = partProvider;
		setJobs();
	}

	private String getServerMessage() {
		try {
			return jobAccessProvider == null ? Messages.JOBPROVIDER_RETRIEVAL_ERROR
					: jobAccessProvider.getServerStartupConfirm();
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
	}

	public String getServerMsg() {
		return getServerMessage();
	}

	public String getJobsByCategory(String... categories) {
		try {
			List<IJob> jobs = jobAccessProvider.getJobs(categories);
			return jobs.toString();
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
	}

	public List<JobBean> getJobs() {
		return jobs;
	}

	public void setJobs() {
		this.jobs = new ArrayList<JobBean>();
		try {
			List<IJob> externalJobs = jobAccessProvider.getJobs();
			for (IJob job : externalJobs) {
				JobBean jb = new JobBean(job);
				this.jobs.add(jb);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
