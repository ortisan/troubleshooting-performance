FROM openjdk:11-jre-slim
WORKDIR '/app'
COPY ./target/app.jar ./app.jar
CMD ["java", "-XX:+UseContainerSupport",  "-XX:MaxRAMPercentage=80", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=20", "-XX:ConcGCThreads=5", "-jar", "app.jar"]