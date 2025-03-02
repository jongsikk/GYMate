FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ./target/gymate.jar gymate.jar
ENTRYPOINT ["java", "-jar", "/gymate.jar"]
