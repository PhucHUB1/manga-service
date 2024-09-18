package com.manga.identityservice.repository;

import com.manga.identityservice.entity.VipPackage;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VipPackageRepository extends JpaRepository<VipPackage, Integer> {
}
