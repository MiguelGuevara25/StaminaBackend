# ⚡ Stamina - Sistema de Gestión de Gimnasios (Backend)

**Stamina** es una API REST robusta desarrollada en Java con Spring Boot, diseñada para centralizar la administración de gimnasios, con especial enfoque en academias de artes marciales y centros de entrenamiento funcional.

## 🚀 Tecnologías Utilizadas

* **Java 17 & Spring Boot 3**: Núcleo del ecosistema.
* **Spring Security & JWT**: Autenticación y autorización basada en roles (ADMIN, USER).
* **Spring Data JPA**: Persistencia de datos y comunicación con la BD.
* **PostgreSQL**: Base de datos relacional para entornos de producción.
* **ModelMapper**: Para la transformación eficiente entre Entidades y DTOs.
* **Lombok**: Reducción de código boilerplate (Getters, Setters, Constructors).
* **BCrypt**: Encriptación de contraseñas de alta seguridad.

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura limpia dividida en capas para facilitar el mantenimiento y la escalabilidad:

1.  **Entities**: Modelado de la base de datos (User, Role, Plan, Attendance).
2.  **Repositories**: Interfaces de acceso a datos con JPA.
3.  **Services**: Capa de lógica de negocio (validaciones de DNI, email duplicado, lógica de roles).
4.  **DTOs**: Objetos de transferencia de datos para proteger la integridad de las entidades.
5.  **Controllers**: Endpoints de la API REST documentados y protegidos.
6.  **Security**: Configuración de filtros JWT y políticas de CORS.

## 🔐 Seguridad y Roles

El sistema implementa un control de acceso basado en roles (**RBAC**):

* **Público**: Registro y Login.
* **Usuario (Socio)**: Consulta de planes propios y asistencia.
* **Administrador**: Gestión total de socios (CRUD), creación de planes y reportes de asistencia.



## 🛣️ Endpoints Principales

### Autenticación
* `POST /auth/login` - Genera un token JWT tras validar credenciales.

### Gestión de Socios (Solo ADMIN)
* `GET /api/users` - Lista todos los socios registrados.
* `GET /api/users/{id}` - Obtiene el detalle de un socio.
* `POST /api/users` - Registra un nuevo socio (con validación de DNI/Email único).
* `PUT /api/users/{id}` - Actualiza datos de un socio existente.
* `DELETE /api/users/{id}` - Elimina (o suspende) a un socio.

### Gestión de Planes
* `GET /api/plans` - Lista los planes de entrenamiento disponibles.
* `POST /api/plans` - Crea nuevos planes (solo Admin).

## 🛠️ Configuración Local

1. Clona el repositorio: `git clone https://github.com/MiguelGuevara25/StaminaBackend.git`
2. Configura tu base de datos en `src/main/resources/application.properties`.
3. Ejecuta el comando: `./mvnw spring-boot:run`
4. La API estará disponible en `http://localhost:8080`.

---
Desarrollado con ❤️ por **Miguel Guevara** - Fullstack Developer.
