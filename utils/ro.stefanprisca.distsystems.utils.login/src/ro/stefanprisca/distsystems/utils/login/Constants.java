package ro.stefanprisca.distsystems.utils.login;

public class Constants {

	public static final String SERVICE_ADDRESS = "http://localhost:8080/loginUtilsService/services/DefaultLoginUtilsPort";
	public static final String ADMINISTRATOR_TYPE = "user.administrator";
	public static final String REGULAR_TYPE = "user.regular";
	public static final String PERSISTENCE_UNIT_NAME = "applicationUsers";

	public static final String CONNECTION_CONF_STRING = "Connection to web service established";

	public static final String LOGIN_ERROR = "loginValidationError";
	public static final String LOGIN_ADMINISTRATOR = "loginAdministrator";
	public static final String LOGIN_REGULAR_USER = "loginRegularUser";

	public static final String PERSISTT_JDBC_URL_CONNECTION = "eclipselink.jdbc.url";
	public static final String PERSISTT_JDBC_URL_CONNECTION_VAL = "ds_assign1";
	public static final String PERSISTT_ELINK_PERST_LVL = "eclipselink.logging.level";
	public static final String PERSISTT_ELINK_PERST_LVL_VAL = "FINE";

	public static final String PERSISTT_ELINK_JDBC_DRIVER = "eclipselink.jdbc.driver";
	public static final String PERSISTT_ELINK_JDBC_DRIVER_VAL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static final String PERSISTT_ELINK_JDBC_PASSWORD = "eclipselink.jdbc.password";
	public static final String PERSISTT_ELINK_JDBC_PASSWORD_VAL = "ds_2014";

	public static final String PERSISTT_ELINK_JDBC_USER = "eclipselink.jdbc.user";
	public static final String PERSISTT_ELINK_JDBC_USER_VAL = "ds_2014";
}
