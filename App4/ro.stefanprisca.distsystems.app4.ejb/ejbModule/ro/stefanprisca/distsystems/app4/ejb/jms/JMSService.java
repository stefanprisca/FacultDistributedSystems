package ro.stefanprisca.distsystems.app4.ejb.jms;

import java.util.Properties;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ro.stefanprisca.distsystems.app4.ejb.common.Constants;
import ro.stefanprisca.distsystems.app4.ejb.common.Messages;
import ro.stefanprisca.distsystems.utils.login.ILoginUtils;
import ro.stefanprisca.distsystems.utils.login.LoginUtilsServiceFactory;
import ro.stefanprisca.distsystems.utils.login.models.ApplicationUser;

/**
 * Message-Driven Bean implementation class for: JMSService
 */
@MessageDriven(name = "JMSService", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/JMSService"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class JMSService implements MessageListener {

	private final ILoginUtils loginUtils;

	/**
	 * Default constructor.
	 */
	public JMSService() {
		this.loginUtils = LoginUtilsServiceFactory
				.provideLoginUtilsServiceAccess(Constants.DB_CONNECTION_DBNAME);
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				System.out
						.println("---------------------------------\n\n\nReceived Message from topic: "
								+ msg.getText()
								+ "\n\n\n-------------------------");
				this.broadcastEmail(Messages.JMS_MAIL_SUBJECT, msg.getText());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private void broadcastEmail(String jmsMailSubject, String text) {
		for (ApplicationUser user : loginUtils.getUsers()) {
			sendEmail(jmsMailSubject, text, user.getEmail());
		}
	}

	private void sendEmail(String subject, String contents, String address) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail." + Constants.EMAIL_PROTOCOL + ".host",
				Constants.EMAIL_HOST);
		properties.put("mail." + Constants.EMAIL_PROTOCOL + ".port",
				Constants.EMAIL_PORT);
		properties.put("mail." + Constants.EMAIL_PROTOCOL + ".auth", "true");

		// Get the default Session object.
		Session session = Session.getInstance(properties,
				new MailAuthenticator(Constants.EMAIL_LOGIN_ID,
						Constants.EMAIL_LOGIN_PW));

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(Constants.EMAIL_FROM_ADDRESS));

			message.addRecipient(javax.mail.Message.RecipientType.TO,
					new InternetAddress(address));

			message.setSubject(subject);

			message.setText(contents);

			// Send message
			Transport t = session.getTransport(Constants.EMAIL_PROTOCOL);
			t.connect();
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	class MailAuthenticator extends Authenticator {
		String user;
		String pw;

		public MailAuthenticator(String username, String password) {
			super();
			this.user = username;
			this.pw = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, pw);
		}
	}
}
