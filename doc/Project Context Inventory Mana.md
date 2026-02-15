📂 Project Context: Inventory Management System (MVP)
🎯 Objetivo

Desarrollar un sistema de gestión de inventario proactivo con Java 21 que automatice alertas de stock, gestione productos de forma segura y permita autenticación basada en roles.

El proyecto está orientado a backend profesional y buenas prácticas modernas.

💻 Tech Stack

Backend: Spring Boot 3.5.10

Lenguaje: Java 21

Base de Datos: PostgreSQL 16

Caché: Redis 7

Seguridad: Spring Security + JWT + BCrypt

Multimedia: Cloudinary SDK

Notificaciones: Spring Mail + Thymeleaf (Async)

Infraestructura: Docker + Docker Compose

ORM: Spring Data JPA (Hibernate)

🐳 Infraestructura (Docker)

El sistema se ejecuta en entorno Dockerizado:

PostgreSQL 16 en contenedor Docker.

Redis 7 en contenedor Docker.

La base de datos se crea automáticamente mediante POSTGRES_DB.

Las credenciales se gestionan con variables de entorno.

Hibernate gestiona la creación de tablas (ddl-auto=update en dev).

No se utilizan scripts SQL manuales para crear tablas.

🌱 Entornos

dev: Docker local + Hibernate ddl-auto=update

prod: Base de datos externa + ddl-auto=validate

🗄️ Modelo de Datos (Gestionado por JPA/Hibernate)

Las tablas son generadas automáticamente a partir de entidades JPA.

Entidad: User

Campos:

id → Long (Identity)

username → String (unique)

password → String (BCrypt hash)

role → Enum (ADMIN, USER)

createdAt → LocalDateTime

Reglas:

username único.

role persistido con @Enumerated(EnumType.STRING).

Entidad: Product

Campos:

id → Long (Identity)

name → String

description → String

price → BigDecimal ( > 0 )

stockActual → Integer ( >= 0 )

stockMinimo → Integer (default 5)

imageUrl → String

isCritical → Boolean

status → Enum (ACTIVE, INACTIVE)

createdAt → LocalDateTime

Reglas de negocio:

Si stockActual < stockMinimo → isCritical = true

Si stockActual == 0 → status = INACTIVE

Si stockActual > 0 → status = ACTIVE

📋 RoadMap de Desarrollo (Backlog MVP)
ID	Feature	Detalle Técnico
ST-01	Infraestructura	Configuración Docker, conexión a PostgreSQL y Redis
ST-02	Seguridad	Implementación de JWT, BCrypt y SecurityFilterChain
ST-03	Inventario	CRUD de Productos con validaciones Jakarta
ST-04	Storage	Integración con Cloudinary
ST-05	Motor de Stock	Lógica automática de estado y criticidad
ST-06	Email Engine	Envío @Async de alertas usando Thymeleaf
🏗️ Reglas de Arquitectura

Arquitectura en capas:
Controller → Service → Repository

DTOs inmutables usando Java Records.

Configuración mediante application.yml usando variables ${ENV_VAR}.

No hardcodear credenciales.

Enumeraciones persistidas con EnumType.STRING.

Separación clara entre:

Infraestructura (Docker)

Persistencia (JPA)

Lógica de negocio (Service)

Seguridad (JWT)

🎯 Estado Esperado del MVP

El sistema debe permitir:

Autenticación segura con JWT.

Gestión CRUD de productos.

Cálculo automático de estado de inventario.

Envío de alertas por email.

Gestión dockerizada lista para despliegue.