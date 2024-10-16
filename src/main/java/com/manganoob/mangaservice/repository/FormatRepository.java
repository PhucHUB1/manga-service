package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends JpaRepository<Format, String> {
}
