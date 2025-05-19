#-----------------------------------------------------------------------------
# Variables shared across multiple stages (they need to be explicitly opted
# into each stage by being declaring there too, but their values need only be
# specified once).
ARG KOBWEB_APP_ROOT="site"

#-----------------------------------------------------------------------------
# Usa una imagen con JDK 17
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo
WORKDIR /app

# Copia el JAR compilado (lo generaremos en el siguiente paso)
COPY build/libs/your-project-name-all.jar /app/app.jar

# Expone el puerto que usará Render
EXPOSE 10000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]