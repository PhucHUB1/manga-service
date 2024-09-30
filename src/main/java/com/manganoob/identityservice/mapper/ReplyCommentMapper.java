package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.ReplyCommentRequest;
import com.manganoob.identityservice.dto.response.manga_res.ReplyCommentResponse;
import com.manganoob.identityservice.entity.ReplyComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReplyCommentMapper {

    @Mapping(source = "userId", target = "user_reply_comment.id")
    @Mapping(source = "commentId", target = "comment.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ReplyComment toReplyComment(ReplyCommentRequest request);

    @Mapping(source = "user_reply_comment.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "comment.content", target = "commentContent")
    ReplyCommentResponse toReplyCommentResponse(ReplyComment replyComment);

}
