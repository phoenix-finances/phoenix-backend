ARG docker_source_repository

FROM ${docker_source_repository}/amazoncorretto:17
LABEL authors="sufian"

WORKDIR /app
COPY app.jar /app

ENTRYPOINT ["java","-jar","app.jar"]