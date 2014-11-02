package ro.stefanprisca.distsystems.app3.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IJobAccessProvider extends Remote {

	public String getServerStartupConfirm() throws RemoteException;

	public List<IJob> getJobs() throws RemoteException;

	public List<IJob> getJobs(String... jobCategory) throws RemoteException;

}
