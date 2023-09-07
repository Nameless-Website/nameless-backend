package com.second.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.second.project.Entity.Cast;

public interface CastRepo extends JpaRepository<Cast, String> {
    
}
