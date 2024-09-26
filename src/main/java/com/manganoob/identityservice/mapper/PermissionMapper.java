package com.manganoob.identityservice.mapper;

import org.mapstruct.Mapper;

import com.manganoob.identityservice.dto.request.PermissionRequest;
import com.manganoob.identityservice.dto.response.PermissionResponse;
import com.manganoob.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
