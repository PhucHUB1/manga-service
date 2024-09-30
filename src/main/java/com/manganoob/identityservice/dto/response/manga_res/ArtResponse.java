package com.manganoob.identityservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtResponse {
    Long id;
    String imageArt;
    String description;
}
