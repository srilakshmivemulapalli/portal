package com.nisum.portal.util;
import java.util.*;
import javax.mail.*;  
import javax.mail.internet.*;



/**
 * 
 * @author vpurini
 *
 */
public class MailSender {
	
	

	public static  void sendEmail( String sendMail,String password,String toMail,String strSubject,String bodyMsg) throws Exception{

			Properties props = new Properties();
			props.put(Constants.GMAIL_SMTP, Constants.TRUE_FLAG);
			props.put(Constants.GMAIL_START_TLS,  Constants.TRUE_FLAG);
			props.put(Constants.GMAIL_HOST, Constants.GMAIL_HOST_NAME);
			props.put(Constants.GMAIL_PORT, Constants.GMAIL_PORT_NUM);

			Authenticator authenticator = new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sendMail, password);
				}
			};

			Session session = Session.getInstance(props,authenticator);

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(sendMail));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(toMail));
				message.setSubject(strSubject);
				message.setText(bodyMsg);
				Transport.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
	
	
	public static String messageBody(String userName){
		
		return  "Hi"+" "+userName+","+System.lineSeparator()+System.lineSeparator()+" You have successfully completed your registration. Welcome to Nisum Portal."+System.lineSeparator()+System.lineSeparator()+"This is an auto generated e-mail. Please do not reply. Thanking you."+System.lineSeparator()+System.lineSeparator()+"Regards"+System.lineSeparator()+"Nisum Portal Application";

	}

}  

