package ro.stefanprisca.distsystems.utils.login;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@WebService(targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/", portName = "DefaultLoginUtilsPort", serviceName = "DefaultLoginUtilsService", endpointInterface = "ro.stefanprisca.distsystems.utils.login.ILoginUtils")
public class DefaultLoginUtils implements ILoginUtils {

	private final EntityManagerFactory factory;
	protected List<ApplicationUser> users;

	public DefaultLoginUtils() {
		factory = Persistence
				.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select t from ApplicationUser t");
		this.users = q.getResultList();
		em.close();
	}

	@WebMethod(operationName = "getConfString", action = "urn:GetConfString")
	@WebResult(name = "return")
	public String getConfString() {
		return Constants.CONNECTION_CONF_STRING;
	}

	public List<ApplicationUser> getUsers() {
		if (users == null) {
			EntityManager em = factory.createEntityManager();
			// read the existing entries and write to console
			Query q = em.createQuery("select t from ApplicationUser t");
			users = q.getResultList();
			em.close();
		}

		return users;

	}

	public void saveUsers(List<ApplicationUser> users) {
		EntityManager em = factory.createEntityManager();

		this.users = users;

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
	}

	public void addUser(ApplicationUser newUser) {

		users.add(newUser);

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		if (!em.contains(newUser)) {
			em.persist(newUser);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(ApplicationUser user) {
		this.users.remove(user);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		if (em.contains(em.find(ApplicationUser.class, user.getId()))) {
			em.remove(em.find(ApplicationUser.class, user.getId()));
		}
		em.getTransaction().commit();
		em.close();
	}

	public boolean isAdminLogin(ApplicationUser user) {
		return user != null
				&& user.getType().equals(Constants.ADMINISTRATOR_TYPE);
	}

	public boolean isRegularLogin(ApplicationUser user) {
		return user != null && user.getType().equals(Constants.REGULAR_TYPE);
	}

	public String doLogin(String loginReqID, String loginReqPW) {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select u from ApplicationUser u "
				+ "where u.loginID = :loginId " + "and u.loginPW = :loginPW");
		q.setParameter("loginId", loginReqID);
		q.setParameter("loginPW", loginReqPW);

		List<ApplicationUser> result = q.getResultList();

		if (result.isEmpty()) {
			return Constants.LOGIN_ERROR;
		}

		ApplicationUser user = result.get(0);

		if (isAdminLogin(user)) {
			return Constants.LOGIN_ADMINISTRATOR;
		} else if (isRegularLogin(user)) {
			return Constants.LOGIN_REGULAR_USER;
		}

		return Constants.LOGIN_ERROR;

	}
}
