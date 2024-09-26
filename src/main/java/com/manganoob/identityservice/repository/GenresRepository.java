package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, String> {
}
