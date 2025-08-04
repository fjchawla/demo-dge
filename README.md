# 💬 Chat History Microservice

This project is a **Spring Boot-based backend service** for storing chat messages and sessions, designed for use with RAG (Retrieval-Augmented Generation) chatbot systems.

It supports:
Chat session management (rename, favorite, delete)
Message storage with optional context
Secure APIs with API key authentication
Pagination of messages
Dockerized
Swagger API doc
Unit tests for core services

---

## Tech Stack

- Java 21
- Spring Boot 3
- PostgreSQL (via Docker)
- Spring Data JPA
- Springdoc OpenAPI (Swagger)
- Bucket4j (optional rate limiting)
- JUnit 5

---

## 🛠️ Setup Instructions

### Prerequisites

- Java 21
- Maven
- Docker

### Run Locally (using Docker Compose)

```
# Build the JAR
mvn clean package -DskipTests

# Run the stack (app + Postgres)
docker-compose up --build
```

The application path:  
`http://localhost:8080`

---

## 🔐 API Authentication

All endpoints require an API key passed via header:

```
x-api-key: my-secret-key-dge-123
```

You can override the key in your `.env` or Docker environment settings. Default configurations/environment variables are provided in application.yaml & .env

---

## 🧪 API Endpoints

### 🎯 Session Management

| Method | Endpoint                        | Description            |
|--------|---------------------------------|------------------------|
| POST   | `/sessions`                     | Create new session     |
| PATCH  | `/sessions/{id}/rename`         | Rename session         |
| PATCH  | `/sessions/{id}/favorite`       | Toggle favorite        |
| DELETE | `/sessions/{id}`                | Delete session         |
| GET    | `/sessions`                     | List all sessions      |

### 💬 Message Handling

| Method | Endpoint                                         | Description                  |
|--------|--------------------------------------------------|------------------------------|
| POST   | `/sessions/{sessionId}/messages`                 | Post message to session       |
| GET    | `/sessions/{sessionId}/messages?page=0&size=10`  | Get paginated message list   |

---

## 📘 Swagger API Docs

Swagger UI path:

👉 `http://localhost:8080/swagger-ui/index.html`

---

## ⚙️ Environment Variables in .env

```env
DATABASE_HOST=localhost
DATABASE_PORT=5432
DATABASE_NAME=chatdb
DATABASE_USER=postgres
DATABASE_PASSWORD=password
API_KEY=supersecret123
```

