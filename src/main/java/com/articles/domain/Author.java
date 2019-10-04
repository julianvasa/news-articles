package com.articles.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @NotNull(message = "Author first name must not be null")
    @NotBlank(message = "Author first name must not be empty")
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @NotNull(message = "Author last name must not be null")
    @NotBlank(message = "Author last name must not be empty")
    @Column(nullable = false, name = "last_name")
    private String lastName;


    @Override
    public String toString() {
        return "Author{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}
