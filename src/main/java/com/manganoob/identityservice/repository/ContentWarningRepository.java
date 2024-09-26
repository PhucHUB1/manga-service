package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.ContentWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentWarningRepository extends JpaRepository<ContentWarning, String> {
}
