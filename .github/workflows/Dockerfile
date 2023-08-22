ARG docker_source_repository

FROM $docker_source_repository
LABEL authors="sufian"

WORKDIR /app
COPY app.jar /app

ENTRYPOINT ["java","-jar","app.jar"]