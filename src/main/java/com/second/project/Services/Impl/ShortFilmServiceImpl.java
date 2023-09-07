package com.second.project.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import com.second.project.Dto.ShortFilmDto;
import com.second.project.Dto.UserDto;
import com.second.project.Entity.Cast;
import com.second.project.Entity.ShortFilm;
import com.second.project.Repo.CastRepo;
import com.second.project.Repo.ShortFilmRepo;
import com.second.project.Response.ResponseHandler;
import com.second.project.Services.interfaces.ShortFilmService;
import com.second.project.Util.AuthUtil;
import com.second.project.Util.JwtUtil;

@Service
public class ShortFilmServiceImpl implements ShortFilmService {

    @Autowired
    ShortFilmRepo shortFilmRepo;
    @Autowired
    CastRepo castRepo;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthUtil authUtil;

    @Override
    public ResponseEntity<Object> addShortFilm(String token, ShortFilmDto shortFilmDto) {

        ShortFilm shortFilm = new ShortFilm(
            shortFilmDto.getName(),
            shortFilmDto.getLink(),
            shortFilmDto.getThumbnail(),
            shortFilmDto.getImages(),
           shortFilmDto.getCast()
            );


            if(authUtil.isAdmin(token)){

                ShortFilm sFilm = shortFilmRepo.findByLink(shortFilmDto.getLink());
                if(sFilm==null){
                    shortFilmRepo.save(shortFilm);
                    return ResponseHandler.responseBuilder("Short film added", HttpStatus.OK, shortFilm);
                }
                else{
                    return ResponseHandler.responseBuilder("Short film already exists", HttpStatus.ALREADY_REPORTED, null);
                }
            }
            else{
                return ResponseHandler.responseBuilder("You are not an admin", HttpStatus.UNAUTHORIZED, null);
            }
           
      
    }

    @Override
    public ResponseEntity<Object> auth(String token) {

            if(authUtil.isAdmin(token)){

                return ResponseHandler.responseBuilder("Authorized", HttpStatus.OK, null);
            }
            else{
                return ResponseHandler.responseBuilder("Not auth", HttpStatus.OK, null);
            }
    }

    @Override
    public ResponseEntity<Object> getAllShortFilm(String token) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)|| authUtil.isUser(token)){
            return ResponseHandler.responseBuilder("All short films are here", HttpStatus.OK, shortFilmRepo.findAll());
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
        
    }

    @Override
    public ResponseEntity<Object> deleteShortFilmById(String token, String id) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)){
            shortFilmRepo.deleteById(id);
            return ResponseHandler.responseBuilder("Short Film Deleted", HttpStatus.OK, null);
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
    }

    @Override
    public ResponseEntity<Object> getShortFilmById(String token, String id) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)|| authUtil.isUser(token)){
            
            return ResponseHandler.responseBuilder("Here is the short film", HttpStatus.OK, shortFilmRepo.findById(id));
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
    }

  

    // @Override
    // public ResponseEntity<Object> updateShortFilmById(String token, String id, ShortFilmDto shortFilmDto) {
        // TODO Auto-generated method stub
    //     if(authUtil.isAdmin(token)){
    //         ShortFilm sFilm = shortFilmRepo.findById(id).get();
    //         sFilm.setLink(shortFilmDto.getLink());
    //         sFilm.setCast(shortFilmDto.getCast());
    //         sFilm.setImages(shortFilmDto.getImages());
    //         sFilm.setName(shortFilmDto.getName());
    //         sFilm.setThumbnail(shortFilmDto.getThumbnail());
    //         shortFilmRepo.save(sFilm);
    //         return ResponseHandler.responseBuilder("Short film updated", HttpStatus.OK, shortFilmRepo.findById(id));
    //     }
    //     else{
    //         return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
    //     }
    // }


   
   
    @Override
    public ResponseEntity<Object> addCastToShortFilm(String token, String shortFilmId, String castId) {
        // TODO Auto-generated method stub
        if(authUtil.isAdmin(token)){
            List<Cast> castSet = null;
            ShortFilm sFilm = shortFilmRepo.findById(shortFilmId).get();
            Cast cast = castRepo.findById(castId).get();
            if(sFilm ==null){
                return ResponseHandler.responseBuilder("short film not found", HttpStatus.NOT_FOUND, null);
            }

            if(sFilm ==null){
                return ResponseHandler.responseBuilder("cast not found", HttpStatus.NOT_FOUND, null);
            }

           if(sFilm!=null && cast!=null){
                castSet = sFilm.getCast();
                castSet.add(cast);
                sFilm.setCast(castSet);
                shortFilmRepo.save(sFilm);
                return ResponseHandler.responseBuilder("cast added to short film", HttpStatus.OK, sFilm);
            }
            else{
                return ResponseHandler.responseBuilder("short film or cast not found", HttpStatus.NOT_FOUND, sFilm);
            }


            
        }
        else{
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.OK, null);
        }
       
    }

    @Override
    public ResponseEntity<Object> updateShortFilmById(String token, String id, Map<String, Object> fields) {
        if (authUtil.isAdmin(token)){
            ShortFilm shortFilm = shortFilmRepo.findById(id).get();
           

                fields.forEach((key, value) -> {
                   
              Field field= ReflectionUtils.findField(ShortFilm.class, key);
              field.setAccessible(true);
       
                 

                        ReflectionUtils.setField(field, shortFilm, value);
                  

                });

                shortFilmRepo.save(shortFilm);
                return ResponseHandler.responseBuilder("ShortFilm updated", HttpStatus.OK, shortFilm);

            
        
        } else {
            return ResponseHandler.responseBuilder("Not authenticated", HttpStatus.UNAUTHORIZED, null);
        }
    }


}
