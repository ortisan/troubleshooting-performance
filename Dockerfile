#FROM adoptopenjdk/openjdk11:alpine
FROM openjdk:11.0.6-jre-slim
#RUN set -ex && apk --no-cache add sudo
#RUN apk add --no-cache gdb
RUN apt-get update && apt-get install libjemalloc2 && rm -rf /var/lib/apt/lists/*
ENV LD_PRELOAD=/usr/lib/x86_64-linux-gnu/libjemalloc.so.2
WORKDIR '/app'
COPY ./target/app.jar ./app.jar
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:MaxRAMPercentage=50", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:MaxMetaspaceSize=256m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:CompressedClassSpaceSize=128m", "-XX:MaxMetaspaceSize=128m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:-UseStringDeduplication", "-XX:-TieredCompilation", "-XX:CompressedClassSpaceSize=128m", "-XX:MaxMetaspaceSize=128m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:MaxDirectMemorySize=128m", "-XX:+UseStringDeduplication", "-XX:CompressedClassSpaceSize=64m", "-XX:MaxMetaspaceSize=128m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
