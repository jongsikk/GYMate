FROM openjdk:17
COPY ./target/GYMate-0.0.1-SNAPSHOT.jar GYMMte.jar
ENTRYPOINT ["java", "-jar", "/GYMate.jar"]
