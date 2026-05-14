FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle gradle

RUN gradle dependencies --no-daemon || true

COPY src src

RUN gradle bootJar --no-daemon -x test

FROM openjdk:21-jre-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/build/libs/*.jar app.jar

RUN mkdir -p /app/uploads /app/logs

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
