package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.ArtRequest;
import com.manganoob.identityservice.dto.response.manga_res.ArtResponse;
import com.manganoob.identityservice.entity.Art;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ArtMapper {

    @Mapping(target = "Art", source = "imageArt", qualifiedByName = "multipartFileToByteArray")
    @Mapping(target = "artName", source = "imageArt", qualifiedByName = "multipartFileToFilename")
    @Mapping(target = "manga.id",source = "request.mangaId")
    @Mapping(target = "user.id",source = "request.userId")
    Art toEntity(ArtRequest request, MultipartFile imageArt);

    @Mapping(target = "imageArt", source = "artName")
    @Mapping(target = "mangaId", source = "manga.id")
    @Mapping(target = "userId", source = "user.id")
    ArtResponse toResponse(Art art);

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