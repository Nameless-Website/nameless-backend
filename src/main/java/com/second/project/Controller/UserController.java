package com.second.project.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.Dto.LoginDto;
import com.second.project.Dto.UserDto;
import com.second.project.Services.Impl.EmailService;
import com.second.project.Services.interfaces.UserService;

import jakarta.servlet.http.HttpSession;





@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    
   @Autowired
   public UserService userService;

   @Autowired
   public EmailService emailService;

 
    //User registration
    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    //User login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDto loginDto){
        return this.userService.loginUser(loginDto);
    }

     //Get users with id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@RequestHeader("auth-token") String token,@PathVariable String id){
      
        return this.userService.getUserById(token,id);
    }

    //Get all users
    @GetMapping
    public ResponseEntity<Object> getAllUser(@RequestHeader("auth-token") String token){
        // LoginResponse loginResponse= this.userService.loginUser(loginDto);
        return this.userService.getAllUser(token);
    }

    //Update user with id
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestHeader("auth-token") String token,@PathVariable String id,@RequestBody Map<String,Object> fields){
        return this.userService.updateUser(token,id,fields);
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<Object> changePassword(@RequestHeader("auth-token") String token,@PathVariable String id,@RequestBody Map<String,Object> fields){
        return this.userService.updateUser(token,id,fields);
    }
}
