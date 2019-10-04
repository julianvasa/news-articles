FROM openjdk:8
ADD target/application-1.0.jar application-1.0.jar
ENTRYPOINT ["java","-jar","application-1.0.jar"]
