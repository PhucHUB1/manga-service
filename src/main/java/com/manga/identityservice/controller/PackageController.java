package com.manga.identityservice.controller;

import com.manga.identityservice.dto.request.VipPackageRequest;
import com.manga.identityservice.dto.response.VipPackageResponse;
import com.manga.identityservice.entity.VipPackage;
import com.manga.identityservice.mapper.VipPackageMapper;
import com.manga.identityservice.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class PackageController {

        @Autowired
        private VipService vipService;

        @Autowired
        private VipPackageMapper vipPackageMapper;

        @PostMapping("/vip-packages")
        public ResponseEntity<VipPackageResponse> createVipPackage(@RequestBody VipPackageRequest vipPackageRequestDTO) {
            // Chuyển từ DTO sang entity
            VipPackage vipPackage = vipPackageMapper.vipPackageRequestToVipPackage(vipPackageRequestDTO);

            // Lưu gói VIP vào cơ sở dữ liệu
            vipService.createVipPackage(vipPackage);

            // Chuyển từ entity sang DTO để trả về
            VipPackageResponse vipPackageResponseDTO = vipPackageMapper.vipPackageToVipPackageResponse(vipPackage);

            return ResponseEntity.ok(vipPackageResponseDTO);
        }
    }
