package com.manganoob.identityservice.dto.request.manga_req;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImagesRequest {

    @NotNull(message = "Image data is required")
    byte[] imageData;

    @NotNull(message = "Image name is required")
    String imageName;

    @NotNull(message = "Chapter ID is required")
    UUID chapterId;
}