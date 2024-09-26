package com.manganoob.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.manganoob.identityservice.dto.request.RoleRequest;
import com.manganoob.identityservice.dto.response.RoleResponse;
import com.manganoob.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
