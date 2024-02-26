# Utiliza una imagen base de Java
FROM openjdk:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación Spring Boot al contenedor
COPY . .

# Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-cp", "target/classes", "com.SocialLift.SocialLift.SocialLiftBackApplication"]