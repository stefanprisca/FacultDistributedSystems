package ro.stefanprisca.distsystems.app3.server.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ro.stefanprisca.distsystems.app3.common.IJob;

@Entity
public class Job implements IJob {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String compName;
	@Temporal(TemporalType.DATE)
	private Date deadline;
	private String contactDetails;
	private String jobSpecification;
	private Boolean taken;
	private List<JobCategory> categories = new ArrayList<JobCategory>();

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getJobSpecification() {
		return jobSpecification;
	}

	public void setJobSpecification(String jobSpecification) {
		this.jobSpecification = jobSpecification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	@OneToMany
	public List<JobCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<JobCategory> categories) {
		this.categories = categories;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public String getDisplayCategories() {
		String cats = "";
		for (JobCategory cat : categories) {
			cats += cat.getName() + "; ";
		}
		return cats;
	}

}
