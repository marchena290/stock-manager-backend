# 📦 StockManager API: Intelligent Inventory Engine

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Managed-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)

## 📖 Descripción
**StockManager** es un microservicio robusto diseñado para la gestión de suministros industriales. A diferencia de un CRUD básico, este motor integra **reglas de negocio inteligentes** para la toma de decisiones proactivas, como la detección automática de stock crítico y la gestión de estados de inventario.

> **Nota de Desarrollo:** Este proyecto ha sido construido utilizando una metodología de **Desarrollo Asistido por IA (AI-Co-piloting)**, optimizando la arquitectura de software y la calidad del código mediante técnicas avanzadas de Prompt Engineering.

---

## 🚀 Características Principales

### 🧠 Motor de Reglas de Criticidad (ST-05)
El sistema evalúa automáticamente cada entrada de producto para determinar su nivel de riesgo:
- **Cálculo Proactivo:** Si `stockActual < stockMinimo`, el sistema marca el producto con el flag `isCritical`.
- **Auditoría Nativa:** Registro automático de fechas de creación y estados operativos (`ACTIVO`, `INACTIVO`).

### 🐳 Infraestructura como Código (Docker)
Despliegue simplificado mediante contenedores para garantizar la paridad entre entornos:
- **Base de Datos:** PostgreSQL 15+ pre-configurado.
- **Persistencia:** Volúmenes Docker para la seguridad de los datos.

### 🔐 Seguridad y Estándares
- **Autenticación:** JWT (JSON Web Tokens) para sesiones seguras stateless.
- **Arquitectura:** Diseño en capas (Controller, Service, Repository) siguiendo principios **SOLID**.

---

## 🛠️ Stack Tecnológico

| Capa | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java 21 (LTS) |
| **Framework** | Spring Boot 3.5.x |
| **Persistencia** | Spring Data JPA / Hibernate |
| **Base de Datos** | PostgreSQL |
| **Seguridad** | Spring Security + BCrypt |
| **DevOps** | Docker & Docker Compose |

---

## 🛠️ Instalación y Ejecución

Para levantar el entorno completo (API + Base de Datos), asegúrate de tener instalado **Docker** y ejecuta:

```bash
# 1. Clonar el repositorio
git clone [https://github.com/tu-usuario/stock-manager.git](https://github.com/tu-usuario/stock-manager.git)

# 2. Navegar a la carpeta
cd stock-manager

# 3. Levantar servicios
docker-compose up --build
