package com.manganoob.identityservice.dto.request.manga_req;

import com.manganoob.identityservice.Enum.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
    @NotNull(message = "Content is required")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    String content;

    @NotNull(message = "User ID is required")
    UUID userId;

    UUID mangaId;
    UUID chapterId;

    @NotNull(message = "Status is required")
    Status status;
}
