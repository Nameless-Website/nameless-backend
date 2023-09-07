package com.second.project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.second.project.Entity.ShortFilm;

@Repository
public interface ShortFilmRepo extends JpaRepository<ShortFilm, String>{
    ShortFilm findByLink(String link);

    Object getShortFilmById(String id);
}
