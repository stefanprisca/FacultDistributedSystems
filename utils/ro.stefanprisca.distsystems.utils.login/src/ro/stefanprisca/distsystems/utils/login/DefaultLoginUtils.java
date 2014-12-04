package ro.stefanprisca.distsystems.utils.login;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@SuppressWarnings("restriction")
@WebService(targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/", portName = "DefaultLoginUtilsPort", serviceName = "DefaultLoginUtilsService", endpointInterface = "ro.stefanprisca.distsystems.utils.login.ILoginUtils")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class DefaultLoginUtils implements ILoginUtils {

	private final EntityManagerFactory factory;
	protected List<ApplicationUser> users;

	public DefaultLoginUtils() {
		Map<String, Object> properties = LoginUtilsServiceFactory
				.provideDefaultConnectionProperties();
		factory = Persistence.createEntityManagerFactory(
				Constants.PERSISTENCE_UNIT_NAME, properties);

		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select t from ApplicationUser t");
		this.users = q.getResultList();
		em.close();
	}

	@WebMethod(operationName = "getConfString", action = "urn:GetConfString")
	@WebResult(name = "return")
	public String getConfString() {
		System.out.println(Constants.CONNECTION_CONF_STRING);
		return Constants.CONNECTION_CONF_STRING;
	}

	@WebMethod(operationName = "getUsers", action = "urn:GetUsers")
	@WebResult(name = "return")
	public List<ApplicationUser> getUsers() {
		if (users == null) {
			EntityManager em = factory.createEntityManager();
			Query q = em.createQuery("select t from ApplicationUser t");
			this.users = q.getResultList();
			em.close();
		}

		return users;

	}

	@WebMethod(operationName = "saveUsers", action = "urn:SaveUsers")
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
				old.setLoginName(user.getLoginName());
				old.setEmail(user.getEmail());
				old.setPassword(user.getPassword());
			} else {
				old = user;
			}

			em.getTransaction().commit();
		}

		em.close();
	}

	@WebMethod(operationName = "addUser", action = "urn:AddUser")
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

	@WebMethod(operationName = "deleteUser", action = "urn:DeleteUser")
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

	@WebMethod(operationName = "doLogin", action = "urn:DoLogin")
	@WebResult(name = "return")
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
