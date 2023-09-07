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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.Dto.CastDto;
import com.second.project.Services.interfaces.CastService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cast")
public class CastController {
    @Autowired
    private CastService castService;

    //Create new cast
    @PostMapping
    
    public ResponseEntity<Object> createNewCast(@RequestHeader("auth-token") String token,@RequestBody CastDto castDto){
        return this.castService.createNewCast(token,castDto);
    }

    //get all cast
    @GetMapping
    public ResponseEntity<Object> getAllCast(@RequestHeader("auth-token") String token){
        return this.castService.getAllCast(token);
    }

    //Get cast by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCastById(@PathVariable("id") String id,  @RequestHeader("auth-token") String token){
        return this.castService.getCastById(token,id);
    }

    //Delete cast with id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCastById(@PathVariable("id") String id,  @RequestHeader("auth-token") String token){
        return this.castService.deleteCastById(token,id);
    }

    //Update CAST with id
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCast(@RequestHeader("auth-token") String token,@PathVariable String id,@RequestBody Map<String,Object> fields){
        return this.castService.updateCast(token,id,fields);
    }
}
