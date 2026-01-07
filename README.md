# Mini Offer Engine - Looplink Coding Exercise

This project is a simplified transaction processing system with a mini offer engine.
It ingests purchase transactions, applies promotional offers, awards non-monetary rewards (stickers),
and exposes APIs to inspect shopper activity.

## Tech Stack
- Java 17+
- Spring Boot
- Spring Web (REST APIs)
- Swagger UI (OpenAPI)

## Prerequisites
- Java 17 or higher
- Maven or Gradle
- Redis

## Setup & Run

1. Clone the repository:
```
git clone <repo-url>
cd <repo-name>
```
2. Build the project:
```
mvn clean install
```
3. Run the application:
```
./mvnw spring-boot:run
```
4. Verify the app is running:
```
Health check: GET http://localhost:8080/health
Swagger UI: http://localhost:8080/swagger-ui.html
```
API Documentation

All APIs are documented and can be exercised via Swagger UI.