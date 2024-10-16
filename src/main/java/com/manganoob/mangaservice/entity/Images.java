package com.manganoob.mangaservice.entity;


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
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Lob
    @Column(name = "image")
     byte[] imageData;

     String imageName;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
     Chapters chapter;
}
