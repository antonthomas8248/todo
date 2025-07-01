FROM openjdk:24
EXPOSE 8080
ADD target/todocrud-0.0.1-SNAPSHOT.jar todocrud-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/todocrud-0.0.1-SNAPSHOT.jar"]
