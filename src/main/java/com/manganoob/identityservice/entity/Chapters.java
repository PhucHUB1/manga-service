package com.manganoob.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Chapters {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String volume;
    String chapter_number;
    String page_number;

    String title;
    LocalDate release_date;
    LocalDateTime createAt;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Images> chapterImages;

    @OneToMany(mappedBy = "chapter_comment", cascade = CascadeType.ALL)
    List<Comment> chapterComments;

    @ManyToOne
    Manga manga_chapter;


}
