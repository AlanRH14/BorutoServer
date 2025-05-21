# Usamos imagen base de OpenJDK con Gradle
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Imagen final con JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]