package ro.stefanprisca.distsytems.iot.paho.desktop;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttCallback implements MqttCallback {

	public static String report;
	public void connectionLost(Throwable arg0) {
		 report = "\n\n ------------------------ Connection lost --------------------------\n"
				+ "Connection was lost due to: " + arg0.getStackTrace();

		 System.out.println(report);

	}

	public void deliveryComplete(IMqttDeliveryToken devToken) {
		report = "\n\n ------------------------ Delivered Message --------------------------"
				+ "\nMessage was sent by the client: "
				+ devToken.getClient().getClientId()
				+ "\nMessage was sent to the following topics: "
				+ devToken.getTopics();

		try {
			report += "\nMessage content is "
					+ devToken.getMessage().getPayload();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		System.out.println(report);
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		 report = "\n\n ------------------------ Got Message --------------------------"
				+ "\nA message arrived on the topic:" + topic +
				"\nThe message contents are: " + new String(message.getPayload());
		
		 System.out.println(report);
	
	}

}
