package ro.stefanprisca.distsystems.app5.serviceconnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app5.beans.PacketTrackingBean;

@RunWith(JUnit4.class)
public class ServiceConnectionTests {

	@Test
	public void testGetPackListConn(){
		PacketTrackingBean pckTracking = new PacketTrackingBean();
		pckTracking.getPackets().forEach(p ->  System.out.println(p));
	}
}
