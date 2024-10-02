package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.request.manga_req.ArtRequest;
import com.manganoob.identityservice.dto.response.manga_res.ArtResponse;
import com.manganoob.identityservice.service.ArtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/art")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArtController {
    ArtService artService;


    @PostMapping("/create")
    public ApiResponse<ArtResponse> create(@RequestBody ArtRequest request) throws IOException {
        ArtResponse artResponse = artService.createArt(request);
        return ApiResponse.<ArtResponse>builder()
                .result(artResponse)
                .build();
    }

    @GetMapping("/{art_id}")
    public ApiResponse<ArtResponse> getArtById(@PathVariable UUID art_id) {
        ArtResponse artResponse = artService.getArtById(art_id);
            return ApiResponse.<ArtResponse>builder()
                    .result(artResponse)
                    .build();

    }

    @PutMapping("update/{art_id}")
    public ApiResponse<ArtResponse> updateArt(@PathVariable UUID art_id, @RequestBody ArtRequest request) throws IOException {
        ArtResponse artResponse = artService.updateArt(art_id, request);
        return ApiResponse.<ArtResponse>builder()
                .result(artResponse)
                .build();
    }

    @DeleteMapping("delete/{art_id}")
    public ApiResponse<Void> deleteArt(@PathVariable UUID art_id) {
        artService.deleteArt(art_id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/manga/{mangaId}")
    public ApiResponse<List<ArtResponse>> getArtsByMangaId(@PathVariable UUID mangaId) {
        List<ArtResponse> artResponses = artService.getAllArtsByMangaId(mangaId);
        return ApiResponse.<List<ArtResponse>>builder()
                .result(artResponses)
                .build();
    }
}
