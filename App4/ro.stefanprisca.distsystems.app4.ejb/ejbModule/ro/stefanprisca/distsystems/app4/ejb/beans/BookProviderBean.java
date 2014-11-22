package ro.stefanprisca.distsystems.app4.ejb.beans;

import javax.ejb.Stateless;

import ro.stefanprisca.distsystems.app4.ejb.common.Constants;

/**
 * Session Bean implementation class BookProviderBean
 */
@Stateless
public class BookProviderBean implements BookProviderRemote {

	public BookProviderBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getServerConfirmation() {
		// TODO Auto-generated method stub
		return Constants.EJB_CONNECTION_TEST;
	}

}
