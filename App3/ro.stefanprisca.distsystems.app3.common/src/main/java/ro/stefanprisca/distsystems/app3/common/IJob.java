package ro.stefanprisca.distsystems.app3.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IJob extends Remote {
	public Date getDeadline() throws RemoteException;

	public String getContactDetails() throws RemoteException;

	public String getJobSpecification() throws RemoteException;

	public String getTitle() throws RemoteException;

	public String getCompName() throws RemoteException;

	public String getDisplayCategories() throws RemoteException;

	public Boolean getTaken() throws RemoteException;

}
