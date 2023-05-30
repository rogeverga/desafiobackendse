# DESAFIO BACKEND SE

## Descrição
API responsável em criar link de pagamento através dos pedidos enviados

## Arquitetura
O projeto é desenvolvido em:
- Spring Boot 2.5.4;
- Java 8;
- Webflux;
- Maven;
- JUnit Jupiter;

## Como baixar as dependências do projeto
- Basta executar o comando mvn clean install para baixar as dependências do repositório central do maven

## Como executar o projeto
- Após baixar as dependências do projeto basta executar o comando mvn spring-boot:run para subir a API e começar a utilizá-la

## Como executar os testes unitários do projeto
- Após baixar as dependências do projeto basta executar o comando mvn test para testar a API

## Como testar o projeto
- Basta importar a collection que está na raíz do projeto chamada 'DesafioBackendSE.postman_collection.json' no postman e subir o projeto

## Observações
- Nem todos os testes unitários foram feitos
- Faltou implementar os logs das requests e responses ao serviço de meio de pagamento 