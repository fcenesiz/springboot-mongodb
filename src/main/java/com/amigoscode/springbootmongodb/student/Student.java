package com.amigoscode.springbootmongodb.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Document // mongo için bu bir document'tir dedik!
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true) // email'leri eşsiz yapmak için, application.yaml'da 'auto-index-creation: true' olmalı!
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favoriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    public Student(String firstName, String lastName, String email, Gender gender,
                   Address address, List<String> favoriteSubjects, BigDecimal totalSpentInBooks,
                   LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favoriteSubjects = favoriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}
