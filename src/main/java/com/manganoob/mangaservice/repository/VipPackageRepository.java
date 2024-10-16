package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.VipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipPackageRepository extends JpaRepository<VipPackage, Integer> {
}