# Spring em CRUD

## Descrição

O Nome do Seu Projeto é uma aplicação de gerenciamento de produtos desenvolvida com o framework Spring Boot. Ela fornece uma plataforma para adicionar, listar, atualizar e excluir produtos.
Essa aplicação foi projetada para atender às necessidades de gerenciamento de produtos de uma loja online ou qualquer negócio que lide com estoques de produtos, e pode facilmente ser adaptado
para uma empresa que precisa controlar os dados referentes a seus clientes.

## Tecnologias Utilizadas

- Spring Boot: Framework de desenvolvimento Java para criar aplicativos Java facilmente.
- MySQL : Banco de Dados Utilizado para armazenamento das informações.
- Postman : Utilizado para teste das funcionalidades da API.

## Pré-Requisitos

Para configurar e executar este projeto, você precisará do seguinte:

- JDK (Java Development Kit): Instale o JDK 8 ou superior.

- IDE de Desenvolvimento: Use uma IDE de desenvolvimento Java, como Eclipse ou IntelliJ IDEA.

- Banco de Dados: Configure um banco de dados (por exemplo, MySQL) e atualize as configurações no arquivo application.properties.

## Documentação

## Base URL

A URL base para acessar a API é: `https://localhost:8090/products`

## Autenticação

A API não requer autenticação para operações públicas. No entanto, operações mais avançadas podem requerer autenticação, dependendo da configuração do servidor.

## Endpoints

### Cadastrar um Produto

- **URL:** `/products`
- **Método:** `POST`
- **Descrição:** Cadastra um novo produto no sistema.
- **Corpo da Requisição:**
  - `nome` (string, obrigatório): Nome do produto.
  - `value` (decimal, obrigatório): Preço do produto.
  - `idProduct` (UUID, gerado automáticamente): ID único do produto.

**Exemplo de Requisição:**

```json
{
  "nome": "Produto A",
  "value": 19.99,
}
```

**Resposta de Sucesso:**
**Código:** 201(Created)
**Corpo da Resposta:**
```json
{
  "id": "7d97d7a0-7c3d-4b44-8b72-23be76f7c17c",
  "nome": "Produto A",
  "value": 19.99
}
```
**Resposta de Erro:**
**Código:** 400(Bad Request)

### Listar Todos Produtos

- **URL:** `/products`
- **Método:** `GET`
- **Descrição:** Retorna a lista com todos os produtos do sistema.

**Resposta de Sucesso:**
**Código:** 200(OK)
**Corpo da Resposta:**
```json
[
    {
        "idProduct": "27988e15-c357-4c43-9fbf-4521bdd8cb3e",
        "name": "Product A",
        "deleted_at": null,
        "updated_at": "2023-08-29",
        "value": 1505.00
    },
    {
        "idProduct": "d5225347-95d3-4797-8be9-9ac84aadfc06",
        "name": "ProductB",
        "deleted_at": null,
        "updated_at": null,
        "value": 1000.00
    }
]
```

# A ser finalizado...
