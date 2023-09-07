package com.second.project.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "category_id",length = 45,updatable = false)
    private String category_id;

    @Column(name = "name",length = 32,updatable = false)
    private String name;


    public Categories() {
    }

    public Categories(String category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public String getcategory_id() {
        return this.category_id;
    }

    public void setcategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories category_id(String category_id) {
        setcategory_id(category_id);
        return this;
    }

    public Categories name(String name) {
        setName(name);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " category_id='" + getcategory_id() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
}
