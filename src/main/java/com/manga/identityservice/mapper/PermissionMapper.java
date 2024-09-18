package com.manga.identityservice.mapper;

import org.mapstruct.Mapper;

import com.manga.identityservice.dto.request.PermissionRequest;
import com.manga.identityservice.dto.response.PermissionResponse;
import com.manga.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
