package com.second.project.Entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Entity
@Table(name="shortFilm")
public class ShortFilm {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id",updatable = false)
    private String id;

    @Column(name = "name",length =255 ,nullable = false,unique = false)
    private String name;
 
    @Column(name = "link",length = 255,nullable = false,unique = true)
    private String link;

    @Column(name = "thumbnail",length = 255,nullable = false)
    private String thumbnail;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="shortFilmImages_id",referencedColumnName = "id" )
    private List<ShortFilmImages> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("shortFilm")
    @JoinTable(name="shortfilm_cast",joinColumns = @JoinColumn(name="shortfilm_id"),inverseJoinColumns =@JoinColumn(name= "cast_id"))
    private List<Cast> cast;


    // public ShortFilm() {
    // }

    public ShortFilm(String name, String link, String thumbnail, List<ShortFilmImages> images, List<Cast> cast) {

        this.name = name;
        this.link = link;
        this.thumbnail = thumbnail;
        this.images = images;
        this.cast = cast;
    }




}
