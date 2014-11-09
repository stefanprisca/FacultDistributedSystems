package ro.stefanprisca.distsystems.app3.client.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean {

	public String toLogIn() {
		return "/home.xhtml?faces-redirect=true";
	}

	public String toAdminPage() {
		return "/secured/admin.xhtml?faces-redirect=true";
	}

	public String toRegularPage() {
		return "/user.xhtml?faces-redirect=true";
	}

	public String toPostJobPage() {
		return "/postJob.xhtml?faces-redirect=true";
	}
}