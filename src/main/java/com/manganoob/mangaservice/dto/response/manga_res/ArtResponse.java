package com.manganoob.mangaservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtResponse {
    UUID id;
    String imageArt;
    UUID mangaId;
    String userId;
}
