FROM openjdk:18
ARG JAR_FILE=docker/WAA_first_demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]