# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado pelo Maven/Gradle para dentro da imagem
COPY target/SGCC-0.0.1-SNAPSHOT.jar /app/SGCC-0.0.1-SNAPSHOT.jar

# Expõe a porta 8080 para o tráfego HTTP
EXPOSE 8080

# Comando para rodar o projeto
ENTRYPOINT ["java", "-jar", "/app/SGCC-0.0.1-SNAPSHOT.jar"]
