package ro.stefanprisca.distsystems.app3.client.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import ro.stefanprisca.distsystems.utils.login.Constants;
import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@ManagedBean(name = "adminManager")
public class AdministratorManagerBean extends ApplicationUserManager {

	private ApplicationUser editableUser = new ApplicationUser();
	private ApplicationUser newUser = new ApplicationUser();
	protected List<ApplicationUser> users;

	private boolean userType;

	public ApplicationUser getEditableUser() {
		return editableUser;
	}

	public void setEditableUser(ApplicationUser editableUser) {
		this.editableUser = editableUser;
	}

	public String editUser(ApplicationUser user) {
		user.setCanEdit(true);
		System.out.println("Editing user");
		return null;
	}

	public String saveUsers() {
		loginUtils.saveUsers(this.users);
		return null;
	}

	public String addUser() {

		if (userType) {
			newUser.setType(Constants.ADMINISTRATOR_TYPE);
		} else {
			newUser.setType(Constants.REGULAR_TYPE);
		}

		loginUtils.addUser(newUser);
		users.add(newUser);

		return null;
	}

	public String deleteUser(ApplicationUser user) {
		this.users.remove(user);
		loginUtils.deleteUser(user);
		return null;
	}

	public List<ApplicationUser> getUsers() {
		if (users == null) {
			users = loginUtils.getUsers();
		}
		return users;
	}

	public ApplicationUser getNewUser() {
		return newUser;
	}

	public void setNewUser(ApplicationUser newUser) {
		this.newUser = newUser;
	}

	@Override
	public String doLogout() {
		this.users.clear();
		this.users = null;

		return super.doLogout();
	}

	public Boolean getUserType() {
		return userType;
	}

	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

}
