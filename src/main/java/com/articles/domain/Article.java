package com.articles.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Article {

    @Id
    @NotNull
    private Long id;

    @NotNull(message = "Article header must not be null")
    @NotBlank
    @Column(name = "header")
    private String header;

    @NotNull(message = "Article short description must not be null")
    @NotBlank
    @Column(name = "short_description")
    private String shortDescription;

    @NotNull(message = "Article text must not be null")
    @NotBlank
    @Column(name = "text")
    private String text;

    @Column(name = "published_date")
    private Date publishedDate;

    @NotNull(message = "Article author/s must not be null")
    @NotEmpty(message = "Article authors list must not be empty")
    @Valid
    @Column(name = "authors")
    @ElementCollection
    private Set<Author> authors;

    @Valid
    @Column(name = "keywords")
    @ElementCollection
    private Set<Keyword> keywords;


}
