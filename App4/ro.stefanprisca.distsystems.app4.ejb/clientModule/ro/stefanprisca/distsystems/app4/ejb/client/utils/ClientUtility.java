package ro.stefanprisca.distsystems.app4.ejb.client.utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderBean;
import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.common.Constants;

public class ClientUtility {

	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static void main(String[] argv) {
		doLookup();
	}

	public static BookProviderRemote doLookup() {
		Context context = null;
		BookProviderRemote bean = null;
		try {
			// 1. Obtaining Context
			context = ClientUtility.getInitialContext();
			// 2. Generate JNDI Lookup name
			String lookupName = getLookupName();
			// 3. Lookup and cast
			bean = (BookProviderRemote) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static String getLookupName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = Constants.EJB_APP_NAME;

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = Constants.EJB_BUNDLE_NAME;

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = Constants.EJB_DISTINCT_NAME;

		// The EJB bean implementation class name
		String beanName = BookProviderBean.class.getSimpleName();

		// Fully qualified remote interface name
		final String interfaceName = BookProviderRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		return name;
	}

	private static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

}