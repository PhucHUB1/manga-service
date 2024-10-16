package com.manganoob.mangaservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.manganoob.mangaservice.dto.request.RoleRequest;
import com.manganoob.mangaservice.dto.response.RoleResponse;
import com.manganoob.mangaservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
