package com.manganoob.identityservice.entity;

import com.manganoob.identityservice.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    String password;
    String firstName;
    String lastName;
    String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status ;

    @Column(name = "is_vip", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    boolean isVip = false;


    @Lob
    @Column(length = 1000000)
    byte[] imageAvatar;

    @Column(name = "imageAvatarFilename")
    String imageAvatarFilename;


    @ManyToMany
    Set<Role> roles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Comment> comments;

    @OneToMany(mappedBy = "user_reply_comment",cascade = CascadeType.ALL)
    Set<ReplyComment> replyComments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Art> artList;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = Status.ACTIVE;
        }

    }
}
