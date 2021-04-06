FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine

RUN apk --no-cache add git build-base cmake linux-headers curl ca-certificates \
    && mkdir -p /opt/appdynamics/ /usr/local/share/ca-certificates/itau /app

RUN cd /; git clone --depth 1 --branch v1.6.7 https://github.com/microsoft/mimalloc; cd mimalloc; mkdir build; cd build; cmake ..; make -j$(nproc); make install

ENV LD_PRELOAD=/lib/libmimalloc.so
ENV MIMALLOC_LARGE_OS_PAGES=1
ENV LANG C.UTF-8

# Retirado os certificados

WORKDIR /app

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

HEALTHCHECK --interval=2m --retries=3 --timeout=8s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c",  "java $JAVA_OPTS -jar app.jar"]