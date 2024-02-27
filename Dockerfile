# Utiliza una imagen base de Java
FROM openjdk:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el código fuente y el archivo pom.xml de tu aplicación Spring Boot al contenedor
COPY . .

# Construir la aplicación Spring Boot
RUN mvn clean package

# Copiar el archivo JAR generado al contenedor
COPY target/nombre-del-archivo.jar app.jar

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
