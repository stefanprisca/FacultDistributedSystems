package ro.stefanprisca.distsystems.utils.login;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/", portName = "DefaultLoginUtilsPort", serviceName = "DefaultLoginUtilsService", endpointInterface = "ro.stefanprisca.distsystems.utils.login.ILoginUtils")
public class DefaultLoginUtils implements ILoginUtils {

	public static final String SERVICE_ADDRESS = "http://localhost:8080/ro.stefanprisca.distsystems.utils.login/services/DefaultLoginUtilsPort";

	@WebMethod(operationName = "getConfString", action = "urn:GetConfString")
	@WebResult(name = "return")
	public String getConfString() {
		return "Confirmed";
	}
}
