package com.manganoob.identityservice.dto.request.manga_req;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequest {
    String name;
    MultipartFile authorAvatar;
    String biography;

    String pixiv_link;

    String skeb_link;

    String tumblr_link;

    String youtube_link;

    String weibo_link;

    String naver_link;

    String website_link;
}
