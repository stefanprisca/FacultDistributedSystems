package ro.stefanprisca.distsystems.app1.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app1.models.ApplicationUser;

@SessionScoped
@ManagedBean(name = "userManager", eager = true)
public class ApplicationUserManager {

	public static final String PERSISTENCE_UNIT_NAME = "applicationUsers";
	public static final String ADMINISTRATOR_TYPE = "user.administrator";
	public static final String REGULAR_TYPE = "user.regular";

	private final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	private ApplicationUser user;

	private String loginReqID;
	private String loginReqPW;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	public boolean isAdminLogin() {
		return user != null && user.getType().equals(ADMINISTRATOR_TYPE);
	}

	public boolean isRegularLogin() {
		return user != null && user.getType().equals(REGULAR_TYPE);
	}

	public boolean isLogIn() {
		return isAdminLogin() || isRegularLogin();
	}

	public String doLogin() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select u from ApplicationUser u "
				+ "where u.loginID = :loginId " + "and u.loginPW = :loginPW");
		q.setParameter("loginId", loginReqID);
		q.setParameter("loginPW", loginReqPW);

		List<ApplicationUser> result = q.getResultList();

		if (result.isEmpty()) {
			// Set login ERROR
			FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			// To to login page
			return navigationBean.toLogIn();
		}

		this.user = result.get(0);

		if (isAdminLogin()) {
			return navigationBean.toAdminPage();
		} else if (isRegularLogin()) {
			return navigationBean.toRegularPage();
		}

		return navigationBean.toLogIn();

	}

	public String doLogout() {

		System.out.println("Loggin out...");

		ExternalContext ex = FacesContext.getCurrentInstance()
				.getExternalContext();
		ex.invalidateSession();

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
