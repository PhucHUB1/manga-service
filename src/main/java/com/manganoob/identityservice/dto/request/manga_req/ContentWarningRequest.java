package com.manganoob.identityservice.dto.request.manga_req;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentWarningRequest {
    String content_name;
}
