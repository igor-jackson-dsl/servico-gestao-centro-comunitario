
# Sistema de Gerenciamento de Centros Comunitários (SGCC)

Este projeto é um sistema de gerenciamento de centros comunitários, desenvolvido utilizando Spring Boot e MongoDB. Ele permite o intercâmbio de recursos entre centros comunitários, com regras para validação de ocupação e pontos, além de um sistema de notificação e persistência de dados no MongoDB.

## Funcionalidades

- **Cadastro de Centros Comunitários**: Permite o cadastro de centros comunitários, incluindo suas informações de capacidade e ocupação.
- **Intercâmbio de Recursos**: Gerenciamento do intercâmbio de recursos entre centros, seguindo regras específicas de ocupação e pontos.
- **Regras de Intercâmbio**:
  - Verificação de quantidade de recursos suficiente para a troca.
  - Validação de ocupação superior a 90% para permitir troca com diferença de pontos.
  - Persistência de dados de intercâmbio.
- **Integração com MongoDB**: Armazenamento de dados no banco de dados MongoDB, com suporte a inicialização de coleções com dados preexistentes.

## Tecnologias Utilizadas

- **Java 11+**
- **Spring Boot 2.7.x**
- **MongoDB**
- **Spring Data MongoDB**
- **Docker Compose (para MongoDB e RabbitMQ)**
- **JUnit 5** (para testes unitários)
- **Spring Boot Test** (para integração com MongoDB embutido)
- **Swagger/OpenAPI** (para documentação de API)

## Pré-requisitos

- **Java 11+**
- **Maven 3.9.9**
- **Docker** e **Docker Compose**
- **MongoDB** (ou pode usar a versão incorporada nos testes)

## Configuração

### 1. Executar a Aplicação

Você pode iniciar a aplicação junto dos serviços de banco de dados e mensagens com Docker Compose. Certifique-se de ter o Docker instalado e execute o seguinte comando no diretório raiz do projeto:
```bash
mvn install
```
```bash
docker-compose up -d
```

Este comando inicializará os contêineres da aplicação, MongoDB e RabbitMQ.


A aplicação estará disponível em: `http://localhost:8080`.



## Documentação da API

O sistema utiliza o **Swagger/OpenAPI** para documentar os endpoints REST. Para acessar a documentação da API, vá até:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

- **POST /centros**: Cadastra um novo centro comunitário.
- **PUT /centros/{id}/ocupacao**: Atualiza a ocupação de um centro.
- **GET /centros/listar**: lista todos os centros.
- **POST /intercambio**: Realiza um intercâmbio de recursos entre centros comunitários.
- **GET /intercambio**: Lista todos os intercâmbios realizados.
- **GET /relatorios/relatorio-recursos**: Gera um pdf com a media de recursos por centro comunitario.
- **GET /relatorios/relatorio-ocupacao**: Gera um pdf com os centros com ocupacao maior que 90%.
- **GET /relatorios/relatorio-intercambio**: Gera um pdf com os intercambios realizados.

## Estrutura do Projeto

- `model/`: Contém as entidades de negócio (`CentroComunitario`, `CentroRecursos`, `Recursos`, `Intercambio`).
- `repository/`: Interfaces para comunicação com o MongoDB.
- `service/`: Lógica de negócio para o gerenciamento dos centros e intercâmbios.
- `controller/`: Endpoints REST que expõem as funcionalidades do sistema.
- `config/`: Configurações da aplicação, incluindo inicialização de coleções no MongoDB.
- `test/`: Testes unitários e de integração.

## Testes

Os testes são executados com JUnit 5 e Spring Boot Test. Para rodar os testes:

```bash
mvn test
```

