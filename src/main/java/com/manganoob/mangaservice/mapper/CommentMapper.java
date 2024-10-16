package com.manganoob.mangaservice.mapper;

import com.manganoob.mangaservice.dto.request.manga_req.CommentRequest;
import com.manganoob.mangaservice.dto.request.manga_req.UpdateCommentRequest;
import com.manganoob.mangaservice.dto.response.manga_res.CommentResponse;
import com.manganoob.mangaservice.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "chapterId", target = "chapter.id")
    @Mapping(source = "mangaId", target = "manga.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Comment toComment(CommentRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "manga.id", target = "mangaId")
    @Mapping(source = "chapter.id", target = "chapterId")
    @Mapping(source = "replyComments", target = "replyComments")
    CommentResponse toCommentResponse(Comment comment);

    @Mapping(target = "updatedAt", ignore = true)
    void updateComment(@MappingTarget Comment comment,UpdateCommentRequest request);
}
