package ro.stefanprisca.distsystems.utils.login;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

@WebService(name = "ILoginUtils", targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/")
public interface ILoginUtils {

	@WebMethod(operationName = "getConfString", action = "urn:GetConfString")
	@WebResult(name = "return")
	public abstract String getConfString();

	@WebMethod(operationName = "getUsers", action = "urn:GetUsers")
	@WebResult(name = "return")
	public abstract List<ApplicationUser> getUsers();

	@WebMethod(operationName = "saveUsers", action = "urn:SaveUsers")
	public abstract void saveUsers(
			@WebParam(name = "arg0") List<ApplicationUser> users);

	@WebMethod(operationName = "doLogin", action = "urn:DoLogin")
	@WebResult(name = "return")
	public abstract String doLogin(@WebParam(name = "arg0") String loginReqID, @WebParam(name = "arg1") String loginReqPW);
}