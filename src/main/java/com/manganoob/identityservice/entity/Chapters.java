package com.manganoob.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    UUID id;

    String volume;
    String page_number;
    String title;
    LocalDate release_date;
    LocalDateTime createAt;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Images> chapterImages;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> chapterComments;

    @ManyToOne
    @JoinColumn(name = "manga_id", nullable = false)
    Manga manga_chapters;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }
}
