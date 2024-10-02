package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MangaRepository extends JpaRepository<Manga, UUID> {
}
