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
public class ArtRequest {
    MultipartFile imageArt;

    @NotNull(message = "Manga ID is required")
    UUID mangaId;
    @NotNull(message = "User ID is required")
    String userId;
}
