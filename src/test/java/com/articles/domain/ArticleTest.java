package com.articles.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;

public class ArticleTest {

    private Set<Author> sampleAuthorList;
    private Set<Author> sampleAuthorListWithFirstNameNull;
    private Set<Author> sampleAuthorListWithLastNameNull;
    private Set<Author> sampleAuthorListWithFirstNameBlank;
    private Set<Author> sampleAuthorListWithLastNameBlank;
    private Set<Keyword> sampleKeywordsWithBlankValue;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Author author = Author.builder().firstName("First Name").lastName("Last Name").build();
        Author authorWithFirstNameNull = Author.builder().lastName("Last Name").build();
        Author authorWithFirstNameBlank = Author.builder().firstName("").lastName("Last Name").build();
        Author authorWithLastNameNull = Author.builder().firstName("First Name").build();
        Author authorWithLastNameBlank = Author.builder().firstName("First Name").lastName("").build();
        sampleAuthorListWithFirstNameNull = Stream.of(authorWithFirstNameNull).collect(Collectors.toSet());
        sampleAuthorListWithFirstNameBlank = Stream.of(authorWithFirstNameBlank).collect(Collectors.toSet());
        sampleAuthorListWithLastNameNull = Stream.of(authorWithLastNameNull).collect(Collectors.toSet());
        sampleAuthorListWithLastNameBlank = Stream.of(authorWithLastNameBlank).collect(Collectors.toSet());
        sampleAuthorList = Stream.of(author).collect(Collectors.toSet());

        Keyword keyword = Keyword.builder().value("").build();
        sampleKeywordsWithBlankValue = Stream.of(keyword).collect(Collectors.toSet());
    }

    @Test
    public void whenEmptyListOfAuthors_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(Collections.emptySet()).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenListOfAuthorsContainsAuthorWithFirstNameIsNull_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorListWithFirstNameNull).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenListOfAuthorsContainsAuthorWithFirstNameIsBlank_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorListWithFirstNameBlank).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenListOfAuthorsContainsAuthorWithLastNameIsNull_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorListWithLastNameNull).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenListOfAuthorsContainsAuthorWithLastNameIsBlank_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorListWithLastNameBlank).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenHeaderIsNull_throwsValidationError() {
        Article article = Article.builder().text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenHeaderIsBlank_throwsValidationError() {
        Article article = Article.builder().header("").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenTextIsNull_throwsValidationError() {
        Article article = Article.builder().header("Header").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenTextIsBlank_throwsValidationError() {
        Article article = Article.builder().text("").header("Header").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenPublishedDateIsNull_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").shortDescription("short description").authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenShortDescriptionIsNull_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenShortDescriptionIsBlank_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").shortDescription("").publishedDate(new Date()).authors(sampleAuthorList).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void whenListOfKeywordsContainsKeywordWithBlankValue_throwsValidationError() {
        Article article = Article.builder().header("Header").text("text").publishedDate(new Date()).shortDescription("short description").authors(sampleAuthorList).keywords(sampleKeywordsWithBlankValue).build();
        final Set<ConstraintViolation<Article>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(article);
        assertFalse(violations.isEmpty());
    }
}
