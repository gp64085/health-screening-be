# syntax=docker/dockerfile:1.7-labs

# --------
# Builder: uses Maven with Temurin JDK 17 and BuildKit cache for faster builds
# --------
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy only the pom first to leverage Docker layer caching for deps
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -e -B -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -e -B -DskipTests package

# ------------
# Runtime: lightweight JRE 17 with non-root user
# ------------
FROM eclipse-temurin:17-jre-alpine AS runtime

# Create non-root user
RUN addgroup -S app \
    && adduser -S -G app app

WORKDIR /app

# Copy the fat jar from the builder
COPY --from=builder /app/target/*.jar app.jar

# OCI labels (metadata)
LABEL org.opencontainers.image.title="health-screening-be" \
      org.opencontainers.image.description="Spring Boot Health Screening backend" \
      org.opencontainers.image.licenses="MIT"

# Default active profile (overridable at runtime)
ARG SPRING_PROFILES_ACTIVE=prod
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Common envs (overridable)
ENV SERVER_PORT=8082
# Expose app port
EXPOSE 8082

# Run as non-root
USER app


# JVM options can be provided via JAVA_TOOL_OPTIONS
CMD ["java", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
