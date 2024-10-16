package com.manganoob.mangaservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.manganoob.mangaservice.dto.request.user_req.UserCreationRequest;
import com.manganoob.mangaservice.dto.request.user_req.UserUpdateRequest;
import com.manganoob.mangaservice.dto.response.user_res.UserResponse;
import com.manganoob.mangaservice.entity.User;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "imageAvatar", target = "imageAvatar", qualifiedByName = "multipartFileToByteArray")
    @Mapping(source = "imageAvatar", target = "imageAvatarFilename", qualifiedByName = "multipartFileToFilename")
    User toUser(UserCreationRequest request, MultipartFile imageAvatar);


    @Mapping(source = "imageAvatarFilename", target = "imageAvatar")
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(source = "imageAvatar", target = "imageAvatar", qualifiedByName = "multipartFileToByteArray")
    void updateUser(@MappingTarget User user, UserUpdateRequest request);


    @Named("multipartFileToByteArray")
    default byte[] multipartFileToByteArray(MultipartFile file) {
        try {
            return (file != null && !file.isEmpty()) ? file.getBytes() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to byte[]", e);
        }
    }

    @Named("multipartFileToFilename")
    default String multipartFileToFilename(MultipartFile file) {
        return (file != null && !file.isEmpty()) ? file.getOriginalFilename() : null;
    }
}
