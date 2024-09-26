package com.manganoob.identityservice.entity;

import com.manganoob.identityservice.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
     User user_comment;

     LocalDateTime createdAt = LocalDateTime.now();
     LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    Chapters chapter_comment;

    @OneToMany(mappedBy = "reply_comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyComment> replyComments  = new ArrayList<>();
}
