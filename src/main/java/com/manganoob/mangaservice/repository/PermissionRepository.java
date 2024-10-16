package com.manganoob.mangaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manganoob.mangaservice.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
