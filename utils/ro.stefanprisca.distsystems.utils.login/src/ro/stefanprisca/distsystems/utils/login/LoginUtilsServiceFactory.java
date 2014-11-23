package ro.stefanprisca.distsystems.utils.login;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class LoginUtilsServiceFactory {

	private static String dbName = "ds_assign1";
	private static String dbConnectionString = "jdbc:sqlserver://localhost:9895;databaseName=";

	public static ILoginUtils provideLoginUtilsServiceAccess(String dbNameIn) {
		dbName = dbNameIn;
		return provideLoginUtilsServiceAccess();
	}

	public static ILoginUtils provideLoginUtilsServiceAccess() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ILoginUtils.class);
		factory.setAddress(ro.stefanprisca.distsystems.utils.login.Constants.SERVICE_ADDRESS);
		return (ILoginUtils) factory.create();
	}

	public static String getDbConnectionString() {
		return dbConnectionString + dbName;
	}
}
