# Anagram Generator

Esse projeto é um utilitário simples, feito em Java com Spring Boot, que gera todos os anagramas possíveis de uma lista de letras distintas.

Desenvolvido para um desafio técnico. A ideia foi fazer uma solução bem clara, com validação da entrada e testes cobrindo os principais casos.

## Descrição

O programa recebe uma lista de letras distintas e retorna todas as permutações possíveis (anagramas). A geração é feita com backtracking. Também possui validações básicas:

- Não pode conter letras repetidas
- Todos os caracteres devem ser letras
- A lista não pode estar vazia

## Tecnologias usadas

- Java 17
- Maven
- Spring Boot
- JUnit 5

## Como rodar o projeto

Pré-requisitos:
- Java 17 instalado
- Maven instalado

1. Clonar o repositório

Git clone: https://github.com/jhordanben/anagram-generator.git
cd anagram-generator

2. Rodar os testes
mvn clean test

3. Subir a aplicação
mvn spring-boot:run

4. Fazer uma requisição para o endpoint

POST http://localhost:8080/api/anagrams  


Content-Type: application/json

```json
{
  "letters": ["a", "b", "c"]
}

## Ou também no PowerShell

Invoke-RestMethod -Uri "http://localhost:8080/api/anagrams" -Method Post -ContentType "application/json" -Body '{"letters":["a","b","c"]}'


## Casos de teste implementados

- Entrada comum com 3 letras
- Entrada com uma letra só
- Entrada com caracteres inválidos
- Entrada com letras repetidas
- Entrada vazia
" > README.md