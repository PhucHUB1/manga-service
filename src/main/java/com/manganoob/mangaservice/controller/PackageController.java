package com.manganoob.mangaservice.controller;

import com.manganoob.mangaservice.dto.request.ApiResponse;
import com.manganoob.mangaservice.dto.request.VipPackageRequest;
import com.manganoob.mangaservice.dto.response.VipPackageResponse;
import com.manganoob.mangaservice.entity.VipPackage;
import com.manganoob.mangaservice.mapper.VipPackageMapper;
import com.manganoob.mangaservice.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class PackageController {

    @Autowired
    private VipService vipService;

    @Autowired
    private VipPackageMapper vipPackageMapper;

    @PostMapping("/create-package")
    public ApiResponse<VipPackageResponse> createVipPackage(@RequestBody VipPackageRequest vipPackageRequestDTO) {
        VipPackage vipPackage = vipPackageMapper.vipPackageRequestToVipPackage(vipPackageRequestDTO);

        vipService.createVipPackage(vipPackage);

        VipPackageResponse vipPackageResponse = vipPackageMapper.vipPackageToVipPackageResponse(vipPackage);

        return ApiResponse.<VipPackageResponse>builder()
                .result(vipPackageResponse)
                .build();
    }
    @GetMapping("/all-packages")
    public ApiResponse<List<VipPackageResponse>> getAllPackage() {
        return ApiResponse.<List<VipPackageResponse>>builder()
                .result(vipService.getAllPackages())
                .build();
    }
}