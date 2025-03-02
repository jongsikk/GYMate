FROM openjdk:17
COPY ./target/GYMMte-0.0.1-SNAPSHOT.jar GYMMte.jar
ENTRYPOINT ["java", "-jar", "/GYMate.jar"]
