package ro.stefanprisca.distsystems.app3.client.beans;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app3.common.Constants;
import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.JobRMIObject;
import ro.stefanprisca.distsystems.app3.common.Messages;

@SessionScoped
@ManagedBean(name = "jobAccess", eager = true)
public class JobAccessBean {
	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	private final IJobAccessProvider jobAccessProvider;
	private List<JobBean> jobs;
	private List<String> selectedCategories;
	private List<String> jobCategories;
	private Date filterStartDate;
	private Date filterEndDate;

	private List<String> newJCategories;
	private JobBean newJob = new JobBean();

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

	public String getServerMsg() {
		try {
			return jobAccessProvider == null ? Messages.JOBPROVIDER_RETRIEVAL_ERROR
					: jobAccessProvider.getServerStartupConfirm();
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
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

	public void setJobs(List<IJob> externalJobs) {
		this.jobs = new ArrayList<JobBean>();
		for (IJob job : externalJobs) {
			JobBean jb = new JobBean(job);
			this.jobs.add(jb);
		}
	}

	public List<JobBean> getJobs() {
		return jobs;
	}

	public String takeJob(Long jobId) {
		try {
			jobAccessProvider.takeJobAction(jobId);
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}

		setJobs();

		return null;
	}

	public String postJob() {
		try {
			jobAccessProvider.addNewJob(new JobRMIObject(newJob),
					newJCategories);
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
		setJobs();
		return navigationBean.toRegularPage();
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public String filterJobs() {
		try {
			List<IJob> jobs = jobAccessProvider.getJobs(selectedCategories,
					filterStartDate, filterEndDate);
			setJobs(jobs);
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
		return null;
	}

	public String resetFilters() {
		setJobs();
		return null;
	}

	public List<String> getJobCategories() {
		this.jobCategories = new ArrayList<String>();
		try {
			this.jobCategories.addAll(jobAccessProvider.getJobCategories());
		} catch (RemoteException e) {
		}
		return this.jobCategories;
	}

	public void setJobCategories(List<String> jobCategories) {
		this.jobCategories = jobCategories;
	}

	public List<String> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(List<String> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public Date getFilterStartDate() {
		return filterStartDate;
	}

	public void setFilterStartDate(Date filterStartDate) {
		this.filterStartDate = filterStartDate;
	}

	public Date getFilterEndDate() {
		return filterEndDate;
	}

	public void setFilterEndDate(Date filterEndDate) {
		this.filterEndDate = filterEndDate;
	}

	public JobBean getNewJob() {
		return newJob;
	}

	public void setNewJob(JobBean newJob) {
		this.newJob = newJob;
	}

	public List<String> getNewJCategories() {
		return newJCategories;
	}

	public void setNewJCategories(List<String> newJCategories) {
		this.newJCategories = newJCategories;
	}

}
