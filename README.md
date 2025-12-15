# Projetos Web APIs

Compilado de projetos em JAVA incluindo Web APIs, Microserviços e outros projetos. Utilizando majoritariamente o Spring Boot.

## 📋 Descrição

Este repositório foi criado para hospedar múltiplos projetos Java independentes, permitindo:
- Desenvolvimento de Web APIs RESTful
- Implementação de Microserviços
- Projetos didáticos e experimentais
- Comunicação entre projetos quando necessário

## 🏗️ Estrutura do Repositório

```
projetos-web-apis/
├── common/                    # Módulo compartilhado entre projetos
│   └── src/
│       └── main/java/com/adrianuz/common/
│           ├── dto/          # DTOs compartilhados (ex: ApiResponse)
│           ├── exception/    # Exceções customizadas
│           └── config/       # Configurações compartilhadas (ex: CORS)
├── [projeto-1]/              # Primeiro projeto independente
├── [projeto-2]/              # Segundo projeto independente
└── pom.xml                   # POM pai para gerenciar todos os módulos
```

## 🚀 Tecnologias Utilizadas

- **Java**: 17+
- **Spring Boot**: 3.2.0
- **Maven**: Gerenciamento de dependências e build multi-módulo
- **Spring Web**: Para criação de APIs REST

## 📦 Módulo Common

O módulo `common` contém código compartilhado que pode ser usado por todos os projetos:

### Componentes Disponíveis:

1. **ApiResponse<T>**: Classe para respostas padronizadas da API
   - Uso: `ApiResponse.success(data)` ou `ApiResponse.error("mensagem")`
   - Inclui: status, mensagem, dados e timestamp

2. **BusinessException**: Exceção customizada para regras de negócio
   - Pode incluir código de erro customizado
   - Facilita tratamento centralizado de exceções

3. **CorsConfig**: Configuração CORS para permitir comunicação entre serviços

## 🛠️ Como Adicionar um Novo Projeto

### Passo 1: Criar a estrutura do projeto

```bash
mkdir nome-do-projeto
cd nome-do-projeto
mkdir -p src/main/java/com/adrianuz/nomedoprojeto
mkdir -p src/main/resources
mkdir -p src/test/java/com/adrianuz/nomedoprojeto
```

### Passo 2: Criar o pom.xml do projeto

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.adrianuz</groupId>
        <artifactId>projetos-web-apis</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>nome-do-projeto</artifactId>
    <packaging>jar</packaging>

    <name>Nome do Projeto</name>
    <description>Descrição do projeto</description>

    <dependencies>
        <!-- Dependência do módulo comum (opcional) -->
        <dependency>
            <groupId>com.adrianuz</groupId>
            <artifactId>common</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Passo 3: Adicionar o módulo ao pom.xml raiz

No arquivo `pom.xml` na raiz do repositório, adicione o novo módulo:

```xml
<modules>
    <module>common</module>
    <module>nome-do-projeto</module>  <!-- Adicione esta linha -->
</modules>
```

### Passo 4: Criar a classe Application

```java
package com.adrianuz.nomedoprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Passo 5: Configurar application.properties

```properties
spring.application.name=nome-do-projeto
server.port=8080  # Escolha uma porta diferente para cada projeto
```

## 🔄 Comunicação Entre Projetos

Para permitir que projetos se comuniquem:

### Opção 1: RestTemplate ou WebClient

```java
@Service
public class OutroProjetoService {
    
    private final RestTemplate restTemplate;
    
    public OutroProjetoService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    
    public ApiResponse<?> chamarOutroServico() {
        String url = "http://localhost:8081/api/endpoint";
        return restTemplate.getForObject(url, ApiResponse.class);
    }
}
```

### Opção 2: Usar o módulo Common

Compartilhe DTOs e interfaces através do módulo `common`:

1. Adicione a dependência do `common` no seu projeto
2. Use as classes compartilhadas como `ApiResponse`
3. Mantenha contratos de API consistentes

### Opção 3: Microserviços com Service Discovery (futuro)

Para arquiteturas mais complexas, poderemos adicionar:
- Eureka Server (Service Discovery)
- API Gateway
- Config Server

## 🏃 Como Executar

### Build de todos os projetos:
```bash
mvn clean install
```

### Build de um projeto específico:
```bash
cd nome-do-projeto
mvn spring-boot:run
```

### Executar testes:
```bash
mvn test
```

## 📝 Convenções de Código

- Use pacotes no formato: `com.adrianuz.nomedoprojeto`
- Siga as convenções de nomenclatura Java (camelCase, PascalCase)
- Documente classes e métodos públicos
- Escreva testes para funcionalidades críticas
- Use o módulo `common` para código compartilhado

## 🎯 Roadmap

- [x] Estrutura inicial do repositório
- [x] Módulo common com utilitários básicos
- [ ] Projeto exemplo: API CRUD simples
- [ ] Projeto exemplo: Microserviço de autenticação
- [ ] Projeto exemplo: API com integração de banco de dados
- [ ] Implementação de Service Discovery (Eureka)
- [ ] API Gateway
- [ ] Documentação com Swagger/OpenAPI

## 📄 Licença

Este projeto está sob a licença especificada no arquivo LICENSE.

## 👤 Autor

Adrianuz

---

**Nota**: Este é um repositório de estudo e experimentação. Os projetos começam simples e aumentam em complexidade com o tempo.
