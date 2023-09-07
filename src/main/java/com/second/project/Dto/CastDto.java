package com.second.project.Dto;

import java.util.ArrayList;
import java.util.List;

import com.second.project.Entity.ShortFilm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CastDto {
  
 
    private String cast_id;
    private String name;
    private String gender;
    private String image;
    private String role;
     private List<ShortFilm> shortfilm = new ArrayList<>();


    public CastDto( String name, String gender, String image,String role, List<ShortFilm> shortfilm) {
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.role = role;
        this.shortfilm = shortfilm;
    }

}
