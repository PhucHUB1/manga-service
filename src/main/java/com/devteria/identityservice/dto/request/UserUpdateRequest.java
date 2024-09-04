package com.devteria.identityservice.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.devteria.identityservice.validator.DobConstraint;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;

    @Email
    String email;

    List<String> roles;
}
