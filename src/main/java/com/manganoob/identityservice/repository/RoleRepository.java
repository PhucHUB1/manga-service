package com.manganoob.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manganoob.identityservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
