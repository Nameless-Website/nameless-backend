package com.second.project.Response;

import java.util.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message , HttpStatus httpStatus, Object responsObject){
        Map <String, Object> response = new HashMap<>();
        response.put("message",message);
        response.put("data", responsObject);
        response.put("status", httpStatus);

        return new ResponseEntity<>(response,httpStatus);

    }
}