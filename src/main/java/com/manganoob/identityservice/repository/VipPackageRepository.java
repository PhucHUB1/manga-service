package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.VipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipPackageRepository extends JpaRepository<VipPackage, Integer> {
}