package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.ContentWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentWarningRepository extends JpaRepository<ContentWarning, String> {
}
