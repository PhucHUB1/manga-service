package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.request.manga_req.CommentRequest;
import com.manganoob.identityservice.dto.request.manga_req.UpdateCommentRequest;
import com.manganoob.identityservice.dto.response.manga_res.CommentResponse;
import com.manganoob.identityservice.service.CommentService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    @PostMapping("/manga/create")
    public ApiResponse<CommentResponse> createCommentForManga(@RequestBody CommentRequest request) {
        CommentResponse commentResponse = commentService.addCommentToManga(request.getMangaId(), request);
        return ApiResponse.<CommentResponse>builder()
                .result(commentResponse)
                .build();
    }

    @PostMapping("/chapter/create")
    public ApiResponse<CommentResponse> createCommentForChapter(@RequestBody CommentRequest request) {
        CommentResponse commentResponse = commentService.addCommentToChapter(request.getChapterId(), request);
        return ApiResponse.<CommentResponse>builder()
                .result(commentResponse)
                .build();
    }

    @PutMapping("/{commentId}/update")
    public ApiResponse<CommentResponse> updateComment(@PathVariable UUID commentId, @RequestBody UpdateCommentRequest request) {
        CommentResponse updatedComment = commentService.updateComment(commentId, request);
        return ApiResponse.<CommentResponse>builder()
                .result(updatedComment)
                .build();
    }

    @DeleteMapping("/{commentId}/delete")
    public ApiResponse<Void> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ApiResponse.<Void>builder()
                .build();
    }

    @GetMapping("/manga/{mangaId}")
    public ApiResponse<List<CommentResponse>> getAllCommentsForManga(@PathVariable UUID mangaId) {
        List<CommentResponse> comments = commentService.getAllCommentsForManga(mangaId);
        return ApiResponse.<List<CommentResponse>>builder()
                .result(comments)
                .build();
    }

    @GetMapping("/chapter/{chapterId}")
    public ApiResponse<List<CommentResponse>> getAllCommentsForChapter(@PathVariable UUID chapterId) {
        List<CommentResponse> comments = commentService.getAllCommentsForChapter(chapterId);
        return ApiResponse.<List<CommentResponse>>builder()
                .result(comments)
                .build();
    }
}
