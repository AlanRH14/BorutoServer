#-----------------------------------------------------------------------------
# Variables compartidas
ARG APP_NAME="BorutoServer"
ARG PORT=8080
ARG GRADLE_VERSION=8.4
ARG JAVA_VERSION=17

#-----------------------------------------------------------------------------
# Etapa de construcción optimizada
FROM gradle:${GRADLE_VERSION}-jdk${JAVA_VERSION}-alpine AS builder

# Configuración de Gradle para entornos limitados
RUN mkdir -p /home/gradle/.gradle && \
    echo "org.gradle.jvmargs=-Xmx256m -XX:MaxMetaspaceSize=128m" > /home/gradle/.gradle/gradle.properties && \
    echo "org.gradle.daemon=false" >> /home/gradle/.gradle/gradle.properties && \
    echo "org.gradle.caching=true" >> /home/gradle/.gradle/gradle.properties

WORKDIR /app

# 1. Copia solo los archivos necesarios para la resolución de dependencias
COPY build.gradle.kts settings.gradle.kts gradle.properties /app/
COPY gradle /app/gradle

# 2. Descarga dependencias primero (caché independiente)
RUN gradle --no-daemon --stacktrace --info --refresh-dependencies dependencies

# 3. Copia el resto del código
COPY src /app/src

# 4. Construye la aplicación
RUN gradle --no-daemon --stacktrace --info build

#-----------------------------------------------------------------------------
# Etapa final ultra ligera
FROM eclipse-temurin:${JAVA_VERSION}-jre-alpine

ARG PORT
ENV PORT=$PORT

WORKDIR /app

# Copia solo el JAR construido
COPY --from=builder /app/build/libs/${APP_NAME}-*.jar ./app.jar

# Configuración mínima para Ktor
EXPOSE ${PORT}

# Optimización para contenedores
ENTRYPOINT ["java", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseContainerSupport", \
    "-XX:+AlwaysActAsServerClassMachine", \
    "-XX:+UseStringDeduplication", \
    "-jar", "app.jar"]