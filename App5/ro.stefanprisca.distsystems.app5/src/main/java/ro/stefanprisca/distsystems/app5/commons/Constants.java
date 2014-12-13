package ro.stefanprisca.distsystems.app5.commons;

public class Constants {
	public static final String DB_CONNECTION_DBNAME = "ds_assign5";
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	public static final String PACKET_ATTRIBUTE_NAME  = "Name";
	public static final String PACKET_ATTRIBUTE_ID  = "Id";
	public static final String PACKET_ATTRIBUTE_LOCATION  = "Location";
	public static final String PACKET_ATTRIBUTE_ENABLETRK  = "EnableTracking";
	
	public static final String SERVICE_PCK_MAN_URI_BASE = "http://localhost:9259/api/packet";
	public static final String SERVICE_PCK_MAN_RQ_ADDPCK = "?"+PACKET_ATTRIBUTE_NAME+"=%s&"+PACKET_ATTRIBUTE_LOCATION+"=%s";
	
	public static final String SERVICE_PCK_TRACK_URI_BASE = "http://localhost:9259/api/tracking";

	public static final String SERVICE_PCK_TRACK_RQ_UPDATELOC = "/?"+PACKET_ATTRIBUTE_ID+"=%s&"+PACKET_ATTRIBUTE_LOCATION+"=%s";
	
}
