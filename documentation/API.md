# API Overview

Prefer the gateway (`http://localhost:8088`):

- `GET/POST /customers`, `GET/PUT/DELETE /customers/{id}`
- `GET/POST /products`, `GET/PUT/DELETE /products/{id}`
- `GET/POST /orders`, `GET /orders/{id}`

Direct service ports: customer `8081`, product `8082`, order `8083`.
Eureka dashboard: `http://localhost:8761`