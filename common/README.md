# Módulo Common

Módulo compartilhado que fornece utilitários, configurações e classes base para todos os projetos.

## 📦 Componentes

### 1. DTOs (Data Transfer Objects)

#### ApiResponse<T>
Classe genérica para padronizar respostas de API.

**Campos:**
- `success`: boolean - indica se a operação foi bem-sucedida
- `message`: String - mensagem descritiva
- `data`: T - dados retornados (genérico)
- `timestamp`: LocalDateTime - momento da resposta

**Uso:**
```java
// Resposta de sucesso
ApiResponse<User> response = ApiResponse.success(user);

// Resposta de sucesso com mensagem customizada
ApiResponse<List<User>> response = ApiResponse.success("Usuários encontrados", users);

// Resposta de erro
ApiResponse<Void> response = ApiResponse.error("Usuário não encontrado");
```

### 2. Exceções

#### BusinessException
Exceção customizada para regras de negócio.

**Campos:**
- `code`: String - código de erro opcional
- `message`: String - mensagem de erro

**Uso:**
```java
throw new BusinessException("USUARIO_NAO_ENCONTRADO", "Usuário não existe no sistema");
```

### 3. Configurações

#### CorsConfig
Configuração CORS para permitir comunicação entre diferentes origens.

**Configuração padrão:**
- Permite todas as origens (`*`)
- Métodos: GET, POST, PUT, DELETE, PATCH, OPTIONS
- Todos os headers permitidos
- Max age: 3600 segundos

> **Nota de Segurança**: Em produção, configure origens específicas ao invés de `*`.

## 🔧 Como Usar

### Adicionar dependência no seu projeto

No `pom.xml` do seu projeto, adicione:

```xml
<dependency>
    <groupId>com.adrianuz</groupId>
    <artifactId>common</artifactId>
    <version>${project.version}</version>
</dependency>
```

### Importar classes

```java
import com.adrianuz.common.dto.ApiResponse;
import com.adrianuz.common.exception.BusinessException;
```

## 🎨 Boas Práticas

1. **ApiResponse**: Use sempre para manter consistência nas respostas
2. **BusinessException**: Lance para erros de regra de negócio
3. **Não modifique**: Este módulo é compartilhado - mudanças afetam todos os projetos
4. **Extensibilidade**: Prefira estender classes ao invés de modificá-las

## 🚀 Evoluções Futuras

- [ ] Tratador global de exceções
- [ ] Utilitários de validação
- [ ] Classes para paginação
- [ ] Constantes compartilhadas
- [ ] Formatadores e conversores
- [ ] Clientes HTTP configurados
