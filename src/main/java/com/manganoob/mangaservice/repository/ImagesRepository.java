package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagesRepository extends JpaRepository<Images, UUID> {
    List<Images> findAllByChapterId(UUID chapterId);
}
