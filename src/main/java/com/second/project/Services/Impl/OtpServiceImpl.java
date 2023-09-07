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

import com.second.project.Entity.Otp;
import com.second.project.Entity.User;
import com.second.project.Repo.OtpRepo;
import com.second.project.Repo.UserRepo;
import com.second.project.Response.ResponseHandler;
import com.second.project.Services.interfaces.OtpService;

import jakarta.servlet.http.HttpSession;


@Service
public class OtpServiceImpl implements OtpService{
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
    @Autowired
    EmailService emailService;

	@Value("${email.pass}")
	private String pass;
		@Autowired
		UserRepo userRepo;

        @Autowired
		OtpRepo otpRepo;
        


	public ResponseEntity<Object> sendOtp(String email){
		int otp = 1000 + random.nextInt(9000);
		User user = userRepo.findByEmail(email);
		if(user!=null){
            Otp otpCon = new Otp(otp,email);
			Boolean flag =emailService.sendEmail("Your OTP for Nameless is : "+otp,"Forgot Password",email,"namelessgroup2020@gmail.com");
			if(flag){
				otpRepo.save(otpCon);
			}
			return ResponseHandler.responseBuilder("OTP sent to "+email, HttpStatus.OK, null);
		}
		else{
			return ResponseHandler.responseBuilder("Email with this user Not Found", HttpStatus.NOT_FOUND, null);
		}


	}

	
	public ResponseEntity<Object> verifyOtp(Integer otp, String email){

		Otp otpObj = otpRepo.findByEmail(email);
		if(otp ==(int)otpObj.getValue()){
            otpRepo.delete(otpObj);
			return ResponseHandler.responseBuilder("Verification Successful", HttpStatus.OK, null);
		}
		else{
			return ResponseHandler.responseBuilder("Verification failed! Please enter correct OTP", HttpStatus.NOT_ACCEPTABLE, null);
		}

	}
}

	