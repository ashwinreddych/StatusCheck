package com.ash.statuscheck.driver;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Driver {

	private static String USER_NAME = "rocking509@gmail.com"; // GMail user name (just the part
	private static String PASSWORD = "madcprreddy"; // GMail password
	private static String RECIPIENT = "ashwinreddych@gmail.com";
//smtp.gmail.com
	
	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("rocking509@gmail.com","madcprreddy");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rocking509@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("ashwinreddych@gmail.com"));
			message.setSubject("RemindMe Registration");
			message.setText("Hey Ashwin," +
					"\n\n Thank you for Registering RemindMe"+
					"\n Verification Code" +
					"\n\n www.RemindMe.com/Account.jsp");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
