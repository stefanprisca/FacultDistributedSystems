package ro.stefanprisca.distsytems.iot.paho.desktop;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import ro.stefanprisca.distsystems.iot.paho.constants.Constants;

/**
 * Hello world!
 *
 */
public class MQTTSample {

	private  MqttClient sampleClient;

	public MQTTSample(){
		try {
			sampleClient = new MqttClient(Constants.BROKER, Constants.CLIENTID);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public  void subscribe(String... topic) {

		try {

			sampleClient.subscribe(topic);
			System.out.println("Subscribed to topics! ");

		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	
	public  void publish(String topic, String contents) {
		try {

			System.out.println("Publishing message: " + Constants.CONTENT);
			MqttMessage message = new MqttMessage(contents.getBytes());
			message.setQos(Constants.QUOS);
			sampleClient.publish(topic, message);
			System.out.println("Message published");

		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	public  void initConnection() {
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			sampleClient = new MqttClient(Constants.BROKER, Constants.CLIENTID,
					persistence);
			sampleClient.setCallback(new MyMqttCallback());
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + Constants.BROKER);

			sampleClient.connect(connOpts);
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connected");
	}

	public  void disconect(){
		try {
			sampleClient.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Disconnected");
	}
}
