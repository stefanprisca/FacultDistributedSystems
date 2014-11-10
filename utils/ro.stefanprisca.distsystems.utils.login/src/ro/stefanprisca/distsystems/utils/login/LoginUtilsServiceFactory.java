package ro.stefanprisca.distsystems.utils.login;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class LoginUtilsServiceFactory {

	public static ILoginUtils provideLoginUtilsServiceAccess() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ILoginUtils.class);
		factory.setAddress(ro.stefanprisca.distsystems.utils.login.Constants.SERVICE_ADDRESS);
		return (ILoginUtils) factory.create();
	}
}
