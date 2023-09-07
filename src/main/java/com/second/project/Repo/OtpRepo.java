package com.second.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.second.project.Entity.Otp;

public interface OtpRepo extends JpaRepository<Otp,String>{

    Otp findByEmail(String email);
    
}
