# 1. Update to Java 22 to match your local development environment
FROM eclipse-temurin:22-jdk-jammy

WORKDIR /app

# 2. Copy the JAR (Ensure 'mvn clean install' passed successfully first!)
COPY target/*.jar app.jar

EXPOSE 8080

# 3. Enhanced Entrypoint:
#    - Forces UTC timezone to prevent "Asia/Calcutta" errors
#    - Allows passing arguments
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "app.jar"]