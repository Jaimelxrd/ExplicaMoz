# ExplicaMoz API

ExplicaMoz é uma plataforma de gestão de conteúdo educacional focada no currículo moçambicano, abrangendo da 6ª à 12ª classe.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security & JWT**
- **PostgreSQL**
- **Lombok**
- **Maven**
- **Docker**
- **GitHub Actions**

## 📋 Funcionalidades

- **API Pública (v1)**:
  - Listagem paginada de conteúdos publicados.
  - Busca textual em títulos e descrições.
  - Busca de conteúdos por classe, disciplina e slug.
  - Sugestão de conteúdos relacionados.
- **Painel Administrativo (v1)**:
  - Autenticação via JWT (Stateless).
  - CRUD completo de conteúdos com validação.
  - Geração automática de URL amigável (slug) a partir do título.
  - Gestão de metadados para SEO.

## 🛠️ Como executar

### Localmente
1. **Requisitos**: JDK 17, PostgreSQL, Maven.
2. **Configuração**: Ajuste `src/main/resources/application.properties`.
3. **Execução**:
   ```bash
   ./mvnw spring-boot:run
   ```

### Via Docker
1. **Build da imagem**:
   ```bash
   docker build -t explicamoz-api .
   ```
2. **Execução**:
   ```bash
   docker run -p 8080:8080 explicamoz-api
   ```

## 🔌 Endpoints Principais (v1)

### Público
- `GET /api/v1/conteudos/publicados`: Lista paginada de conteúdos.
- `GET /api/v1/conteudos/busca?q={termo}`: Busca textual paginada.
- `GET /api/v1/conteudos/slug/{slug}`: Conteúdo específico via slug.

### Administrativo
- `POST /api/v1/admin/login`: Login e obtenção de Token JWT.
- `GET /api/v1/admin/conteudos`: Lista administrativa paginada.
- `POST /api/v1/admin`: Criar conteúdo.

## 📄 Documentação (Swagger)
Acesse a documentação interativa em: `http://localhost:8080/swagger-ui.html`

## 🛡️ Tratamento de Erros
A API utiliza um formato padrão para erros:
```json
{
  "timestamp": "2024-02-04T12:00:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Mensagem detalhada",
  "path": "/api/v1/..."
}
```

## 📝 Próximos Passos
- [x] Implementar Spring Security com JWT.
- [x] Adicionar paginação e busca textual.
- [x] Integrar Swagger.
- [x] Containerização com Docker.
- [x] Automação com CI/CD.
