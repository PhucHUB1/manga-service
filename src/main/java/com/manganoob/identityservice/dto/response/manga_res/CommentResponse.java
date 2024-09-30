package com.manganoob.identityservice.dto.response.manga_res;

import com.manganoob.identityservice.Enum.Status;
import com.manganoob.identityservice.dto.response.user_res.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
     UUID id;
     String content;
     UUID userId;
     UUID chapterId;
     UUID mangaId;
     List<ReplyCommentResponse> replyComments;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
    Status status;

}
