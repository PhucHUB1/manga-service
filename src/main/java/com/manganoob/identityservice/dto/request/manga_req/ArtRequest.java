package com.manganoob.identityservice.dto.request.manga_req;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtRequest {
    MultipartFile imageArt;
    String description;
}
