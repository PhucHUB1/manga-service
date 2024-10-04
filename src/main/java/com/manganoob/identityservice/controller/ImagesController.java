package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.request.manga_req.ImagesRequest;
import com.manganoob.identityservice.dto.response.manga_res.ImagesResponse;
import com.manganoob.identityservice.service.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImagesController {
    ImageService imageService;

    @PostMapping("/post")
    public ApiResponse<ImagesResponse> create(@RequestBody ImagesRequest request) throws IOException {
        return ApiResponse.<ImagesResponse>builder()
                .result(imageService.postImage(request))
                .build();
    }
    @GetMapping("/chapter/{chapterId}")
    public ApiResponse<List<ImagesResponse>> getAllByChapter(@PathVariable UUID chapterId) {
        return ApiResponse.<List<ImagesResponse>>builder()
                .result(imageService.getAllByChapterId(chapterId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ImagesResponse> getById(@PathVariable UUID id) {
        return ApiResponse.<ImagesResponse>builder()
                .result(imageService.getById(id))
                .build();
    }
}
