insert into articles (id, header, short_description, text, published_date) values (1, 'header text', 'short description text', 'article text', PARSEDATETIME('1 Oct 2019, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')
);
insert into article_authors (article_id, first_name, last_name) values (1, 'first name', 'last name');
insert into article_keywords (article_id, value) values (1, 'tech');
insert into article_keywords (article_id, value) values (1, 'sports');
insert into article_keywords (article_id, value) values (1, 'science');
