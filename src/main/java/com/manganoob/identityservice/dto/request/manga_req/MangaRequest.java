package com.manganoob.identityservice.dto.request.manga_req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MangaRequest {

    @NotNull(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    String title;

    @Size(max = 255, message = "Alt Title cannot exceed 255 characters")
    String altTitle;

    @NotNull(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 1000 characters")
    String description;

    Set<UUID> chapterIds;
    Set<UUID> artIds;
    Long authorId;

    Set<String> genreIds;
    Set<String> formatIds;
    Set<String> themeIds;
    Set<String> contentWarningIds;

}

