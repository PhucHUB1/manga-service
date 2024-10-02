package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Chapters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChaptersRepository extends CrudRepository<Chapters, UUID> {
}
