# --- Stage 1: Build the Application ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Builds the JAR file inside the container
RUN mvn clean package -DskipTests

# --- Stage 2: Run the Application ---
# (Using Java 21 LTS is safer than 22, but works for both)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy the built JAR from Stage 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Forces UTC (Best practice) but your DB connection handles the specific timezone
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "app.jar"]