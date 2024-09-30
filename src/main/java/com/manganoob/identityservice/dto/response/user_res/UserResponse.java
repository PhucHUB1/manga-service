package com.manganoob.identityservice.dto.response.user_res;

import java.time.LocalDate;
import java.util.Set;

import com.manganoob.identityservice.dto.response.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;
    String imageAvatar;
    Set<RoleResponse> roles;
}
