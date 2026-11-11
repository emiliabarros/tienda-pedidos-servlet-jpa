# Tienda — API de Pedidos con Servlets, JPA/Hibernate y DTOs

Aplicación web de dos partes: un backend Java que expone los pedidos de una tienda como JSON, y un frontend en TypeScript que los consume y muestra el detalle de cada uno. Trabajo práctico grupal (Grupo 4).

**Integrantes:** Emilia, Agustin, Guadalupe, Miguel

## Stack

| Capa | Tecnologías |
|---|---|
| Backend | Java 21, Jakarta Servlet 6, Hibernate 6.3 / Jakarta Persistence 3, MySQL, Lombok, Gson, Maven (WAR) |
| Frontend | TypeScript, HTML, CSS, `fetch` API |

## Arquitectura

Backend en capas, con DTOs para no exponer las entidades directamente:

```
tienda-lombok-dto/src/main/java/com/tienda/
├── model/        # Entidades JPA: Cliente, Pedido, Producto
├── dto/          # PedidoDTO, ProductoDTO
├── mapper/       # MapperUtil: entidad → DTO
├── managerdb/    # Acceso a datos con EntityManager
├── service/      # Lógica de negocio y serialización a JSON
└── servlet/      # PedidoServlet (@WebServlet("/pedidos"))
```

## Funcionalidades

- `GET /pedidos`: lista todos los pedidos.
- `GET /pedidos?id={id}`: devuelve un pedido con sus productos; responde `400` si el ID es inválido.
- Cabeceras CORS para que el frontend pueda consumir la API desde otro origen.
- Frontend: tabla de pedidos (fecha, cliente, total) con botón para cargar los datos y vista de detalle por pedido.

## Cómo correrlo

### Backend

1. Crear en MySQL una base llamada `tienda` (puerto `3306`; las tablas se generan con `hbm2ddl.auto=update`).
2. Revisar usuario y contraseña en `src/main/resources/META-INF/persistence.xml`.
3. Generar el WAR y desplegarlo en un servidor compatible con Jakarta EE 10 (por ejemplo, Tomcat 10+):

```bash
cd tienda-lombok-dto
mvn clean package
```

El frontend espera la API en `http://localhost:8082/tienda/pedidos`: configurá el puerto `8082` y el contexto `/tienda` en el servidor, o cambiá la URL en `Front-tienda/app.ts`.

### Frontend

Abrir `Front-tienda/index.html` en el navegador. `app.js` ya está compilado; para regenerarlo desde `app.ts`:

```bash
cd Front-tienda
npx tsc
```
