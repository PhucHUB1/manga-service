package com.manganoob.identityservice.dto.response.manga_res;

import com.manganoob.identityservice.Enum.Status;
import com.manganoob.identityservice.dto.response.user_res.UserResponse;
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
