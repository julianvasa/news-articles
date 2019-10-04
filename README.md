# Simple articles application

This is a simple rest api app to insert/read/delete news articles.

The following use cases have been implemented:
 
 - allow an editor to create/update/delete an article
 - display one article
 - list all articles for a given author
 - list all articles for a given period
 - find all articles for a specific keyword 
 
 An article consist in the following information:
 
 - header
 - short description
 - text
 - publish date
 - author(s)
 - keyword(s)
 
 [Swagger REST API Documentation](http://localhost/swagger-ui.html)<br>

An initial article is inserted automatically on the local DB 

The project is ready to be deployed in **Docker**. 
 <br>
 Please change:
  - `docker.image.prefix` 
  - `docker.image.location` 
  
 in pom.xml and run dockerfile plugin, it will generate an image file that can be deployed in Docker. 
 Docker application should be running before running maven plugin dockerfile.
  
**Build:**
1. mvn clean package 
2. mvn spring-boot:run

**To deploy in docker:**
1. mvn dockerfile-build
2. cd target/docker
3. create articles repository on Docker first 
4. docker push julianvasa/articles:latest

