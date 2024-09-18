package com.manga.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.manga.identityservice.dto.request.RoleRequest;
import com.manga.identityservice.dto.response.RoleResponse;
import com.manga.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
