#FROM amazoncorretto:17-alpine-jdk
FROM openjdk:17-jdk-alpine
MAINTAINER com.lekhraj
COPY target/spring-1.0.0.jar spring-1.0.0.jar
ENTRYPOINT ["java","-jar","/spring-1.0.0.jar"]