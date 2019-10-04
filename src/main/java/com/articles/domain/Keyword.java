package com.articles.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Table(name = "keywords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword {

    @NotNull(message = "Keyword value must not be null")
    @NotBlank(message = "Keyword value must not be empty")
    @Column(nullable = false, name = "value")
    private String value;

    @Override
    public String toString() {
        return "Keyword{" +
            "value='" + value + '\'' +
            '}';
    }
}
