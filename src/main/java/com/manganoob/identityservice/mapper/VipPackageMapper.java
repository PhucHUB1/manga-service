package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.VipPackageRequest;
import com.manganoob.identityservice.dto.response.VipPackageResponse;
import com.manganoob.identityservice.entity.VipPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VipPackageMapper {
    VipPackageResponse vipPackageToVipPackageResponse(VipPackage vipPackage);

    VipPackage vipPackageRequestToVipPackage(VipPackageRequest vipPackageRequestDTO);
}