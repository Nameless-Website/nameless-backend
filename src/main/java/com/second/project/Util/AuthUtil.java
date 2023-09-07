package com.second.project.Util;

import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.second.project.Entity.User;

import io.jsonwebtoken.Claims;

@Component
public class AuthUtil {

    @Autowired
    JwtUtil jwtUtil;

     public boolean isAdmin(String token){

        Claims claims = jwtUtil.extractAllClaims(token);
        if(claims.get("role").equals("admin")){
           
            return true;
        }else{
            
            return false;
        }
     
     }
     public boolean isUser(String token){

         if(jwtUtil.isTokenExpired(token)){
            return false;
         }
         else{
            Claims claims = jwtUtil.extractAllClaims(token);
            if(claims.get("role").equals("user")){
              
               return true;
           }else{
               
               return false;
           }
         }
         
     
     }
}
