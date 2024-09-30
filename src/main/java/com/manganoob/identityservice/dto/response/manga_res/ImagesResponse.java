package com.manganoob.identityservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImagesResponse {
    UUID id;
    String imageName;
    UUID chapterId;
}