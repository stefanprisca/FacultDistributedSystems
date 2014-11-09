package ro.stefanprisca.distsystems.app3.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IJobAccessProvider extends Remote {

	public String getServerStartupConfirm() throws RemoteException;

	public List<IJob> getJobs() throws RemoteException;

	public List<IJob> getJobs(List<String> jobCategories, Date startDate,
			Date endDate) throws RemoteException;

	public List<String> getJobCategories() throws RemoteException;

	public void takeJobAction(Long jobId) throws RemoteException;

	void addNewJob(IJob newJob, List<String> categories) throws RemoteException;
}
