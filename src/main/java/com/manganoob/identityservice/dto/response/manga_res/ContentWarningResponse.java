package com.manganoob.identityservice.dto.response.manga_res;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentWarningResponse {
    String content_name;
}
