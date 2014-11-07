package ro.stefanprisca.distsystems.utils.login;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "ILoginUtils", targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/")
public interface ILoginUtils {

	@WebMethod(operationName = "getConfString", action = "urn:GetConfString")
	@WebResult(name = "return")
	public abstract String getConfString();

}