package com.manganoob.mangaservice.controller;

import com.manganoob.mangaservice.dto.request.ApiResponse;
import com.manganoob.mangaservice.dto.request.RatingsRequest;
import com.manganoob.mangaservice.dto.response.AverageRatingResponse;
import com.manganoob.mangaservice.dto.response.RatingsResponse;
import com.manganoob.mangaservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
public class RatingsController {

    @Autowired
    private RatingService ratingService;

    // 1. Create a new rating for a specific manga
    @PostMapping("/manga/{mangaId}")
    public ApiResponse<RatingsResponse> createRating(@PathVariable UUID mangaId, @RequestBody RatingsRequest request) {
        return ApiResponse.<RatingsResponse>builder()
                .result(ratingService.createRating(mangaId, request))
                .build();
    }

    // 3. Get all ratings for a specific manga
    @GetMapping("/manga/{mangaId}")
    public ApiResponse<List<RatingsResponse>> getRatingsByManga(@PathVariable UUID mangaId) {
        return ApiResponse.<List<RatingsResponse>>builder()
                .result(ratingService.getRatingsByManga(mangaId))
                .build();
    }


    // 5. Update a specific rating (only if it belongs to the current user)
    @PutMapping("/{ratingId}")
    public ApiResponse<RatingsResponse> updateRating(@PathVariable Long ratingId, @RequestBody RatingsRequest request) {
        return ApiResponse.<RatingsResponse>builder()
                .result(ratingService.updateRating(ratingId, request))
                .build();
    }

    // 6. Delete a specific rating (only if it belongs to the current user)
    @DeleteMapping("/{ratingId}")
    public ApiResponse<Void> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ApiResponse.<Void>builder().build(); // No result is needed for deletion
    }

    // 7. Get the average rating for a specific manga
    @GetMapping("/manga/{mangaId}/average")
    public ApiResponse<AverageRatingResponse> getAverageRating(@PathVariable UUID mangaId) {
        return ApiResponse.<AverageRatingResponse>builder()
                .result(ratingService.getAverageRatingByManga(mangaId))
                .build();
    }
}