package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtRepository extends JpaRepository<Art, UUID> {

    List<Art> findByMangaId(UUID mangaId);
}