package ro.stefanprisca.distsystems.app3.client.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app3.common.Constants;
import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;

@SessionScoped
@ManagedBean(name = "jobAccess", eager = true)
public class JobAccessBean {

	private final IJobAccessProvider jobAccessProvider;
	private String serverMsg;

	public JobAccessBean(){
		IJobAccessProvider partProvider;
		try {
			partProvider = (IJobAccessProvider) Naming
					.lookup(Constants.JOB_ACCESS_PROVIDER_REGPOINT);
		} catch (Exception e) {
			partProvider = null;
			System.err.println(Messages.JOBPROVIDER_RETRIEVAL_ERROR);
		}

		jobAccessProvider = partProvider;
		serverMsg = getServerMessage();
	}
	
	private String getServerMessage(){
		try {
			return jobAccessProvider == null ? Messages.JOBPROVIDER_RETRIEVAL_ERROR : jobAccessProvider.getServerStartupConfirm();
		} catch (RemoteException e) {
			return Messages.JOBPROVIDER_METHOD_CALL_ERROR;
		}
	}

	public String getServerMsg() {
		return serverMsg;
	}

	public void setServerMsg(String serverMsg) {
		this.serverMsg = serverMsg;
	}
}
