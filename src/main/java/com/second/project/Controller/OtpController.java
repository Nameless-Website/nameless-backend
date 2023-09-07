package com.second.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.Services.Impl.EmailService;
import com.second.project.Services.Impl.OtpServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class OtpController {

    @Autowired
    OtpServiceImpl otpServiceImpl;
    
    @GetMapping("/sendotp/{mailId}")
    public ResponseEntity<Object> sendOtp(@PathVariable String mailId){
        return this.otpServiceImpl.sendOtp(mailId);
    }
    @GetMapping("/verifyotp/{mailId}/{otp}")
    public ResponseEntity<Object> verifyOtp(@PathVariable String mailId,@PathVariable Integer otp ){
        return this.otpServiceImpl.verifyOtp(otp,mailId);
    }
}
