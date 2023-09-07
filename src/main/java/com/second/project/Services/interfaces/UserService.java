package com.second.project.Services.interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.second.project.Dto.LoginDto;
import com.second.project.Dto.UserDto;


public interface UserService {

      ResponseEntity<Object> addUser(UserDto userDto) ;

      ResponseEntity<Object> loginUser(LoginDto loginDto);

    ResponseEntity<Object> updateUser(String id, String id2, Map<String, Object> fields);

    ResponseEntity<Object> getUserById(String token, String id);

ResponseEntity<Object> getAllUser(String token);
    
}
