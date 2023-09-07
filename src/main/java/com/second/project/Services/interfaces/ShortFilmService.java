package com.second.project.Services.interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.second.project.Dto.ShortFilmDto;
import com.second.project.Dto.UserDto;

public interface ShortFilmService {
    
    ResponseEntity<Object> auth(String token);
    ResponseEntity<Object> addShortFilm(String token, ShortFilmDto shortFilmDto);
    ResponseEntity<Object> getAllShortFilm(String token);
    ResponseEntity<Object> deleteShortFilmById(String token, String id);
    ResponseEntity<Object> getShortFilmById(String token, String id);
    ResponseEntity<Object> updateShortFilmById(String token, String id, Map<String, Object> fields);
    ResponseEntity<Object> addCastToShortFilm(String token, String shortFilmId, String castId);
}
