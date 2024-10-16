package com.manganoob.mangaservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyCommentResponse {
      UUID id;
      String reply_content;
      String commentContent;
      UUID commentId;
      UUID userId;
      LocalDateTime createdAt;
      LocalDateTime updatedAt;
}
