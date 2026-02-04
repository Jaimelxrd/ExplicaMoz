# ExplicaMoz API

ExplicaMoz é uma plataforma de gestão de conteúdo educacional focada no currículo moçambicano, abrangendo da 6ª à 12ª classe.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📋 Funcionalidades

- **API Pública**:
  - Listagem de conteúdos publicados.
  - Busca de conteúdos por classe, disciplina e slug.
  - Sugestão de conteúdos relacionados.
- **Painel Administrativo**:
  - Autenticação simples (MVP).
  - CRUD completo de conteúdos.
  - Gestão de metadados para SEO.

## 🛠️ Como executar

1. **Requisitos**:
   - JDK 17
   - PostgreSQL instalado e rodando
   - Maven

2. **Configuração do Banco de Dados**:
   - Crie um banco de dados chamado `explicamoz_db`.
   - Ajuste as credenciais no arquivo `src/main/resources/application.properties` se necessário.

3. **Execução**:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🔌 Endpoints Principais

### Público
- `GET /api/conteudos/publicados`: Lista todos os conteúdos publicados.
- `GET /api/conteudos/{id}`: Detalhes de um conteúdo por ID.
- `GET /api/conteudos/classe/{classe}`: Conteúdos filtrados por classe.
- `GET /api/conteudos/slug/{slug}`: Conteúdo específico via URL amigável.

### Administrativo
- `POST /api/admin/login`: Realiza login administrativo.
- `GET /api/admin/conteudos`: Lista todos os conteúdos (incluindo rascunhos).
- `POST /api/admin`: Cria novo conteúdo.
- `PUT /api/admin/{id}`: Atualiza conteúdo existente.
- `DELETE /api/admin/{id}`: Remove um conteúdo.

## 📝 Próximos Passos (Backlog)

- [ ] Implementar Spring Security com JWT.
- [ ] Adicionar paginação nos endpoints de listagem.
- [ ] Integrar Swagger (OpenAPI) para documentação interativa.
- [ ] Implementar busca textual.
