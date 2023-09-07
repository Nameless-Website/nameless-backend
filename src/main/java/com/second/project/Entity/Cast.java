package com.second.project.Entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cast")
public class Cast {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "cast_id",updatable = false)
    private String cast_id;

    @Column(name = "name",length = 20)
    private String name;


    @Column(name = "gender",length = 20)
    private String gender;

    @Column(name = "image")
    private String image;
    
    @Column(name = "role",length = 20)

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    private String role;

    @ManyToMany(mappedBy = "cast", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cast")
    private List<ShortFilm> shortfilm = new ArrayList<>();

    public String getCast_id() {
        return this.cast_id;
    }

    public void setCast_id(String cast_id) {
        this.cast_id = cast_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ShortFilm> getShortfilm() {
        return this.shortfilm;
    }

    public void setShortfilm(List<ShortFilm> shortfilm) {
        this.shortfilm = shortfilm;
    }


   

    public Cast( String name, String gender, String image, String role, List<ShortFilm> shortfilm) {
      
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.role = role;
        this.shortfilm = shortfilm;
    }



}
