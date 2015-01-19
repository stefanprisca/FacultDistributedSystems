package ro.stefanprisca.distsytems.iot.californium;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;

public class MyCoapClient {

	public static final int APPLICATION_XML = 41;
	
	private static void printResponse(CoapResponse response){
		if(response.getCode() != ResponseCode.NOT_FOUND){
			System.out.println("We got a good response code: " + response.getCode());
			System.out.println("The contents of the response are: " + response.getResponseText());
		}else{
			System.out.println("The resource was not found!");
		}
	}
	
	public static void main(String[] args) {
		CoapClient client = new CoapClient("coap://localhost/"+MyCoapServer.RESOURCE_NAME);
		CoapResponse response = client.get();
		printResponse(response);
		
		response = client.putIfMatch("Hello from the client", APPLICATION_XML, MyCoapResource.MY_FIRST_CONDITION.getBytes());
		printResponse(response);
		
		response =client.post("Not allowed", APPLICATION_XML);
		printResponse(response);
		
	}

}
