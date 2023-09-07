package com.second.project.Services.Impl;

import java.io.File;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.second.project.Entity.User;
import com.second.project.Repo.UserRepo;
import com.second.project.Response.ResponseHandler;

import jakarta.servlet.http.HttpSession;


@Service
public class EmailService {
	// public static void main(String[] args) {
		
// 		System.out.println("preparing to send message ...");
// 		String message = "Hello , Dear, this is message for security check . ";
// 		String subject = "CodersArea : Confirmation";
// 		String to = "learncodewithdurgesh@gmail.com";
// 		String from = "techsoftindia2018@gmail.com";
		
// //		sendEmail(message,subject,to,from);
// 		sendAttach(message,subject,to,from);
// 	}
	Random random = new Random(1000);

	@Value("${email.pass}")
	private String pass;
		@Autowired
		UserRepo userRepo;
	//this is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {

		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("techsoftindia2018@gmail.com", "*******");
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//attachement..
		
		//file path
		String path="C:\\Users\\user\\Desktop\\ca_logo.png";
		
		
		MimeMultipart mimeMultipart = new MimeMultipart();
		//text
		//file
		
		MimeBodyPart textMime = new MimeBodyPart();
		
		MimeBodyPart fileMime = new MimeBodyPart();
		
		try {
			
			textMime.setText(message);
			
			File file=new File(path);
			fileMime.attachFile(file);
			
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		m.setContent(mimeMultipart);
		
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	
		System.out.println("Sent success...................");
		
		
	}

	//this is responsible to send email..
	public  Boolean sendEmail(String message, String subject, String to, String from) {
		
		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.starttls.enable",true);
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("namelessgroup2020@gmail.com", pass);
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		
		try {
            MimeMessage m = new MimeMessage(session);
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setText(message);
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		System.out.println("Sent success...................");
		return true;
		
		}catch (Exception e) {
			e.printStackTrace();
            return false;
		}
			
	}

		
	}


	