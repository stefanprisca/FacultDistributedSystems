package ro.stefanprisca.distsystems.app1.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean {

	public String toLogIn() {
		return "/home.xhtml";
	}

	public String toAdminPage() {
		return "/secured/admin.xhtml";
	}

	public String toRegularPage() {
		return "/user.xhtml";
	}
}