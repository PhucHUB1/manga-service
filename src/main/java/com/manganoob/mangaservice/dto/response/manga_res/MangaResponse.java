package com.manganoob.mangaservice.dto.response.manga_res;

import com.manganoob.mangaservice.Enum.State;
import com.manganoob.mangaservice.Enum.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MangaResponse {
     UUID id;
     String title;
     String altTitle;
     String description;
     int year;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
     Set<ChapterResponse> chapters;
     Set<ArtResponse> artList;
     List<CommentResponse> comments;
     AuthorResponse author;
     Set<GenresResponse> genresList;
     Set<FormatResponse> formatList;
     Set<ThemeResponse> themeList;
     Set<ContentWarningResponse> contentWarningList;
     Status status;
     State state;
     Double averageRating;
}
