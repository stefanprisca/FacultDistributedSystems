package ro.stefanprisca.distsystems.app4.ejb.beans;

import javax.ejb.Stateless;

import ro.stefanprisca.distsystems.app4.ejb.common.Messages;

/**
 * Session Bean implementation class BookProviderBean
 */
@Stateless
public class BookProviderBean implements BookProviderRemote {

	public BookProviderBean() {
	}

	@Override
	public String getServerConfirmation() {
		return Messages.EJB_CONNECTION_TEST;
	}

}
