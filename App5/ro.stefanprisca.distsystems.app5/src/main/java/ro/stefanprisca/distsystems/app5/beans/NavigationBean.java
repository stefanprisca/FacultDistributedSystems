package ro.stefanprisca.distsystems.app5.beans;

public class NavigationBean {

	public static String login_ToRegularPage() {
		return "/user.xhtml?faces-redirect=true";
	}

	public static String logout_ToLogInPage() {
		return "/home.xhtml?faces-redirect=true";
	}

	public static String login_ToRegisterPage() {
		return "/register.xhtml?faces-redirect=true";
	}

	public static String user_ToAddPacket() {
		return "addPacket.xhtml?faces-redirect=true";
	}

	public static String addPacket_ToUserPage() {
		return "user.xhtml?faces-redirect=true";
	}

	public static String user_ToUpdateLoc() {
		return "updateLocation.xhtml?faces-redirect=true";
	}
	
	public static String updateLoc_ToUserPage() {
		return "user.xhtml?faces-redirect=true";
	}

}
