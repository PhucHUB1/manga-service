package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.response.manga_res.MangaResponse;
import com.manganoob.identityservice.service.SearchMangaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/manga/search")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MangaSearchController {
    SearchMangaService searchMangaService;


    @GetMapping("/genres")
    public ApiResponse<List<MangaResponse>> searchByGenres(@RequestParam Set<String> genres) {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.searchByGenres(genres))
                .build();
    }

    // Search by format
    @GetMapping("/format")
    public ApiResponse<List<MangaResponse>> searchByFormat(@RequestParam Set<String> formats) {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.searchByFormat(formats))
                .build();
    }

    // Search by theme
    @GetMapping("/theme")
    public ApiResponse<List<MangaResponse>> searchByTheme(@RequestParam Set<String> themes) {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.searchByTheme(themes))
                .build();
    }

    // Search by content warning
    @GetMapping("/content-warning")
    public ApiResponse<List<MangaResponse>> searchByContentWarning(@RequestParam Set<String> contentWarnings) {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.searchByContentWarning(contentWarnings))
                .build();
    }

    // Search by author name
    @GetMapping("/author")
    public ApiResponse<List<MangaResponse>> searchByAuthorName(@RequestParam String authorName) {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.searchByAuthorName(authorName))
                .build();
    }

    // Get latest manga
    @GetMapping("/latest")
    public ApiResponse<List<MangaResponse>> getLatestManga() {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.getLatestManga())
                .build();
    }

    // Get oldest manga
    @GetMapping("/oldest")
    public ApiResponse<List<MangaResponse>> getOldestManga() {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.getOldestManga())
                .build();
    }

    // Get newly updated manga
    @GetMapping("/latest-update")
    public ApiResponse<List<MangaResponse>> getNewlyUpdatedManga() {
        return ApiResponse.<List<MangaResponse>>builder()
                .result(searchMangaService.getNewlyUpdatedManga())
                .build();
    }
    // A-Z
    @GetMapping("/sort/asc")
    public ApiResponse<List<MangaResponse>> getAllMangaSortedByTitleAsc() {
        List<MangaResponse> mangaResponses = searchMangaService.getAllMangaSortedByTitleAsc();
        return ApiResponse.<List<MangaResponse>>builder()
                .result(mangaResponses)
                .build();
    }
    // Z-A
    @GetMapping("/sort/desc")
    public ApiResponse<List<MangaResponse>> getAllMangaSortedByTitleDesc() {
        List<MangaResponse> mangaResponses = searchMangaService.getAllMangaSortedByTitleDesc();
        return ApiResponse.<List<MangaResponse>>builder()
                .result(mangaResponses)
                .build();
    }
}
