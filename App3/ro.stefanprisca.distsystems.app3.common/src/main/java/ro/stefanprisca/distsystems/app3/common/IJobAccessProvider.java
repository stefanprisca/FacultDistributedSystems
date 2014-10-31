package ro.stefanprisca.distsystems.app3.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJobAccessProvider extends Remote {

	public String getServerStartupConfirm() throws RemoteException;
}
