FROM openjdk:8-jdk-slim
LABEL maintainer=wangxin

COPY target/*.jar /app.jar


ENTRYPOINT ["java", "-jar", "/a0p0.p.jar"]