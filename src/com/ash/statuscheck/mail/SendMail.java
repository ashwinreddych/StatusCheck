package com.ash.statuscheck.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ash.statuscheck.pojo.ProfileDetails;

public class SendMail {
	public boolean sendVerificationEmail(ProfileDetails profileDetails) {
		boolean value = false;
		
		value = sendEmailMessage(profileDetails);
		return value;
	}

	private boolean sendEmailMessage(ProfileDetails profileDetails) {
		
		boolean value = false;
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
			System.out.println(profileDetails.getEmail());
			System.out.println(profileDetails.getName());
			System.out.println(profileDetails.getVerifyCode());
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rocking509@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(profileDetails.getEmail()));
			message.setSubject("RemindMe Registration");
			message.setText("Hey " + profileDetails.getName() +"," +
					"\n\n Thank you for Registering RemindMe"+
					"\n Verification Code :" + profileDetails.getVerifyCode()+
					"\n\n www.RemindMe.com/Account.jsp");
			value = true;
			Transport.send(message);
			
			System.out.println("EMail Sent Sucessfully to :" +profileDetails.getEmail());
 
		} catch (MessagingException e) {
			System.out.println("Email Failed for User :" +profileDetails.getEmail());
			throw new RuntimeException(e);
		}
		return value;
	}

}
