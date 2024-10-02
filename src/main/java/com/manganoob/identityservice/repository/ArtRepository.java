package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Art;
import com.manganoob.identityservice.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtRepository extends JpaRepository<Art, UUID> {

    List<Art> findByMangaId(UUID mangaId);
}