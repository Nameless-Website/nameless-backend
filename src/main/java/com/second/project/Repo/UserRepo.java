package com.second.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.second.project.Entity.User;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, String>{

    Optional<User> findOneByEmailAndPassword(String email, String password);
 
    User findByEmail(String email);

   
    
}
