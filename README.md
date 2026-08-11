# Java Spring Boot Microservice

Spring Boot **microservices** for **Java 11 only**.

Layout inspired by [nihadamirov/spring-boot-microservices](https://github.com/nihadamirov/spring-boot-microservices)
(Eureka discovery, API gateway, customer/product/order services, Docker Compose) —
implemented as a **Maven multi-module** reactor for Java 11 with integrated quality tools
(not a copy of that Gradle/Zuul/Mongo stack).

## Requirements

- JDK **11** (see `.sdkmanrc`)
- Maven Wrapper (`./mvnw` / `mvnw.cmd`)

## Project structure

```
.
├── eureka-server/          Service discovery
├── api-gateway/            Spring Cloud Gateway
├── customer-service/       Customer REST API
├── product-service/        Product REST API
├── order-service/          Order REST API (+ service clients)
├── config/                 Checkstyle / PMD / SpotBugs
├── documentation/
├── scripts/ck|git|tools/
├── docker-compose.yml
├── pom.xml                 Parent reactor (Java 11 + tools)
└── mvnw / mvnw.cmd
```

## Building from source

```bash
./mvnw clean test
```

Run locally (separate terminals), start Eureka first:

```bash
./mvnw -pl eureka-server spring-boot:run
./mvnw -pl customer-service spring-boot:run
./mvnw -pl product-service spring-boot:run
./mvnw -pl order-service spring-boot:run
./mvnw -pl api-gateway spring-boot:run
```

Or with Docker:

```bash
./mvnw -DskipTests package
docker compose up --build
```

## Quality tools

See `documentation/TOOLS.md`. All of: CK, CPD, Checkstyle, Git, JaCoCo,
OWASP-Dependency-Check, PIT, PMD, SpotBugs, Static-DU-JaCoCo-composite, diff-cover.

```bash
bash scripts/tools/run_tools.sh
```

## License

Apache License 2.0 — see `LICENSE.txt`.