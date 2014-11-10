package ro.stefanprisca.distsystems.app3.client.beans;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import ro.stefanprisca.distsystems.app3.common.IJob;

@ManagedBean(name = "jobBean", eager = true)
public class JobBean extends UnicastRemoteObject implements IJob {
	private static final long serialVersionUID = -8610000541722396472L;

	private Long id;
	private String title;
	private String compName;
	private Date deadline;
	private String contactDetails;
	private String jobSpecification;
	private Boolean taken;
	private String categories;

	public JobBean() throws RemoteException {
	};

	public JobBean(IJob job) throws RemoteException {
		try {
			this.id = job.getId();
			this.title = job.getTitle();
			this.compName = job.getCompName();
			this.deadline = job.getDeadline();
			this.contactDetails = job.getContactDetails();
			this.jobSpecification = job.getJobSpecification();
			this.taken = job.getTaken();
			this.categories = job.getDisplayCategories();
		} catch (RemoteException e) {
		}
	}

	public Long getId() throws RemoteException {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() throws RemoteException {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompName() throws RemoteException {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Date getDeadline() throws RemoteException {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getContactDetails() throws RemoteException {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getJobSpecification() throws RemoteException {
		return jobSpecification;
	}

	public void setJobSpecification(String jobSpecification) {
		this.jobSpecification = jobSpecification;
	}

	public Boolean getTaken() throws RemoteException {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public String getCategories() throws RemoteException {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getDisplayCategories() throws RemoteException {
		return getCategories();
	}

}
