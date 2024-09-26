package com.manganoob.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Lob
    @Column(name = "imageArt")
    byte[] imageArt;

    @Column(name = "imageArtname")
    String imageArtName;

    String description;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    Manga manga_art;
}
