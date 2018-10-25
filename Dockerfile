FROM openjdk:8-jdk-alpine

VOLUME /tmp

#ARG JAR_FILE
ARG JAR_FILE=target/businessdate-service-0.0.1-SNAPSHOT-thorntail.jar

COPY ${JAR_FILE} app.jar
COPY ./docker-entrypoint.sh /

ENTRYPOINT ["sh", "/docker-entrypoint.sh"]

# docker build -t c.grings/businessdate-service:0.0.1-SNAPSHOT .