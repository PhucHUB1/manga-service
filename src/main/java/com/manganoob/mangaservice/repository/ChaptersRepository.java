package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Chapters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChaptersRepository extends CrudRepository<Chapters, UUID> {
}
