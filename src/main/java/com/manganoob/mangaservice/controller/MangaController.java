package com.manganoob.mangaservice.controller;

import com.manganoob.mangaservice.dto.request.ApiResponse;
import com.manganoob.mangaservice.dto.request.manga_req.MangaRequest;
import com.manganoob.mangaservice.dto.response.manga_res.MangaResponse;
import com.manganoob.mangaservice.service.MangaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/manga")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MangaController {
    MangaService mangaService;

    @PostMapping("/create")
    public ApiResponse<MangaResponse> createManga(@RequestBody MangaRequest request) {
        return ApiResponse.<MangaResponse>builder()
                .result(mangaService.createManga(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MangaResponse> getMangaById(@PathVariable UUID id) {
        return ApiResponse.<MangaResponse>builder()
                .result(mangaService.getMangaById(id))
                .build();
    }

    @GetMapping("/all-manga")
    public ApiResponse<List<MangaResponse>> getAllManga() {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(mangaService.getAllManga())
                .build();
    }
    @DeleteMapping("/delete/{manga_id}")
    ApiResponse<Void> delete(@PathVariable UUID manga_id) {
        mangaService.deleteManga(manga_id);
        return ApiResponse.<Void>builder().build();
    }

}
