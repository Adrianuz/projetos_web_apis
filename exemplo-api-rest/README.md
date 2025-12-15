# Exemplo API REST

API REST simples usando Spring Boot para demonstrar a estrutura do repositório.

## 📋 Descrição

Este projeto é um exemplo de uma Web API REST que gerencia produtos. Demonstra:
- Uso do módulo `common` para respostas padronizadas
- Operações CRUD (Create, Read, Update, Delete)
- Tratamento de exceções com `BusinessException`
- Estrutura básica de Controller, Service e Model

## 🚀 Como Executar

### Opção 1: Usando Maven
```bash
cd exemplo-api-rest
mvn spring-boot:run
```

### Opção 2: Build e execução do JAR
```bash
cd exemplo-api-rest
mvn clean package
java -jar target/exemplo-api-rest-1.0.0-SNAPSHOT.jar
```

A API estará disponível em: `http://localhost:8080`

## 📡 Endpoints Disponíveis

### Listar todos os produtos
```http
GET http://localhost:8080/api/produtos
```

**Resposta:**
```json
{
  "success": true,
  "message": "Produtos listados com sucesso",
  "data": [
    {
      "id": 1,
      "nome": "Notebook",
      "descricao": "Notebook Dell Inspiron",
      "preco": 3500.00
    }
  ],
  "timestamp": "2024-01-15T10:30:00"
}
```

### Buscar produto por ID
```http
GET http://localhost:8080/api/produtos/{id}
```

### Criar novo produto
```http
POST http://localhost:8080/api/produtos
Content-Type: application/json

{
  "nome": "Monitor",
  "descricao": "Monitor 27 polegadas",
  "preco": 1200.00
}
```

### Atualizar produto
```http
PUT http://localhost:8080/api/produtos/{id}
Content-Type: application/json

{
  "nome": "Monitor 4K",
  "descricao": "Monitor 27 polegadas 4K",
  "preco": 1800.00
}
```

### Deletar produto
```http
DELETE http://localhost:8080/api/produtos/{id}
```

## 🧪 Testando a API

### Usando curl

**Listar produtos:**
```bash
curl http://localhost:8080/api/produtos
```

**Criar produto:**
```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Webcam","descricao":"Webcam Full HD","preco":250.00}'
```

**Buscar produto:**
```bash
curl http://localhost:8080/api/produtos/1
```

**Atualizar produto:**
```bash
curl -X PUT http://localhost:8080/api/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Notebook Pro","descricao":"Notebook Dell Inspiron i15","preco":4000.00}'
```

**Deletar produto:**
```bash
curl -X DELETE http://localhost:8080/api/produtos/1
```

## 📦 Estrutura do Projeto

```
exemplo-api-rest/
├── src/
│   ├── main/
│   │   ├── java/com/adrianuz/exemploapi/
│   │   │   ├── Application.java          # Classe principal
│   │   │   ├── controller/
│   │   │   │   └── ProdutoController.java # Controlador REST
│   │   │   ├── service/
│   │   │   │   └── ProdutoService.java    # Lógica de negócio
│   │   │   └── model/
│   │   │       └── Produto.java           # Modelo de dados
│   │   └── resources/
│   │       └── application.properties     # Configurações
│   └── test/
└── pom.xml
```

## 🔑 Conceitos Demonstrados

1. **Uso do módulo common:**
   - `ApiResponse<T>` para respostas padronizadas
   - `BusinessException` para exceções de negócio
   - Configuração CORS compartilhada

2. **Arquitetura em camadas:**
   - Controller: Recebe requisições HTTP
   - Service: Contém lógica de negócio
   - Model: Representa os dados

3. **RESTful API:**
   - Verbos HTTP apropriados (GET, POST, PUT, DELETE)
   - Status codes HTTP corretos
   - URLs semânticas

4. **Armazenamento em memória:**
   - Dados não persistem após reiniciar a aplicação
   - Ideal para demonstração e testes

## 🔄 Próximos Passos

Para evoluir este projeto, você pode:
- [ ] Adicionar banco de dados (H2, PostgreSQL, MySQL)
- [ ] Implementar validação de dados (Bean Validation)
- [ ] Adicionar testes unitários e de integração
- [ ] Implementar paginação nas listagens
- [ ] Adicionar documentação com Swagger/OpenAPI
- [ ] Implementar autenticação e autorização
- [ ] Adicionar cache (Redis)
- [ ] Criar Dockerfile para containerização
