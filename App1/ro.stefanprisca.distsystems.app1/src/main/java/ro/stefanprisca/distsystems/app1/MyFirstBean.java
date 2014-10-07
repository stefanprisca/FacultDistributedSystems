package ro.stefanprisca.distsystems.app1;

import javax.faces.bean.ManagedBean;

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
