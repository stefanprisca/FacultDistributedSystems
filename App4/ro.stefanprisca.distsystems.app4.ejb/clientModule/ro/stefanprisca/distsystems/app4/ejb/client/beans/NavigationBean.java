package ro.stefanprisca.distsystems.app4.ejb.client.beans;

public class NavigationBean {

	public static String login_ToAdminPage() {
		return "secured/admin.xhtml?faces-redirect=true";
	}

	public static String login_ToRegularPage() {
		return "/user.xhtml?faces-redirect=true";
	}

	public static String logout_ToLogInPage() {
		return "/home.xhtml?faces-redirect=true";
	}

	public static String login_ToRegPage() {
		return "/register.xhtml?faces-redirect=true";
	}

	public static String admin_ToEditBookPage() {
		// this page is reached from the administrator page, so no need to put
		// < secured/ > in front of it, as the pages are
		// in the same folder
		return "editBook.xhtml?faces-redirect=true";
	}

	public static String editB_ToAdminPage() {
		return "admin.xhtml?faces-redirect=true";
	}

}
