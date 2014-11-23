package ro.stefanprisca.distsystems.app4.ejb.client.beans;

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

}
