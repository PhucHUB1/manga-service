package com.manganoob.identityservice.service;

import com.manganoob.identityservice.dto.request.manga_req.ImagesRequest;
import com.manganoob.identityservice.dto.response.manga_res.ImagesResponse;
import com.manganoob.identityservice.entity.Chapters;
import com.manganoob.identityservice.entity.Images;
import com.manganoob.identityservice.mapper.ImagesMapper;
import com.manganoob.identityservice.repository.ChaptersRepository;
import com.manganoob.identityservice.repository.ImagesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageService {
    ImagesMapper imagesMapper;
    ChaptersRepository chaptersRepository;
    ImagesRepository imagesRepository;

    public ImagesResponse postImage(ImagesRequest request) throws IOException {
        Images image = imagesMapper.toImages(request);

        Optional<Chapters> chapterOpt = chaptersRepository.findById(request.getChapterId());
        if (chapterOpt.isEmpty()) {
            throw new RuntimeException("Chapter not found with id: " + request.getChapterId());
        }
        image.setChapter(chapterOpt.get());

        if (request.getImageData() != null && !request.getImageData().isEmpty()) {
            image.setImageData(request.getImageData().getBytes());
            image.setImageName(request.getImageData().getOriginalFilename());
        }

        Images savedImage = imagesRepository.save(image);
        return imagesMapper.toImagesResponse(savedImage);
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
