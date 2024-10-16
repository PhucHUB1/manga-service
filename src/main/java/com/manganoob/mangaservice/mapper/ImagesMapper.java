package com.manganoob.mangaservice.mapper;

import com.manganoob.mangaservice.dto.request.manga_req.ImagesRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ImagesResponse;
import com.manganoob.mangaservice.entity.Images;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ImagesMapper {

    @Mapping(target = "chapter.id", source = "request.chapterId")
    @Mapping(target = "imageData", source = "imageData", qualifiedByName = "multipartFileToByteArray")
    @Mapping(target = "imageName", source = "imageData", qualifiedByName = "multipartFileToFilename")
    Images toImages(ImagesRequest request, MultipartFile imageData);

    @Mapping(target = "chapterId", source = "chapter.id")
    @Mapping(target = "imageName", source = "imageName")
    ImagesResponse toImagesResponse(Images images);


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