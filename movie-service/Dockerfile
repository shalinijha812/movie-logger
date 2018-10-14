FROM openjdk:10
EXPOSE 8080
ADD target/movie-service-0.0.1-SNAPSHOT.jar /usr/src/movie-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","movie-service-0.0.1-SNAPSHOT.jar"]
