package ro.stefanprisca.distsystems.app3.client.beans;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app3.common.IJob;

@SessionScoped
@ManagedBean(name = "jobBean", eager = true)
public class JobBean implements IJob {

	private IJob job;

	public JobBean() {
	};

	public JobBean(IJob job) {
		this.job = job;
	}

	public String getDeadline() throws RemoteException {
		return job.getDeadline();
	}

	public String getContactDetails() throws RemoteException {
		return job.getContactDetails();
	}

	public String getJobSpecification() throws RemoteException {
		// TODO Auto-generated method stub
		return job.getJobSpecification();
	}

	public String getTitle() throws RemoteException {
		// TODO Auto-generated method stub
		return job.getTitle();
	}

	public String getCompName() throws RemoteException {
		// TODO Auto-generated method stub
		return job.getCompName();
	}

	public String getDisplayCategories() throws RemoteException {
		// TODO Auto-generated method stub
		return job.getDisplayCategories();
	}

	public Boolean getTaken() throws RemoteException {
		// TODO Auto-generated method stub
		return job.getTaken();
	}

	public void setUnderlyingJob(IJob job) {
		this.job = job;
	}

}
