package com.second.project.Services.interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.second.project.Dto.CastDto;

public interface CastService {

    ResponseEntity<Object> getAllCast(String token);

    ResponseEntity<Object> createNewCast(String token, CastDto castDto);

    ResponseEntity<Object> getCastById(String token, String id);

    ResponseEntity<Object> deleteCastById(String token, String id);

    ResponseEntity<Object> updateCast(String token, String id, Map<String, Object> fields);
    
}
