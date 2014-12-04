package ro.stefanprisca.distsystems.app4.ejb.client.utils;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.stefanprisca.distsystems.app4.ejb.common.Messages;

@JMSDestinationDefinitions(value = { @JMSDestinationDefinition(name = "java:/topic/JMSService", interfaceName = "javax.jms.Topic", destinationName = "JMSService") })
@WebServlet("/JMSService")
public class JMSPublisher extends HttpServlet {
	private static final long serialVersionUID = -8314035702649252239L;

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/topic/JMSService")
	private Topic topic;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		final Destination destination = topic;
		context.createProducer().send(destination, Messages.JMS_MAIL_MESSAGE);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
