package ro.stefanprisca.distsystems.app1.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app1.models.ApplicationUser;

@ManagedBean(name = "adminManager")
public class AdministratorManagerBean extends ApplicationUserManager {

	private final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	private ApplicationUser editableUser = new ApplicationUser();
	private ApplicationUser newUser = new ApplicationUser();
	protected List<ApplicationUser> users;

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
		EntityManager em = factory.createEntityManager();

		for (ApplicationUser user : users) {
			user.setCanEdit(false);
			em.getTransaction().begin();
			ApplicationUser old = em.find(ApplicationUser.class, user.getId());
			if (old != null) {
				old.setBirthDate(user.getBirthDate());
				old.setCanEdit(false);
				old.setName(user.getName());
				old.setHomeAddress(user.getHomeAddress());
				old.setLatitude(user.getLatitude());
				old.setLoginName(user.getLoginName());
				old.setLongitude(user.getLongitude());
				old.setPassword(user.getPassword());
			} else {
				old = user;
			}
			em.getTransaction().commit();
		}

		em.close();
		return null;
	}

	public String addUser() {
		users.add(newUser);

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		if (!em.contains(newUser)) {
			em.persist(newUser);
		}
		em.getTransaction().commit();
		em.close();
		return null;
	}

	public String deleteUser(ApplicationUser user) {
		this.users.remove(user);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		if (em.contains(newUser)) {
			em.remove(newUser);
		}
		em.getTransaction().commit();
		em.close();

		return null;
	}

	public List<ApplicationUser> getUsers() {
		if (users == null) {
			EntityManager em = factory.createEntityManager();
			// read the existing entries and write to console
			Query q = em.createQuery("select t from ApplicationUser t");
			users = q.getResultList();
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

}
