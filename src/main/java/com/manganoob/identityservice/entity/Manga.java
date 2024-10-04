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
import java.util.UUID;

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
    UUID id;

    String title;
    String altTitle;
    String description;
    int year;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "manga_chapters", cascade = CascadeType.ALL)
    Set<Chapters> chapters = new HashSet<>();

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL)
    Set<Art> artList;

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL)
    List<Comment> mangaCommentList;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author manga_author;

    @ManyToMany(mappedBy = "manga_genres")
    Set<Genres> manga_genres;

    @ManyToMany(mappedBy = "manga_format")
    Set<Format> manga_format;

    @ManyToMany(mappedBy = "manga_theme")
    Set<Theme> manga_theme;

    @ManyToMany(mappedBy = "manga_content_warning")
    Set<ContentWarning> manga_content_warning;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    State state;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.year = LocalDateTime.now().getYear();
        if (this.status == null && this.state == null) {
            this.status = Status.WAITING;
            this.state  = State.SUBMITTED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.year = LocalDateTime.now().getYear();
    }
}

