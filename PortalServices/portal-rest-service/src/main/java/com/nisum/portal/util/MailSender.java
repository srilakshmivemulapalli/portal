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
			message.setContent(bodyMsg, "text/html");
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


	public static String messageBody(String userName){

		return  "Hi"+" "+userName+","+System.lineSeparator()+System.lineSeparator()+" You have successfully completed your registration. Welcome to Nisum Portal."+System.lineSeparator()+System.lineSeparator()+"This is an auto generated e-mail. Please do not reply. Thanking you."+System.lineSeparator()+System.lineSeparator()+"Regards"+System.lineSeparator()+"Nisum Portal Application";

	}


	public static String questionMsgBody(String userName, String question,String description){

		StringBuilder sb = new StringBuilder();

		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;'>");
		sb.append("<i>Hi"+"  "+userName+","+"</i>");
		sb.append("<br><br>");
		
		sb.append("<i> We have new question in Nisum Portal</i>");
		sb.append("<br>");
		sb.append("<b> Question :  </b><i>"+question+"..."+"</i>");
		sb.append("<a href='http://localhost:8080/portal/#/questions'>");
		sb.append("<i>for more details.</i>");
		sb.append("</a>");
		sb.append("<br><br>");
		sb.append("<i>This is an auto generated e-mail. Please check this in Nisum portal. Thanking you.</i>");
		sb.append("<br><br>");
		sb.append("<i>Regards,</i>");
		sb.append("<br><br>");
		sb.append("<font color=red><i>Nisum Portal Application.</i></font>");
		return  sb.toString();

	}
	
	
	public static String ReplayToQuestionBody(String userName,String description,String questionId){

		StringBuilder sb = new StringBuilder();

		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;'>");
		sb.append("<i>Hi"+"  "+userName+","+"</i>");
		sb.append("<br><br>");
		
		sb.append("<i> Please find the answer in Nisum Portal</i>");
		sb.append("<br>");
		//sb.append("<b> Question :  </b><i>"+question+"..."+"</i>");
		sb.append("<a href='http://localhost:8080/portal/#/question/'"+Integer.parseInt(questionId)+">");
		sb.append("<i> Click for more details.</i>");
		sb.append("</a>");
		//sb.append("<table width='600px' align='center' border='1' cellpadding='0' cellspacing='0' style='border-top:5px solid white;'");
		//sb.append("<tr><td>"+description+"</td></tr></table>");
		//sb.append("<br><br>");
		//sb.append("<a href='http://localhost:8080/portal/#/questions'>");
		//sb.append("<i>for more details.</i>");
		//sb.append("</a>");
		sb.append("<br><br>");
		sb.append("<i>This is an auto generated e-mail. Please check this in Nisum portal. Thanking you.</i>");
		sb.append("<br><br>");
		sb.append("<i>Regards,</i>");
		sb.append("<br><br>");
		sb.append("<font color=red><i>Nisum Portal Application.</i></font>");
		return  sb.toString();

	}
	
	
	public static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	

}  

