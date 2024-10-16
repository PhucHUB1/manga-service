package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, String> {
}
