
# 🛒 Spring Boot Shopping Cart API

Este proyecto es una API REST de carrito de compras implementada en **Spring Boot**, con base de datos en memoria H2 y buenas prácticas (SOLID, DTOs, separación por capas).

---

## 📦 Endpoints de Productos

### 🔹 `GET /api/products`
Obtiene todos los productos desde la API externa (Fake Store).

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "title": "Product 1",
    "price": 29.99
  }
]
```

---

### 🔹 `GET /api/products/{id}`
Obtiene un producto por ID.

**Ejemplo:**  
`GET http://localhost:8080/api/products/1`

---

## 📑 Endpoints de Órdenes

### 🔸 `POST /api/orders`
Crea una orden.

**Body de ejemplo:**
```json
{
  "client": {
    "name": "Saúl Ponce",
    "email": "saul@example.com"
  },
  "orderDetail": {
    "shippingAddress": "Calle Falsa 123",
    "notes": "Entregar en la puerta"
  },
  "productIds": [1, 2]
}
```

---

### 🔸 `GET /api/orders`
Lista todas las órdenes creadas.

---

### 🔸 `GET /api/orders/{id}`
Obtiene una orden por su ID.  
`GET http://localhost:8080/api/orders/1`

---

## 💳 Endpoint de Pago

### 🟣 `PUT /api/orders/{id}/pay`
Simula el pago de una orden.

**Ejemplo:**  
`PUT http://localhost:8080/api/orders/1/pay`

**Respuesta:**
```json
{
  "message": "Order paid successfully",
  "data": {
    "id": 1,
    "status": "PAID"
  }
}
```

---

## ▶️ Cómo ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

> Asegúrate de tener Java 17+ y Maven instalado, o usa el wrapper incluido.

---

## 🧪 Consola H2

- URL: `http://localhost:8080/h2-console`
- JDBC: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: *(dejar vacío)*

---

## 📬 Contacto

Este proyecto fue desarrollado por **Saúl Ponce** como prueba técnica backend con Spring Boot.