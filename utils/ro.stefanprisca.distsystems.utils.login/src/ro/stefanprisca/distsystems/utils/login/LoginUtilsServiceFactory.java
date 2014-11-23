package ro.stefanprisca.distsystems.utils.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class LoginUtilsServiceFactory {

	private static String dbName = "ds_assign4";

	public static void setDbName(String dbName) {
		LoginUtilsServiceFactory.dbName = dbName;
	}

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

	public static Map<String, Object> provideDefaultConnectionProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();

		properties.put(Constants.PERSISTT_JDBC_URL_CONNECTION,
				Constants.PERSISTT_JDBC_URL_CONNECTION_VAL);
		properties.put(Constants.PERSISTT_ELINK_PERST_LVL,
				Constants.PERSISTT_ELINK_PERST_LVL_VAL);

		properties.put(Constants.PERSISTT_ELINK_JDBC_DRIVER,
				Constants.PERSISTT_ELINK_JDBC_DRIVER_VAL);
		properties.put(Constants.PERSISTT_ELINK_JDBC_PASSWORD,
				Constants.PERSISTT_ELINK_JDBC_PASSWORD_VAL);
		properties.put(Constants.PERSISTT_ELINK_JDBC_USER,
				Constants.PERSISTT_ELINK_JDBC_USER_VAL);

		properties.put(Constants.PERSISTT_JDBC_URL_CONNECTION,
				LoginUtilsServiceFactory.getDbConnectionString());
		return properties;
	}
}
