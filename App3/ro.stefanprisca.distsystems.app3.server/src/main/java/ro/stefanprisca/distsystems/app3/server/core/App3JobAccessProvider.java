package ro.stefanprisca.distsystems.app3.server.core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ro.stefanprisca.distsystems.app3.common.IJobAccessProvider;
import ro.stefanprisca.distsystems.app3.common.Messages;

public class App3JobAccessProvider extends UnicastRemoteObject implements IJobAccessProvider {
	private static final long serialVersionUID = 2152967318134529091L;

	protected App3JobAccessProvider() throws RemoteException {
		super();
	}

	public String getServerStartupConfirm() throws RemoteException {
		return Messages.REMOTE_RESPONSE_CONFIRMATION_MSG;
	}

}
