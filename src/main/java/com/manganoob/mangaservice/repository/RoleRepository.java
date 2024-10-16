package com.manganoob.mangaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manganoob.mangaservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
