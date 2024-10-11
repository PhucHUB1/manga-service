package com.manganoob.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

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
    UUID id;

    @Lob
    @Column(name = "imageArt")
    byte[] Art;

    @Column(name = "imageArtname")
    String artName;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    Manga manga;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
