# Desafio Técnico Backend – BTG Pactual

Este repositório contém a solução do desafio técnico **Engenheiro de Software – BTG Pactual**.
O objetivo é processar pedidos recebidos de uma fila RabbitMQ, armazenar os dados em banco de dados e expor uma API REST com as informações:

* Valor total do pedido
* Quantidade de pedidos por cliente
* Lista de pedidos realizados por cliente

---

## 📝 Descrição do desafio

* Criar uma aplicação (Java / .NET / Node)
* Modelar e implementar uma base de dados (PostgreSQL, MySQL ou MongoDB)
* Criar um microserviço que consuma dados de uma fila RabbitMQ e grave os dados
* Expor endpoints REST para:

  * Valor total do pedido
  * Quantidade de pedidos por cliente
  * Lista de pedidos realizados por cliente

**Exemplo da mensagem consumida da fila:**

```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```

---

## ⚙️ Tecnologias utilizadas

* **Java 17** + **Spring Boot**
* **RabbitMQ** (mensageria)
* **MongoDB** (persistência dos pedidos)
* **Docker Compose** para orquestração dos serviços

---

## 🚀 Como rodar o projeto

### 1. Pré-requisitos

* Docker e Docker Compose instalados
* (Opcional) Java 17 e Maven para rodar localmente

### 2. Rodando com Docker (recomendado)

Na raiz do projeto:

```bash
docker-compose up --build
```

* A aplicação subirá em `http://localhost:8080`
* RabbitMQ UI em `http://localhost:15672` (user: guest / pass: guest)

### 3. Rodando localmente (sem Docker)

* Suba o RabbitMQ e MongoDB na sua máquina local
* Configure as variáveis de ambiente conforme o `application.properties`
* Execute:

```bash
./mvnw spring-boot:run
```

---

## 📡 Endpoints disponíveis

* `GET /customers/{customerId}/orders` → Valor total de pedidos por cliente + informações do pedido

---

## 📬 Testando a fila

Envie mensagens no formato do exemplo acima para a fila configurada no RabbitMQ.
Depois, consulte os endpoints REST para verificar os resultados.
