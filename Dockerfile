FROM openjdk:17
EXPOSE 8080
ADD /target/forum-0.0.1-SNAPSHOT.jar forum.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod","-jar", "forum.jar"]