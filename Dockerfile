FROM openjdk:11-jre-slim
WORKDIR '/app'
COPY ./target/app.jar ./app.jar
CMD ["java", "-jar", "app.jar"]