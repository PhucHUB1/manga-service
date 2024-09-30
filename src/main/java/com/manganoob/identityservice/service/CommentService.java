package com.manganoob.identityservice.service;

import com.manganoob.identityservice.dto.request.manga_req.CommentRequest;
import com.manganoob.identityservice.dto.response.manga_res.CommentResponse;
import com.manganoob.identityservice.entity.Comment;
import com.manganoob.identityservice.mapper.CommentMapper;
import com.manganoob.identityservice.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
     CommentRepository commentRepository;
     CommentMapper commentMapper;

    public CommentResponse createComment(CommentRequest request) {
        Comment comment = commentMapper.toComment(request);
        comment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }




}
