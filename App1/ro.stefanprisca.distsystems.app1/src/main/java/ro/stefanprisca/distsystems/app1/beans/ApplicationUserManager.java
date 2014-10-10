package ro.stefanprisca.distsystems.app1.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app1.models.ApplicationUser;

@ManagedBean(name = "userManager", eager = true)
public class ApplicationUserManager {

	private static final String PERSISTENCE_UNIT_NAME = "applicationUsers";
	private final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public List<ApplicationUser> getUsers() {
		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select t from ApplicationUser t");
		List<ApplicationUser> userList = q.getResultList();
		return userList;
	}
}
