package ro.stefanprisca.distsystems.app1.restful;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.xml.XMLSource;

import ro.stefanprisca.distsystems.app1.beans.TimeZoneResponse;

@SessionScoped
@ManagedBean(name = "restfulClient")
public class RestfulClient implements Serializable {
	private static final long serialVersionUID = -5743775660211742805L;
	private static final String SERVICE_BASE_URI = "https://maps.googleapis.com/maps/api/timezone/xml";
	private static final String GTZAPI_LOCATION = "location";
	private static final String GTZAPI_TIMESTAMP = "timestamp";

	private transient WebClient client;

	public String getTimeZone(String lat, String lgt) {

		java.util.Date date = new java.util.Date();
		Long currTmp = new Double(
				((new Timestamp(date.getTime()).getTime()) / 1000.0))
				.longValue();

		client = WebClient.create(SERVICE_BASE_URI);
		client.query(GTZAPI_LOCATION, lat + "," + lgt);
		client.query(GTZAPI_TIMESTAMP, String.valueOf(currTmp));

		System.out.println(client.getCurrentURI().toString());
		XMLSource source = client.get(XMLSource.class);

		source.setBuffering();
		System.out.println(source.getValue("/TimeZoneResponse"));
		TimeZoneResponse timeZone = source.getNode("/TimeZoneResponse",
				TimeZoneResponse.class);

		client.close();

		return prettyFormat(timeZone, currTmp);

	}

	private String prettyFormat(TimeZoneResponse timeZone, Long currTmp) {
		Long time = currTmp
				+ Double.valueOf(timeZone.getDst_offset()).longValue()
				+ Double.valueOf(timeZone.getRaw_offset()).longValue();

		time = time * 1000; // this needs to be done because the Google API
							// works with seconds instead of milliseconds;

		String localTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(new Timestamp(time));

		String prettyString = "Your current time based on the Google Timezone API: "
				+ localTime
				+ " ; "
				+ timeZone.getTime_zone_name()
				+ " ( "
				+ timeZone.getTime_zone_id() + " ). ";
		return prettyString;
	}

}
