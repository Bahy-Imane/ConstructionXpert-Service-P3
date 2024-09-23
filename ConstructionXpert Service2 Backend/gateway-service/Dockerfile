FROM openjdk:21
ARG JAR_FILE=target/app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]