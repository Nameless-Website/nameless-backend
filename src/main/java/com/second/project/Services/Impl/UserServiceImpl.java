package com.second.project.Services.Impl;

import java.lang.reflect.Field;
// import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.results.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.second.project.Dto.LoginDto;
import com.second.project.Dto.UserDto;
import com.second.project.Entity.User;
import com.second.project.Repo.UserRepo;
import com.second.project.Response.ResponseHandler;
import com.second.project.Services.interfaces.UserService;
import com.second.project.Util.AuthUtil;
import com.second.project.Util.JwtUtil;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthUtil authUtil;

    private Field field;

    @Override
    public ResponseEntity<Object> addUser(UserDto userDto) {
        // TODO Auto-generated method stub
        User user = new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getEmail(),

                this.passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole());
        User user1 = userRepo.findByEmail(userDto.getEmail());
        if (user1 == null) {

            userRepo.save(user);
            return ResponseHandler.responseBuilder("New User Created", HttpStatus.OK, user);

        } else {
            return ResponseHandler.responseBuilder("User already exists, please login", HttpStatus.ALREADY_REPORTED,
                    null);
        }
    }

    @Override
    public ResponseEntity<Object> loginUser(LoginDto loginDto) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDto.getEmail());
        if (user1 != null) {
            String password = loginDto.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDto.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    String token = jwtUtil.generateToken(user1);
                    Map<String, String> jwtMap = new HashMap<>();
                    jwtMap.put("authToken", token);

                    return ResponseHandler.responseBuilder("Login Successful", HttpStatus.OK, jwtMap);

                } else {
                    return ResponseHandler.responseBuilder("Login Failed", HttpStatus.NOT_FOUND, null);
                }
            } else {
                return ResponseHandler.responseBuilder("password Not Match", HttpStatus.BAD_REQUEST, null);

            }
        } else {
            return ResponseHandler.responseBuilder("Email not exits", HttpStatus.BAD_REQUEST, null);

        }

    }

    @Override
    public ResponseEntity<Object> updateUser(String token, String id, Map<String, Object> fields) {
        // TODO Auto-generated method stub

        if (authUtil.isAdmin(token) ||authUtil.isUser(token)){
            User user = userRepo.findById(id).get();
            if (jwtUtil.validateToken(token, user)) {

                fields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(User.class, key);
                    field.setAccessible(true);

                    if (key == "password") {
                        // ReflectionUtils.setField(field, user, this.passwordEncoder.encode(value.toString()));
                    } else {

                        ReflectionUtils.setField(field, user, value);
                    }

                });

                userRepo.save(user);
                return ResponseHandler.responseBuilder("User updated", HttpStatus.OK, user);

            } else {
                return ResponseHandler.responseBuilder("User not authorized", HttpStatus.UNAUTHORIZED, null);
            }
        } else {
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.UNAUTHORIZED, null);
        }

    }

    @Override
    public ResponseEntity<Object> getUserById(String token, String id) {
        // TODO Auto-generated method stub
        User user = userRepo.findById(id).get();
        if (authUtil.isAdmin(token)) {

            return ResponseHandler.responseBuilder("User Details", HttpStatus.OK, user);
        } else {
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
    }

    @Override
    public ResponseEntity<Object> getAllUser(String token) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)){
            return ResponseHandler.responseBuilder("List of all users", HttpStatus.OK, userRepo.findAll());
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.UNAUTHORIZED, null);
        }
    }

}
