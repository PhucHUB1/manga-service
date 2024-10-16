package com.manganoob.mangaservice.mapper;

import org.mapstruct.Mapper;

import com.manganoob.mangaservice.dto.request.PermissionRequest;
import com.manganoob.mangaservice.dto.response.PermissionResponse;
import com.manganoob.mangaservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
