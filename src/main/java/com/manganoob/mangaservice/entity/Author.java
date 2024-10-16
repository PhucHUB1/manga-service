package com.manganoob.mangaservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Lob
    @Column(name = "authorAvatar")
    byte[] authorAvatar;

    @Column(name = "authorAvatarName")
    String authorAvatarName;

    String biography;

    @Column(name = "pixiv")
    String pixiv_link;
    @Column(name = "skeb")
    String skeb_link;
    @Column(name = "tumblr")
    String tumblr_link;
    @Column(name = "youtube")
    String youtube_link;
    @Column(name = "weibo")
    String weibo_link;
    @Column(name = "naver")
    String naver_link;
    @Column(name = "website")
    String website_link;

    @OneToMany(mappedBy = "manga_author",cascade = CascadeType.ALL)
    List<Manga> mangaList;
}
