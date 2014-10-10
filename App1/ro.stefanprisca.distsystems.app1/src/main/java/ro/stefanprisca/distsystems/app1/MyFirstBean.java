package ro.stefanprisca.distsystems.app1;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.eclipse.persistence.platform.database.SQLServerPlatform;

@SessionScoped
@ManagedBean(name = "helloWorld", eager = true)
public class MyFirstBean {

	public MyFirstBean() {
		// TODO Auto-generated constructor stub
		System.out.println("HelloWorld started!");
	}
	

	public String getMessage() {

		return "Hello World!";
	}
}
