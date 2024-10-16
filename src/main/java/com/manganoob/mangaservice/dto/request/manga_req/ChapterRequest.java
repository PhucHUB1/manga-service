package com.manganoob.mangaservice.dto.request.manga_req;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterRequest {
    @NotNull(message = "Volume is required")
    @Size(min = 1, max = 10, message = "Volume must be between 1 and 10 characters")
    String volume;

    @NotNull(message = "Page number is required")
    @Size(min = 1, max = 10, message = "Page number must be between 1 and 10 characters")
    String pageNumber;

    @NotNull(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    String title;

    @NotNull(message = "Release date is required")
    LocalDate releaseDate;

    @NotNull(message = "Manga ID is required")
    UUID mangaId;
}
