package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.AuthorRequest;
import com.manganoob.identityservice.dto.response.manga_res.AuthorResponse;
import com.manganoob.identityservice.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "authorAvatar", source = "authorAvatar", qualifiedByName = "multipartFileToByteArray")
    @Mapping(target = "authorAvatarName", source = "authorAvatar",qualifiedByName = "multipartFileToFilename")
    Author toEntity(AuthorRequest request);

    @Mapping(target = "authorAvatar", source = "authorAvatarName")
    AuthorResponse toResponse(Author author);


    @Named("multipartFileToByteArray")
    static byte[] multipartFileToByteArray(MultipartFile file) {
        try {
            return file != null ? file.getBytes() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert multipart file to byte array", e);
        }
    }
    @Named("multipartFileToFilename")
    default String multipartFileToFilename(MultipartFile file) {
        return (file != null && !file.isEmpty()) ? file.getOriginalFilename() : null;
    }
}