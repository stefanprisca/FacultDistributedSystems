package ro.stefanprisca.distsystems.utils.login;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://login.utils.distsystems.stefanprisca.ro/", portName = "DefaultLoginUtilsPort", serviceName = "DefaultLoginUtilsService")
public class DefaultLoginUtils {

	@WebMethod(operationName = "getConnectConfirmation", action = "urn:GetConnectConfirmation")
	public String getConnectConfirmation() {
		return "The webservice is alive and ok";
	}
}
