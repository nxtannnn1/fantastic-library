# Use uma imagem do OpenJDK
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o build do backend (jar) para o container
COPY target/*.jar app.jar

# Expõe a porta 8081
EXPOSE 8081

# Comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]
