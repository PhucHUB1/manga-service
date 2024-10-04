package com.manganoob.identityservice.dto.request.manga_req;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImagesRequest {
    MultipartFile imageData;

    @NotNull(message = "Chapter ID is required")
    UUID chapterId;
}