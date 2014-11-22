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

	private String loginReqID;
	private String loginReqPW;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	protected final ILoginUtils loginUtils;

	public ApplicationUserManagerBean() {
		loginUtils = LoginUtilsServiceFactory.provideLoginUtilsServiceAccess();
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
		return this.user;
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

}
