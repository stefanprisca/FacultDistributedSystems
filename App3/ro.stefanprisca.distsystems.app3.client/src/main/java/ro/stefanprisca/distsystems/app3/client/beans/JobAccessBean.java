package ro.stefanprisca.distsystems.app3.client.beans;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import ro.stefanprisca.distsystems.app3.common.Constants;
import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;
import ro.stefanprisca.distsystems.utils.login.ILoginUtils;

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

	public String getLoginServiceResponse() {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ILoginUtils.class);
		factory.setAddress(ro.stefanprisca.distsystems.utils.login.Constants.SERVICE_ADDRESS);
		ILoginUtils client = (ILoginUtils) factory.create();

		return client.getConfString();
	}
}
