package com.manga.identityservice.mapper;

import com.manga.identityservice.dto.request.VipPackageRequest;
import com.manga.identityservice.dto.response.VipPackageResponse;
import com.manga.identityservice.entity.VipPackage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VipPackageMapper {

    VipPackageMapper INSTANCE = Mappers.getMapper(VipPackageMapper.class);

    VipPackageResponse vipPackageToVipPackageResponse(VipPackage vipPackage);

    VipPackage vipPackageRequestToVipPackage(VipPackageRequest vipPackageRequestDTO);
}
