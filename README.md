# Health Screening - QR Based Screening Test (Java Spring Boot)

## Overview
# 🩺 Health Screening Backend

A robust, secure, and scalable backend for a QR-based health screening application. This service handles user authentication, role-based access, admin configurations, and data analytics. Built using modern Java and Spring Boot best practices.

---

## 📦 Tech Stack

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security with JWT
  - Spring Validation
  - Spring AOP for logging
- **MySQL** – Primary database
- **Lombok** – Reduces boilerplate
- **Swagger/OpenAPI** – API documentation and testing
- **Maven** – Build tool

---

## ✅ Features

- 🔐 JWT-based authentication and authorization
- 👥 Role-Based Access Control (RBAC)
- 📋 Admin API support for managing:
  - Questions
  - Users
  - Health screening responses and statistics
- 📤 DTOs used for request/response (no direct entity exposure)
- 🌐 Centralized exception handling using `@RestControllerAdvice`
- 📄 OpenAPI documentation via Swagger
- 🪵 Centralized application logging using Spring AOP
- 🛡️ Aligned with OWASP & SOLID development practices

---

## 📁 Project Structure
```
   health-screening-be/
   ├── controller/ → REST Controllers
   ├── service/ → Service Interfaces
   ├── serviceImpl/ → Business Logic Implementations
   ├── repository/ → JPA Repositories
   ├── dto/ → Request & Response DTOs
   ├── entity/ → JPA Entities
   ├── config/ → Security, Swagger Configs
   ├── exception/ → Global Exception Handling
   ├── security/ → JWT Provider, Filter, EntryPoint
   └── resources/
   └── application.properties
```


---

## 🚀 Getting Started

### 1. Prerequisites

Ensure you have the following installed:

- Java 17+
- Maven
- MySQL

### 2. Database Setup

Create a database in MySQL:

```sql
CREATE DATABASE health-screening;
```

# Build
mvn clean install

# Run
mvn spring-boot:run

## License
This project is licensed under the MIT License.