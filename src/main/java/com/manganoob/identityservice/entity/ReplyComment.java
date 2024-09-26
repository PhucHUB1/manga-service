package com.manganoob.identityservice.entity;

import com.manganoob.identityservice.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String content;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user_reply_comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    Comment reply_comment;

}
