# Use a imagem oficial do OpenJDK 21
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo JAR gerado pelo Maven
COPY target/corporate-maintenance-system-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que sua aplicação Spring Boot usa
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]