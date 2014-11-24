package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "navigationBean", eager = true)
public class NavigationBean {

	public String toAdminPage() {
		return "admin.xhtml?faces-redirect=true";
	}

	public String toRegularPage() {
		return "/user.xhtml?faces-redirect=true";
	}

	public String toLogIn() {
		return "/home.xhtml?faces-redirect=true";
	}

	public String toRegPage() {
		return "/register.xhtml?faces-redirect=true";
	}

}
