package com.manganoob.identityservice.entity;

import com.manganoob.identityservice.Enum.State;
import com.manganoob.identityservice.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String altTitle;
    String description;
    int year;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "manga_chapter",cascade = CascadeType.ALL)
    Set<Chapters> chapters = new HashSet<>();

    @OneToMany(mappedBy = "manga_art",cascade = CascadeType.ALL)
    Set<Art> artList;

    @OneToMany(mappedBy = "manga_author",cascade = CascadeType.ALL)
    List<Author> authorList;

    @ManyToMany(mappedBy = "manga_genres")
    Set<Genres>genresList;

    @ManyToMany(mappedBy = "manga_format")
    Set<Format>formatList;

    @ManyToMany(mappedBy = "manga_theme")
    Set<Theme>themeList;

    @ManyToMany(mappedBy = "manga_content_warning")
    Set<ContentWarning>contentWarningList;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
     Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    State state;
}
