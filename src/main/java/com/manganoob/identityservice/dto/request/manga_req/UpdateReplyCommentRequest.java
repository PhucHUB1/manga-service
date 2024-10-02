package com.manganoob.identityservice.dto.request.manga_req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateReplyCommentRequest {
    @NotNull(message = "Content is required")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    String reply_content;
}
