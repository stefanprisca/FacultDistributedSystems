package ro.stefanprisca.distsystems.app5.beans;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;








import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.eclipse.persistence.internal.oxm.record.json.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ro.stefanprisca.distsystems.app5.commons.Constants;
import ro.stefanprisca.distsystems.app5.models.Packet;

@SessionScoped
@ManagedBean(name = "pckTracking", eager = true)
public class PacketTrackingBean {

	private List<Packet> packets;
	
	public PacketTrackingBean(){
		this.packets = getExternalPckts();
	}

	private List<Packet> getExternalPckts(){
		URLConnection connection;
		ArrayList<Packet> packets = new ArrayList<Packet>();
		try {
			connection = new URL(Constants.SERVICE_BASE_URI_PCK_MAN)
					.openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream input = connection.getInputStream();
			String result = "";
			int b = input.read();
			while(b != -1){
				result+= (char)b;
				b = input.read();
			}
			System.out.println(result);
			
			JSONArray json = (JSONArray) new JSONParser().parse(result);
			JSONParser parser = new JSONParser();
			for(Object s : json){
				JSONObject packetJ = (JSONObject) s;
				Packet newPack = new Packet();
				newPack.setId((Long)packetJ.get("Id"));
				newPack.setLocation(((String) packetJ.get("Location")).trim());
				newPack.setName(((String) packetJ.get("Name")).trim());
				newPack.setTrackingEnabled((Boolean) packetJ.get("EnableTracking"));
				packets.add(newPack);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return packets;
	}
	public List<Packet> getPackets() {
		return packets;
	}

	public void setPackets(List<Packet> packets) {
		this.packets = packets;
	}
}
