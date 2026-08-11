# Architecture

Java 21 Spring Boot microservices (Scenario: multi-service), inspired by common Eureka + Gateway layouts
(such as [nihadamirov/spring-boot-microservices](https://github.com/nihadamirov/spring-boot-microservices))
while remaining a Maven multi-module project tailored for Java 21 + integrated quality tools.

Stack: Spring Boot **3.3.x** + Spring Cloud **2023.0.x** on the `jakarta.*` namespace.

## Services (connected)

| Module | Role | Port |
|--------|------|------|
| `eureka-server` | Service discovery / registration | 8761 |
| `api-gateway` | Spring Cloud Gateway edge routing (`lb://` service ids) | 8088 |
| `customer-service` | Customer REST API | 8081 |
| `product-service` | Product REST API | 8082 |
| `order-service` | Orders; calls customer + product via load-balanced `RestTemplate` | 8083 |

Each domain service uses controller → service → repository layers in one cohesive module.
Persistence is in-memory so `./mvnw test` works without external databases; Docker Compose wires the live service mesh through Eureka.