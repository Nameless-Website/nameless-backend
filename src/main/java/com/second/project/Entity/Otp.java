package com.second.project.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "otp_id",length = 45,updatable = false)
    private String category_id;

    @Column(name = "value",length = 32,updatable = false)
    private Integer value;


    @Column(name = "email",length = 32,unique = true,updatable = false)
    private String email;


    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Otp( Integer value, String email) {
     
        this.value = value;
        this.email = email;
    }


  

}
