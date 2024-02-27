# Utiliza una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17-slim AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el código fuente y el archivo pom.xml de tu aplicación Spring Boot al contenedor
COPY pom.xml .
COPY src ./src

# Construir la aplicación Spring Boot
RUN mvn clean package

# Utiliza una imagen base de Java para ejecutar la aplicación
FROM openjdk:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR construido de la fase de construcción anterior al contenedor
COPY --from=build /app/target/SocialLift-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
