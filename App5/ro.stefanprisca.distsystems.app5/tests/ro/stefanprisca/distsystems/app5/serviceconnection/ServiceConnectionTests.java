package ro.stefanprisca.distsystems.app5.serviceconnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app5.beans.PacketTrackingBean;
import ro.stefanprisca.distsystems.app5.models.Packet;

@RunWith(JUnit4.class)
public class ServiceConnectionTests {

	private final PacketTrackingBean pckTracking = new PacketTrackingBean();
	@Test
	public void testGetPackListConn(){
		
		pckTracking.getPackets().forEach(p ->  System.out.println(p));
	}
	
	//@Test
	public void testPutNewPacket(){
		Packet p = new Packet();
		p.setLocation("");
		p.setName("TestPacket");
		pckTracking.setManagedPacket(p);
		pckTracking.confirmAddPacket();
	}
	
	@Test
	public void testEnableTracking(){
		pckTracking.enableTracking(3);
	}
	
	//@Test
	public void testGetLocation(){
		pckTracking.getPacketLocation(2);
		System.out.println(pckTracking.getCheckLocationResponse());
	}
	
	@Test
	public void testUpdateLocation(){
		pckTracking.setManagedId(3);
		pckTracking.setManagedLocation("Test Location");
		pckTracking.updatePacketLocation();
	}
	
	@Test
	public void testDeletePacket(){
		pckTracking.deletePacket(3);
	}
}
