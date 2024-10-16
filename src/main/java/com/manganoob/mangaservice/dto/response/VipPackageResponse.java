package com.manganoob.mangaservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VipPackageResponse {
    int id;
    String name;
    double price;
    String privileges;
}