package com.manganoob.mangaservice.dto.response.manga_res;

import com.manganoob.mangaservice.Enum.Status;
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
