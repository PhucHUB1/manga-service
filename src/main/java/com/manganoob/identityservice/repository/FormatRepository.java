package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends JpaRepository<Format, String> {
}
