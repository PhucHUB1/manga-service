package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.CommentRequest;
import com.manganoob.identityservice.dto.response.manga_res.CommentResponse;
import com.manganoob.identityservice.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "userId", target = "user_comment.id")
    @Mapping(source = "chapterId", target = "chapter_comment.id")
    @Mapping(source = "mangaId", target = "manga_comment.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment toComment(CommentRequest request);

    @Mapping(source = "user_comment.id", target = "userId")
    @Mapping(source = "manga_comment.id", target = "mangaId")
    @Mapping(source = "chapter_comment.id", target = "chapterId")
    @Mapping(source = "replyComments", target = "replyComments")
    CommentResponse toCommentResponse(Comment comment);
}
