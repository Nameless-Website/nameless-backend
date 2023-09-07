package com.second.project.Dto;

import java.util.List;

import com.second.project.Entity.Cast;
import com.second.project.Entity.ShortFilmImages;
import java.util.Objects;

public class ShortFilmDto {
    private String id;
    private String name;
    private String link;
    private String thumbnail;
    private List<ShortFilmImages> images;
    private List<Cast> cast ;


    public ShortFilmDto() {
    }

    public ShortFilmDto(String name, String link, String thumbnail, List<ShortFilmImages> images, List<Cast> cast) {

        this.name = name;
        this.link = link;
        this.thumbnail = thumbnail;
        this.images = images;
        this.cast = cast;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<ShortFilmImages> getImages() {
        return this.images;
    }

    public void setImages(List<ShortFilmImages> images) {
        this.images = images;
    }

    public List<Cast> getCast() {
        return this.cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

 




    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", link='" + getLink() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", images='" + getImages() + "'" +
            ", cast='" + getCast() + "'" +
            "}";
    }

}
