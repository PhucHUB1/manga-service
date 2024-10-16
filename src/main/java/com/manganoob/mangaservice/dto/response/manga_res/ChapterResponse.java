package com.manganoob.mangaservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterResponse {
     UUID id;
     String volume;
     String pageNumber;
     String title;
     LocalDate releaseDate;
     LocalDateTime createAt;
     MangaResponse manga;
     List<ImagesResponse> chapterImages;
     List<CommentResponse> comments;
}
