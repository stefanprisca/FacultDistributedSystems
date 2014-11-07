package ro.stefanprisca.distsystems.utils.login;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ServiceTest {

	@Test
	public void testConn() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(ILoginUtils.class);
		factory.setAddress(DefaultLoginUtils.SERVICE_ADDRESS);
		ILoginUtils client = (ILoginUtils) factory.create();

		System.out.println(client.getConfString());
	}
}
