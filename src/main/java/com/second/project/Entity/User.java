package com.second.project.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "userId",length = 45,updatable = false)
    private String userId;

    @Column(name = "username",length =255 ,nullable = false,unique = false,updatable = true)
    private String username;
 
    @Column(name = "email",length = 255,nullable = false,unique = true)
    private String email;

    @Column(name = "password",length = 255,nullable = false)
    private String password;

    @Column(name = "role",length = 255,nullable = false)
    private String role;


    public User(String userId, String username, String email, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role=role;
        this.password = password;

    
    }

    public User() {
    }


    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

}
