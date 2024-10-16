package com.manganoob.mangaservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorResponse {
    Long id;
    String name;
    String authorAvatar;
    String biography;

    String pixiv_link;

    String skeb_link;

    String tumblr_link;

    String youtube_link;

    String weibo_link;

    String naver_link;

    String website_link;
}
