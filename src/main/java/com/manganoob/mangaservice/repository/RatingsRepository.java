package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    List<Ratings> findByMangaId(UUID mangaId);
}
