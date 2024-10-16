package com.manganoob.mangaservice.mapper;

import com.manganoob.mangaservice.dto.request.VipPackageRequest;
import com.manganoob.mangaservice.dto.response.VipPackageResponse;
import com.manganoob.mangaservice.entity.VipPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VipPackageMapper {
    VipPackageResponse vipPackageToVipPackageResponse(VipPackage vipPackage);

    VipPackage vipPackageRequestToVipPackage(VipPackageRequest vipPackageRequestDTO);
}