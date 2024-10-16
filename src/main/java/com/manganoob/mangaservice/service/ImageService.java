package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.manga_req.ImagesRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ImagesResponse;
import com.manganoob.mangaservice.entity.Images;
import com.manganoob.mangaservice.exception.AppException;
import com.manganoob.mangaservice.exception.ErrorCode;
import com.manganoob.mangaservice.mapper.ImagesMapper;
import com.manganoob.mangaservice.repository.ChaptersRepository;
import com.manganoob.mangaservice.repository.ImagesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageService {
    ImagesMapper imagesMapper;
    ChaptersRepository chaptersRepository;
    ImagesRepository imagesRepository;

    public ImagesResponse postImage(ImagesRequest request, MultipartFile imageData) throws IOException {
        Images images = imagesMapper.toImages(request,imageData);
        if (imageData != null && !imageData.isEmpty()) {
            try {
                images.setImageData(imageData.getBytes());
                images.setImageName(imageData.getOriginalFilename());
            } catch (IOException e) {
                throw new AppException(ErrorCode.AVATAR_UPLOAD_FAILED);
            }
        }

        try {
            images = imagesRepository.save(images);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.EXISTED);
        }
        return imagesMapper.toImagesResponse(images);
    }


    public List<ImagesResponse> getAllByChapterId(UUID chapterId) {
        List<Images> imagesList = imagesRepository.findAllByChapterId(chapterId);
        return imagesList.stream()
                .map(imagesMapper::toImagesResponse)
                .collect(Collectors.toList());
    }

    public ImagesResponse getById(UUID id) {
        return imagesRepository.findById(id)
                .map(imagesMapper::toImagesResponse)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }
}
