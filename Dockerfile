#-----------------------------------------------------------------------------
# Variables compartidas entre etapas
ARG APP_NAME="BorutoServer"
ARG PORT=8080
ARG GRADLE_VERSION=8.4
ARG JAVA_VERSION=17

#-----------------------------------------------------------------------------
# Etapa de construcción (Gradle con cache)
FROM gradle:${GRADLE_VERSION}-jdk${JAVA_VERSION}-alpine AS builder

ARG APP_NAME
ARG PORT
ENV PORT=$PORT

# Configuración de Gradle para entornos limitados
RUN mkdir -p /home/gradle/.gradle && \
    echo "org.gradle.jvmargs=-Xmx256m -XX:MaxMetaspaceSize=128m" > /home/gradle/.gradle/gradle.properties && \
    echo "org.gradle.daemon=false" >> /home/gradle/.gradle/gradle.properties && \
    echo "org.gradle.caching=true" >> /home/gradle/.gradle/gradle.properties

WORKDIR /app

# Copia solo los archivos necesarios para construir las dependencias
COPY build.gradle.kts settings.gradle.kts gradle.properties /app/
COPY gradle /app/gradle
RUN gradle --no-daemon dependencies

# Copia el resto del código fuente
COPY src /app/src

# Construye la aplicación
RUN gradle --no-daemon build

#-----------------------------------------------------------------------------
# Etapa final (ejecución minimalista)
FROM eclipse-temurin:${JAVA_VERSION}-jre-alpine

ARG APP_NAME
ARG PORT
ENV PORT=$PORT

# Instala solo lo esencial para un servicio web
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/America/Mexico_City /etc/localtime && \
    echo "America/Mexico_City" > /etc/timezone && \
    apk del tzdata

WORKDIR /app

# Copia el JAR construido y elimina metadatos innecesarios
COPY --from=builder /app/build/libs/${APP_NAME}-*.jar ./app.jar
RUN find /app -name "*.jar" -exec sh -c 'exec strip --strip-debug "$0"' {} \;

# Configuración de salud y métricas (opcional)
HEALTHCHECK --interval=30s --timeout=3s \
    CMD wget --quiet --tries=1 --spider http://localhost:${PORT}/health || exit 1

EXPOSE ${PORT}

# Ejecución optimizada para contenedores
ENTRYPOINT ["java", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseContainerSupport", \
    "-XX:+AlwaysActAsServerClassMachine", \
    "-XX:+UseStringDeduplication", \
    "-jar", "app.jar"]