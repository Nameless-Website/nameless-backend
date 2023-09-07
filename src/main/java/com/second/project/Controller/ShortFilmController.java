package com.second.project.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.Dto.ShortFilmDto;
import com.second.project.Services.interfaces.ShortFilmService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/shortfilm")
public class ShortFilmController {
    
    @Autowired
    ShortFilmService shortFilmService;


    //Creeating a short film
    @PostMapping
    
    public ResponseEntity<Object> addShortFilm(@RequestHeader("auth-token") String token,@RequestBody ShortFilmDto shortFilmDto){
        return this.shortFilmService.addShortFilm(token,shortFilmDto);
    }

    //Get all short films
    @GetMapping
    public ResponseEntity<Object> getAllShortFilm(@RequestHeader("auth-token") String token){
        return this.shortFilmService.getAllShortFilm(token);
    }

    //Delete short film with id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShortFilmById(@PathVariable("id") String id,@RequestHeader("auth-token") String token){
        return this.shortFilmService.deleteShortFilmById(token,id);
    }

    //Get short film with id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getShortFilmById(@PathVariable("id") String id,@RequestHeader("auth-token") String token){
        return this.shortFilmService.getShortFilmById(token,id);
    }

    //Update short film by id
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateShortFilmById(@RequestHeader("auth-token") String token,@PathVariable String id,@RequestBody Map<String,Object> fields){
        return this.shortFilmService.updateShortFilmById(token,id,fields);
    }

    //Adding cast to short film
    @PutMapping("/{shortFilmId}/{castId}")
    public ResponseEntity<Object> addCastToShortFilm(@PathVariable("shortFilmId") String shortFilmId,@PathVariable("castId") String castId,@RequestHeader("auth-token") String token){
        return this.shortFilmService.addCastToShortFilm(token,shortFilmId,castId);
    }

    //Check auth token details
    @GetMapping("/auth")
    public ResponseEntity<Object> auth(@RequestHeader("auth-token") String token){
        return this.shortFilmService.auth(token);
    }
}
