package ro.stefanprisca.distsystems.app5.beans;

import static ro.stefanprisca.distsystems.app5.commons.Constants.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ro.stefanprisca.distsystems.app5.models.Packet;

@SessionScoped
@ManagedBean(name = "pckTracking", eager = true)
public class PacketTrackingBean {

	private Packet managedPacket;
	private int managedId = -1;
	private String managedLocation = "";
	private String checkLocationResponse;

	private List<Packet> getExternalPckts() {
		URLConnection connection;
		ArrayList<Packet> packets = new ArrayList<Packet>();
		try {
			connection = new URL(SERVICE_PCK_MAN_URI_BASE).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream input = connection.getInputStream();
			String result = readStream(input);

			JSONArray json = (JSONArray) new JSONParser().parse(result);
			for (Object s : json) {
				JSONObject packetJ = (JSONObject) s;
				Packet newPack = decodePckFromJson(packetJ);
				packets.add(newPack);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return packets;
	}

	public String addPacket() {
		this.setManagedPacket(new Packet());
		return NavigationBean.user_ToAddPacket();
	}

	public String confirmAddPacket() {
		HttpURLConnection connection;
		try {
			String query = String
					.format(SERVICE_PCK_MAN_RQ_ADDPCK, URLEncoder.encode(
							managedPacket.getName(), DEFAULT_CHARSET),
							URLEncoder.encode(managedPacket.getLocation(),
									DEFAULT_CHARSET));
			connection = (HttpURLConnection) new URL(SERVICE_PCK_MAN_URI_BASE
					+ query).openConnection();
			connection.setRequestMethod("GET");
			connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NavigationBean.addPacket_ToUserPage();
	}

	public String deletePacket(int id) {
		HttpURLConnection connection;
		try {
			String query = "/?"+PACKET_ATTRIBUTE_ID+"="+id;
			connection = (HttpURLConnection) new URL(SERVICE_PCK_MAN_URI_BASE
					+ query).openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("DELETE");
			connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String enableTracking(int id) {
		HttpURLConnection connection;
		try {
			String query = "/" + id;
			connection = (HttpURLConnection) new URL(SERVICE_PCK_MAN_URI_BASE
					+ query).openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(id);
			writer.close();
			connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getPacketLocation(int id) {
		HttpURLConnection connection;
		try {
			String query = "/" + id;
			connection = (HttpURLConnection) new URL(SERVICE_PCK_TRACK_URI_BASE
					+ query).openConnection();
			connection.setRequestMethod("GET");

			InputStream in = connection.getInputStream();
			String location = readStream(in).trim();
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesMessage.setDetail("The requested location is: " + location);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updatePacketLocation() {
		HttpURLConnection connection;
		try {
			String query = String.format(SERVICE_PCK_TRACK_RQ_UPDATELOC,
					managedId,
					URLEncoder.encode(managedLocation, DEFAULT_CHARSET));
			connection = (HttpURLConnection) new URL(SERVICE_PCK_TRACK_URI_BASE
					+ query).openConnection();
			connection.setRequestMethod("GET");

			connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NavigationBean.updateLoc_ToUserPage();
	}

	private String readStream(InputStream input) throws IOException {
		String result = "";
		int b = input.read();
		while (b != -1) {
			result += (char) b;
			b = input.read();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private String encodePckToJson(Packet toEncode) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(PACKET_ATTRIBUTE_ID, toEncode.getId());
		jsonObj.put(PACKET_ATTRIBUTE_NAME, toEncode.getName());
		jsonObj.put(PACKET_ATTRIBUTE_LOCATION, toEncode.getLocation());
		jsonObj.put(PACKET_ATTRIBUTE_ENABLETRK, toEncode.isTrackingEnabled());
		return jsonObj.toString();
	}

	private Packet decodePckFromJson(JSONObject packetJ) {
		Packet pck = new Packet();
		pck.setId((long) packetJ.get(PACKET_ATTRIBUTE_ID));
		pck.setName(((String) packetJ.get(PACKET_ATTRIBUTE_NAME)).trim());
		pck.setTrackingEnabled((boolean) packetJ
				.get(PACKET_ATTRIBUTE_ENABLETRK));

		return pck;
	}

	public String toUpdateLocation(int id) {
		managedId = id;
		return NavigationBean.user_ToUpdateLoc();
	}

	public List<Packet> getPackets() {
		return getExternalPckts();
	}

	public Packet getManagedPacket() {
		return managedPacket;
	}

	public void setManagedPacket(Packet managedPacket) {
		this.managedPacket = managedPacket;
	}

	public String getCheckLocationResponse() {
		return checkLocationResponse;
	}

	public void setCheckLocationResponse(String checkLocationResponse) {
		this.checkLocationResponse = checkLocationResponse;
	}

	public int getManagedId() {
		return managedId;
	}

	public void setManagedId(int managedId) {
		this.managedId = managedId;
	}

	public String getManagedLocation() {
		return managedLocation;
	}

	public void setManagedLocation(String managedLocation) {
		this.managedLocation = managedLocation;
	}

}
