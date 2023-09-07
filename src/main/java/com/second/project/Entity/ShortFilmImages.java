package com.second.project.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="shortFilmImages")
public class ShortFilmImages {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id",length = 45,updatable = false)
    private String id;
    @Column(name = "link")
    private String link;

    public ShortFilmImages() {
    }

    public ShortFilmImages(String id, String link) {
        this.link = link;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

  

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
