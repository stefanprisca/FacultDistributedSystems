package ro.stefanprisca.distsystems.app3.internal.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import ro.stefanprisca.distsystems.app3.common.IJob;

public class JobRMIObject extends UnicastRemoteObject implements IJob {

	private static final long serialVersionUID = -5427085641948815986L;

	private Long id;
	private String title;
	private String compName;
	private Date deadline;
	private String contactDetails;
	private String jobSpecification;
	private Boolean taken;
	private String categories;

	public JobRMIObject() throws RemoteException {
	};

	public JobRMIObject(IJob job) throws RemoteException {
		this.id = job.getId();
		this.title = job.getTitle();
		this.compName = job.getCompName();
		this.deadline = job.getDeadline();
		this.contactDetails = job.getContactDetails();
		this.jobSpecification = job.getJobSpecification();
		this.taken = job.getTaken();
		this.categories = job.getDisplayCategories();
	}

	public String getTitle() {
		return title;
	}

	public String getCompName() {
		return compName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public String getJobSpecification() {
		return jobSpecification;
	}

	public Boolean getTaken() {
		return taken;
	}

	public String getCategories() {
		return categories;
	}

	public String getDisplayCategories() {
		return categories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long jobId) {
		this.id = jobId;
	}
}
