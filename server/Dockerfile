#FROM adoptopenjdk/openjdk11:alpine
FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine

RUN apk --no-cache add git build-base cmake linux-headers

#mimalloc config
RUN cd /; git clone https://github.com/microsoft/mimalloc; cd mimalloc; mkdir build; cd build; cmake ..; make
ENV LD_PRELOAD=/lib64/libmimalloc.so
ENV MIMALLOC_LARGE_OS_PAGES=1

ENV LANG C.UTF-8

RUN export GRPC_TRACE=all
RUN export GRPC_VERBOSITY=DEBUG 

#FROM openjdk:11.0.6-jre-slim
#FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.10_9_openj9-0.24.0-alpine-slim
#RUN set -ex && apk --no-cache add sudo
#RUN apk add --no-cache gdb
#RUN apt-get update && apt-get install libjemalloc2 && rm -rf /var/lib/apt/lists/*
#ENV LD_PRELOAD=/usr/lib/x86_64-linux-gnu/libjemalloc.so.2
WORKDIR '/app'
COPY ./target/server.jar ./app.jar
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:MaxRAMPercentage=50", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:MaxMetaspaceSize=256m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:CompressedClassSpaceSize=128m", "-XX:MaxMetaspaceSize=128m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
#CMD ["java", "-XX:NativeMemoryTracking=summary", "-XX:-UseStringDeduplication", "-XX:-TieredCompilation", "-XX:CompressedClassSpaceSize=128m", "-XX:MaxMetaspaceSize=128m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
# Best footprint until now
# CMD ["java", "-XX:NativeMemoryTracking=summary", "-Xss256k",  "-XX:ReservedCodeCacheSize=30m", "-XX:MaxDirectMemorySize=128m", "-XX:+UseStringDeduplication", "-XX:CompressedClassSpaceSize=15m", "-XX:MaxMetaspaceSize=100m", "-Xms128m", "-Xmx200m", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
CMD ["java", "-XX:MaxGCPauseMillis=200", "-XX:ParallelGCThreads=5", "-XX:ConcGCThreads=5", "-jar", "app.jar"]
