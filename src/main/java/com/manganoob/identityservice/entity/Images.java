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
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Lob
    @Column(nullable = false)
     byte[] imageData;

     String imageName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
     Chapters chapter;
}
