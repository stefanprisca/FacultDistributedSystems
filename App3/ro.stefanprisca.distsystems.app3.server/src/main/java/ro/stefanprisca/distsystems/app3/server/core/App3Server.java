package ro.stefanprisca.distsystems.app3.server.core;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class App3Server {
	public static void main(String[] argv) {
		try {
			//`System.setSecurityManager(new SecurityManager());
			
			LocateRegistry.createRegistry(ro.stefanprisca.distsystems.app3.common.Constants.JOB_ACCESS_PROVIDER_PORT);
			
			App3JobAccessProvider jobProvider = new App3JobAccessProvider();
			Naming.rebind(ro.stefanprisca.distsystems.app3.common.Constants.JOB_ACCESS_PROVIDER_REGPOINT, jobProvider);

			System.out.println("App3 Server is ready.");
		
		} catch (Exception e) {
			System.out.println("App3 Server failed: " + e);
		}
	}
}
