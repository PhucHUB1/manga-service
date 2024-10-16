package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.manga_req.ReplyCommentRequest;
import com.manganoob.mangaservice.dto.request.manga_req.UpdateReplyCommentRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ReplyCommentResponse;
import com.manganoob.mangaservice.entity.Comment;
import com.manganoob.mangaservice.entity.ReplyComment;
import com.manganoob.mangaservice.exception.AppException;
import com.manganoob.mangaservice.exception.ErrorCode;
import com.manganoob.mangaservice.mapper.ReplyCommentMapper;
import com.manganoob.mangaservice.repository.CommentRepository;
import com.manganoob.mangaservice.repository.ReplyCommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplyCommentService {
     CommentRepository commentRepository;
     ReplyCommentMapper replyCommentMapper;
     ReplyCommentRepository replyCommentRepository;


    public ReplyCommentResponse addReplyComment(UUID commentId, ReplyCommentRequest request) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        ReplyComment replyComment = replyCommentMapper.toReplyComment(request);
        replyComment.setComment(comment);


        ReplyComment savedReplyComment = replyCommentRepository.save(replyComment);

        return replyCommentMapper.toReplyCommentResponse(savedReplyComment);
    }

    @PostAuthorize("returnObject.userId == authentication.principal.id")
    public ReplyCommentResponse updateReplyComment(UUID replyCommentId, UpdateReplyCommentRequest request) {
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        replyCommentMapper.updateReplyComment(replyComment, request);
        ReplyComment updatedReplyComment = replyCommentRepository.save(replyComment);
        return replyCommentMapper.toReplyCommentResponse(updatedReplyComment);
    }

    public List<ReplyCommentResponse> getAllReplyCommentsByCommentId(UUID commentId) {
        List<ReplyComment> replyComments = replyCommentRepository.findAllByCommentId(commentId);
        return replyComments.stream()
                .map(replyCommentMapper::toReplyCommentResponse)
                .collect(Collectors.toList());
    }

    public void deleteReplyComment(UUID replyCommentId) {
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        replyCommentRepository.delete(replyComment);
    }
}
