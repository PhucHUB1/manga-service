package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, String> {
}
