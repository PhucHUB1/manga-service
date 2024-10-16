package com.manganoob.mangaservice.controller;

import com.manganoob.mangaservice.dto.request.ApiResponse;
import com.manganoob.mangaservice.dto.request.manga_req.ReplyCommentRequest;
import com.manganoob.mangaservice.dto.request.manga_req.UpdateReplyCommentRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ReplyCommentResponse;
import com.manganoob.mangaservice.service.ReplyCommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reply-comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplyCommentController {

    ReplyCommentService replyCommentService;

    @PostMapping("/create")
    public ApiResponse<ReplyCommentResponse> createReplyComment(@RequestBody ReplyCommentRequest request) {
        ReplyCommentResponse replyCommentResponse = replyCommentService.addReplyComment(request.getCommentId(), request);
        return ApiResponse.<ReplyCommentResponse>builder()
                .result(replyCommentResponse)
                .build();
    }

    @PutMapping("/{replyCommentId}/update")
    public ApiResponse<ReplyCommentResponse> updateReplyComment(@PathVariable UUID replyCommentId, @RequestBody UpdateReplyCommentRequest request) {
        ReplyCommentResponse updatedReplyComment = replyCommentService.updateReplyComment(replyCommentId, request);
        return ApiResponse.<ReplyCommentResponse>builder()
                .result(updatedReplyComment)
                .build();
    }

    @DeleteMapping("/{replyCommentId}/delete")
    public ApiResponse<Void> deleteReplyComment(@PathVariable UUID replyCommentId) {
        replyCommentService.deleteReplyComment(replyCommentId);
        return ApiResponse.<Void>builder()
                .build();
    }

    @GetMapping("/comment/{commentId}")
    public ApiResponse<List<ReplyCommentResponse>> getAllReplyCommentsByCommentId(@PathVariable UUID commentId) {
        List<ReplyCommentResponse> replyComments = replyCommentService.getAllReplyCommentsByCommentId(commentId);
        return ApiResponse.<List<ReplyCommentResponse>>builder()
                .result(replyComments)
                .build();
    }
}