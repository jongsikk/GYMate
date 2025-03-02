FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} GYMMte-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/GYMate-0.0.1-SNAPSHOT.jar"]
