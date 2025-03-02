FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ./target/GYmate-0.0.1-SNAPSHOT.jar GYmate-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/GYmate-0.0.1-SNAPSHOT.jar"]
