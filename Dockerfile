FROM openjdk:17
ARG JAR_FILE=target/GYMate-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gymate-app.jar
ENTRYPOINT ["java", "-jar", "/gymate-app.jar"]
