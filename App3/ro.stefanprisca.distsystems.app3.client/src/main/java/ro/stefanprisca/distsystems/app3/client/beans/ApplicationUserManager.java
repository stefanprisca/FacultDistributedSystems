package ro.stefanprisca.distsystems.app3.client.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import ro.stefanprisca.distsystems.utils.login.Constants;
import ro.stefanprisca.distsystems.utils.login.ILoginUtils;
import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@SessionScoped
@ManagedBean(name = "userManager", eager = true)
public class ApplicationUserManager {
	private ApplicationUser user;

	private String loginReqID;
	private String loginReqPW;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	protected final ILoginUtils loginUtils;

	public ApplicationUserManager() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ILoginUtils.class);
		factory.setAddress(ro.stefanprisca.distsystems.utils.login.Constants.SERVICE_ADDRESS);
		loginUtils = (ILoginUtils) factory.create();
	}

	public String doLogin() {
		String response = loginUtils.doLogin(loginReqID, loginReqPW);
		if (response.equals(Constants.LOGIN_ADMINISTRATOR)) {
			return navigationBean.toAdminPage();
		} else if (response.equals(Constants.LOGIN_REGULAR_USER)) {
			return navigationBean.toRegularPage();
		}
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