FROM openjdk:11-jre-slim
EXPOSE 9707
VOLUME /tmp
ARG JAR_FILE=target/iox-tenant-mgmt-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
