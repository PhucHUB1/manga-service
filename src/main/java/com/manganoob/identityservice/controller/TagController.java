package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.request.manga_req.ContentWarningRequest;
import com.manganoob.identityservice.dto.request.manga_req.FormatRequest;
import com.manganoob.identityservice.dto.request.manga_req.GenresRequest;
import com.manganoob.identityservice.dto.request.manga_req.ThemeRequest;
import com.manganoob.identityservice.dto.response.manga_res.ContentWarningResponse;
import com.manganoob.identityservice.dto.response.manga_res.FormatResponse;
import com.manganoob.identityservice.dto.response.manga_res.GenresResponse;
import com.manganoob.identityservice.dto.response.manga_res.ThemeResponse;
import com.manganoob.identityservice.service.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {
    TagService tagService;

//genres
    @PostMapping("/genres/create")
    public ApiResponse<GenresResponse> createGenres(@RequestBody GenresRequest request) {
        return ApiResponse.<GenresResponse>builder()
                .result(tagService.createGenres(request))
                .build();

    }
    @GetMapping("/all-genres")
    public ApiResponse<List<GenresResponse>> getAllGenres() {
        return ApiResponse.<List<GenresResponse>>builder()
                .result(tagService.getAllGenres())
                .build();
    }
    @DeleteMapping("delete/genres/{genres_name}")
    public ApiResponse<Void> deleteGenres(@PathVariable String genres_name) {
        tagService.deleteGenres(genres_name);
        return ApiResponse.<Void>builder().build();
    }
//theme
    @PostMapping("/theme/create")
    public ApiResponse<ThemeResponse> createTheme(@RequestBody ThemeRequest request) {
    return ApiResponse.<ThemeResponse>builder()
            .result(tagService.createTheme(request))
            .build();
    }
    @GetMapping("/all-theme")
    public ApiResponse<List<ThemeResponse>> getAllThemes() {
        return ApiResponse.<List<ThemeResponse>>builder()
                .result(tagService.getAllThemes())
                .build();
    }
    @DeleteMapping("delete/theme/{theme_name}")
    public ApiResponse<Void> deleteTheme(@PathVariable String theme_name) {
        tagService.deleteTheme(theme_name);
        return ApiResponse.<Void>builder().build();
    }
// format
    @PostMapping("/format/create")
    public ApiResponse<FormatResponse> createFormat(@RequestBody FormatRequest request) {
    return ApiResponse.<FormatResponse>builder()
            .result(tagService.createFormat(request))
            .build();
    }

    @GetMapping("/all-format")
    public ApiResponse<List<FormatResponse>> getAllFormats() {
        return ApiResponse.<List<FormatResponse>>builder()
                .result(tagService.getAllFormats())
                .build();
    }

    @DeleteMapping("delete/format/{format_name}")
    public ApiResponse<Void> deleteFormat(@PathVariable String format_name) {
        tagService.deleteFormat(format_name);
        return ApiResponse.<Void>builder().build();
    }
//contentWarning
    @PostMapping("/contentWarning/create")
    public ApiResponse<ContentWarningResponse> createContentWarning(@RequestBody ContentWarningRequest request) {
    return ApiResponse.<ContentWarningResponse>builder()
            .result(tagService.createContentWarning(request))
            .build();
}

    @GetMapping("/all-contentWarning")
    public ApiResponse<List<ContentWarningResponse>> getAllContentWarnings() {
        return ApiResponse.<List<ContentWarningResponse>>builder()
                .result(tagService.getAllContentWarnings())
                .build();
    }

    @DeleteMapping("/delete/contentWarning/{content_name}")
    public ApiResponse<Void> deleteContentWarning(@PathVariable String content_name) {
        tagService.deleteContentWarning(content_name);
        return ApiResponse.<Void>builder().build();
    }
}
