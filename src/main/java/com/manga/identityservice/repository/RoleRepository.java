package com.manga.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manga.identityservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
