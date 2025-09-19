#DOCKERFILE-BACKEND

# Use uma imagem do OpenJDK
FROM openjdk:23-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app


# Agora o Docker conseguirá encontrar o arquivo.
COPY target/Biblioteca-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8080"]
