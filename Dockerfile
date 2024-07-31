FROM amazoncorretto:11-alpine as corretto-jdk
RUN apk add --no-cache binutils
RUN jlink \
         --verbose \
         --add-modules java.base,java.compiler,java.desktop,java.instrument,java.management,java.naming,java.prefs,java.rmi,java.scripting,java.security.jgss,java.sql,jdk.httpserver,jdk.jfr,jdk.unsupported,jdk.crypto.ec,jdk.crypto.cryptoki \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /jre

FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

EXPOSE 9707
COPY --from=corretto-jdk /jre $JAVA_HOME
ARG JAR_FILE=target/IOX-TenantManagement-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app/app.jar
WORKDIR /app
CMD  ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
