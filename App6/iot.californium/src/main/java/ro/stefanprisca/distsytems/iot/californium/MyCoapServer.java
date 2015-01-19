package ro.stefanprisca.distsytems.iot.californium;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class MyCoapServer 
{
	public static final String RESOURCE_NAME = "MyCoapResource";
    public static void main( String[] args )
    {
        CoapServer server = new CoapServer();
        server.add(new MyCoapResource(RESOURCE_NAME));
        server.start();
    }
}

class MyCoapResource extends CoapResource{
	public static final String MY_FIRST_CONDITION = "Cond1";
	public static final String MY_SECOND_CONDITION = "Cond2";

	private List<String> ifMatchConditions = new ArrayList<String>();
	
	
	public MyCoapResource(String name) {
		super(name);
		ifMatchConditions.add(MY_FIRST_CONDITION);
		ifMatchConditions.add(MY_SECOND_CONDITION);
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("The server salutes you!");
	}
	
	
	
	@Override
	public void handlePUT(CoapExchange exchange) {
		exchange.accept();
		
		OptionSet options = exchange.getRequestOptions();
		List<byte[]> inConds = options.getIfMatch();
		boolean pass =false;
		
		System.out.println("Hello, You called the PUT method!");
		
		for(byte[] bSet : inConds){
			if(ifMatchConditions.parallelStream().anyMatch((s) -> s.equals(new String(bSet))) ){
				pass = true;
				break;
			}
		}
		
		if(pass){
			exchange.respond(ResponseCode.CREATED, "Everything is ok, do not panic!");
		}else{
			exchange.respond(ResponseCode.BAD_OPTION, "Nothing is ok. Time to panic!");
		}
	}
}