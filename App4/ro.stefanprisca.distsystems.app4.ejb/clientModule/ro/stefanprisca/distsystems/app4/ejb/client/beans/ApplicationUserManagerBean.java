package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ro.stefanprisca.distsystems.utils.login.Constants;
import ro.stefanprisca.distsystems.utils.login.ILoginUtils;
import ro.stefanprisca.distsystems.utils.login.LoginUtilsServiceFactory;
import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@SessionScoped
@ManagedBean(name = "userManager", eager = true)
public class ApplicationUserManagerBean {
	private ApplicationUser user;
	private boolean newAdmin = false;

	private String loginReqID;
	private String loginReqPW;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	protected final ILoginUtils loginUtils;

	public ApplicationUserManagerBean() {
		String connName = ro.stefanprisca.distsystems.app4.ejb.common.Constants.DB_CONNECTION_DBNAME;
		loginUtils = LoginUtilsServiceFactory
				.provideLoginUtilsServiceAccess(connName);
	}

	public String addUser() {
		if (this.user != null) {
			if (this.newAdmin) {
				user.setType(Constants.ADMINISTRATOR_TYPE);
			} else {
				user.setType(Constants.REGULAR_TYPE);
			}
			loginUtils.addUser(this.user);
			this.loginReqID = this.user.getLoginID();
			this.loginReqPW = this.user.getLoginPW();

			return doLogin();
		} else {
			return null;
		}
	}

	public String doLogin() {

		this.user = new ApplicationUser();
		String response = loginUtils.doLogin(loginReqID, loginReqPW);
		if (response.equals(Constants.LOGIN_ADMINISTRATOR)) {
			this.user.setType(Constants.ADMINISTRATOR_TYPE);
			return navigationBean.toAdminPage();
		} else if (response.equals(Constants.LOGIN_REGULAR_USER)) {
			this.user.setType(Constants.REGULAR_TYPE);
			return navigationBean.toRegularPage();
		}
		this.user = null;
		return navigationBean.toLogIn();
	}

	public String doLogout() {

		System.out.println("Loggin out...");

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc != null) {

			ExternalContext ex = fc.getExternalContext();
			ex.invalidateSession();

		}

		this.user = null;

		return navigationBean.toLogIn();
	}

	public boolean isAdminLogin() {
		return this.user != null
				&& this.user.getType().equals(Constants.ADMINISTRATOR_TYPE);
	}

	// --------------------------

	public ApplicationUser getUser() {
		if (this.user == null) {
			this.user = new ApplicationUser();
		}
		return this.user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public String getLoginReqID() {
		return loginReqID;
	}

	public void setLoginReqID(String loginReqID) {
		this.loginReqID = loginReqID;
	}

	public String getLoginReqPW() {
		return loginReqPW;
	}

	public void setLoginReqPW(String loginReqPW) {
		this.loginReqPW = loginReqPW;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public boolean isNewAdmin() {
		return newAdmin;
	}

	public void setNewAdmin(boolean newAdmin) {
		this.newAdmin = newAdmin;
	}

}
