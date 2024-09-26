package com.devteria.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String altTitle;
    String description;
    int year;


    LocalDate createDate;
    LocalDate updateDate;

}
