package ro.stefanprisca.distsystems.app4.ejb.beans;

import javax.ejb.Remote;

@Remote
public interface BookProviderRemote {
	public String getServerConfirmation();
}
