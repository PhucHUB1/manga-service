package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.manga_req.CommentRequest;
import com.manganoob.mangaservice.dto.request.manga_req.UpdateCommentRequest;
import com.manganoob.mangaservice.dto.response.manga_res.CommentResponse;
import com.manganoob.mangaservice.entity.Chapters;
import com.manganoob.mangaservice.entity.Comment;
import com.manganoob.mangaservice.entity.Manga;
import com.manganoob.mangaservice.exception.AppException;
import com.manganoob.mangaservice.exception.ErrorCode;
import com.manganoob.mangaservice.mapper.CommentMapper;
import com.manganoob.mangaservice.repository.ChaptersRepository;
import com.manganoob.mangaservice.repository.CommentRepository;
import com.manganoob.mangaservice.repository.MangaRepository;
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
public class CommentService {
     CommentRepository commentRepository;
     CommentMapper commentMapper;
     MangaRepository mangaRepository;
     ChaptersRepository chaptersRepository;

    // Thêm comment cho Manga
    public CommentResponse addCommentToManga(UUID mangaId, CommentRequest request) {
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new AppException(ErrorCode.MANGA_NOT_FOUND));

        Comment comment = commentMapper.toComment(request);
        comment.setManga(manga);
        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toCommentResponse(savedComment);
    }

    // Thêm comment cho Chapter
    public CommentResponse addCommentToChapter(UUID chapterId, CommentRequest request) {
        Chapters chapter = chaptersRepository.findById(chapterId)
                .orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND));

        Comment comment = commentMapper.toComment(request);
        comment.setChapter(chapter);
        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toCommentResponse(savedComment);
    }

    public List<CommentResponse> getAllCommentsForManga(UUID mangaId) {
        List<Comment> comments = commentRepository.findAllByMangaId(mangaId);
        return comments.stream()
                .map(commentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> getAllCommentsForChapter(UUID chapterId) {
        List<Comment> comments = commentRepository.findAllByChapterId(chapterId);
        return comments.stream()
                .map(commentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    @PostAuthorize("returnObject.userId == authentication.principal.id")
    public CommentResponse updateComment(UUID commentId, UpdateCommentRequest request) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        commentMapper.updateComment(comment, request);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(updatedComment);
    }

    public void deleteComment(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        commentRepository.delete(comment);
    }
}
