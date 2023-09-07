package com.second.project.Services.Impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.sqm.CastType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.second.project.Dto.CastDto;
import com.second.project.Entity.Cast;
import com.second.project.Entity.User;
import com.second.project.Repo.CastRepo;
import com.second.project.Response.ResponseHandler;
import com.second.project.Services.interfaces.CastService;
import com.second.project.Util.AuthUtil;
import com.second.project.Util.JwtUtil;

@Service
public class CastServiceImpl implements CastService{


    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private CastRepo castRepo;

    @Autowired
    private JwtUtil jwtUtil;
    private Field field;
    @Override
    public ResponseEntity<Object> getAllCast(String token) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)|| authUtil.isUser(token)){
            return ResponseHandler.responseBuilder("All short films are here", HttpStatus.OK, castRepo.findAll());
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
        
    }

    @Override
    public ResponseEntity<Object> createNewCast(String token, CastDto castDto) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)){
           Cast cast = new Cast(
            castDto.getName(),
            castDto.getGender(),
            castDto.getImage(),
            castDto.getRole(),
            castDto.getShortfilm()
           );
           castRepo.save(cast);
           return ResponseHandler.responseBuilder("New Cast Created", HttpStatus.OK, cast);
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
    }

    @Override
    public ResponseEntity<Object> getCastById(String token, String id) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token) ||authUtil.isUser(token) ){
           Optional<Cast> cast = castRepo.findById(id);
           if(cast.isPresent()){
            return ResponseHandler.responseBuilder("Here is the cast details", HttpStatus.OK, cast);
           }
           else{
            return ResponseHandler.responseBuilder("Cast Not Found", HttpStatus.NOT_FOUND, null);
           }
            
         }
         else{
             return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
         }
    }

    @Override
    public ResponseEntity<Object> deleteCastById(String token, String id) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)){
           
            Cast cast = castRepo.findById(id).get();
            castRepo.delete(cast);
            return ResponseHandler.responseBuilder("Cast Deleted", HttpStatus.OK, null);
         }
         else{
             return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
         }
    }

    @Override
    public ResponseEntity<Object> updateCast(String token, String id, Map<String, Object> fields) {
        // TODO Auto-generated method stub
        if (authUtil.isAdmin(token)){
            Cast cast = castRepo.findById(id).get();
           

                fields.forEach((key, value) -> {
                   
              Field field= ReflectionUtils.findField(Cast.class, key);
              field.setAccessible(true);
       
                 

                        ReflectionUtils.setField(field, cast, value);
                  

                });

                castRepo.save(cast);
                return ResponseHandler.responseBuilder("Cast updated", HttpStatus.OK, cast);

            
        
        } else {
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.UNAUTHORIZED, null);
        }
    }
    
}
