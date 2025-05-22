# ✅ Prueba Técnica – API de Gestión de Tareas

Este proyecto es una **API RESTful** desarrollada en **Java 21** con **Spring Boot 3.5**, diseñada para gestionar **proyectos** y sus respectivas **tareas**. Implementa autenticación mediante **JWT** y utiliza **PostgreSQL** para la persistencia de datos.

---

## 📦 Características

- Gestión de **proyectos**
- Gestión de **tareas** asociadas a proyectos
- **Autenticación segura** con JWT
- **Persistencia en base de datos** PostgreSQL
- Pruebas unitarias con **JUnit 5 y Mockito**
- Preparado para futura compatibilidad con **SQL Server**

---

## 🔧 Tecnologías Utilizadas

- Java 21
- Spring Boot 3.5
- Gradle
- PostgreSQL
- JPA (Hibernate)
- JWT (JSON Web Token)

---

## 🚀 Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/jadcve/hualpen-test
cd api-gestion-tareas
```

### 2. Configurar la Base de Datos

Edita el archivo `src/main/resources/application.properties` con tus credenciales de PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_tareas
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update

jwt.secret=mi_clave_secreta
jwt.expiration=86400000
```

### 3. Ejecutar la Aplicación

```bash
./gradlew bootRun
```

La API estará disponible en:  
📍 `http://localhost:8080`

---

## 📁 Endpoints Principales

> Todos los endpoints requieren autenticación JWT, excepto el login.

### 🔐 Autenticación

#### Login
`POST /api/v1/auth/login`

**Body:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}
```

**Usar en siguientes peticiones:**
```
Authorization: Bearer <TOKEN>
```

---

## 📌 Proyectos

### Crear Proyecto
`POST /api/proyectos`
```json
{
  "nombre": "Nombre del proyecto"
}
```

### Listar Proyectos
`GET /api/proyectos`

---

## 📝 Tareas

### Crear Tarea
`POST /api/tareas`
```json
{
  "titulo": "Tarea 1",
  "descripcion": "Descripción opcional",
  "fechaLimite": "2025-05-19",
  "proyectoId": 1
}
```

### Listar Tareas por Proyecto
`GET /api/proyectos/{id}/tareas`

### Marcar Tarea como Completada
`PUT /api/tareas/{id}/completar`

---

## ⚙️ Configuración del Proyecto

Dentro del proyecto encontrarás el archivo `application.properties` con la configuración necesaria para:

- La creación automática de la base de datos:
  ```properties
  spring.jpa.hibernate.ddl-auto=update
  ```
- El `secret` y el `expiration` del token JWT.
- Las credenciales de conexión a la base de datos.

> 🔒 **Importante:** Asegúrate de reemplazar el usuario y contraseña con tus propias credenciales antes de ejecutar el proyecto.

---

## 🌐 URL de la API

La API estará disponible localmente en:  
📍 [http://localhost:8080](http://localhost:8080)

---

## 🧪 Pruebas Unitarias

Este proyecto incluye pruebas unitarias para los servicios utilizando **JUnit 5** y **Mockito**.

Para ejecutarlas con Gradle:

```bash
./gradlew test
```

---

## 📄 Swagger (Documentación Automática)

❌ Actualmente no disponible

Debido a una incompatibilidad entre **Spring Boot 3.5.0-RC1** y **Springdoc OpenAPI 2.5.0**, no es posible generar la documentación Swagger en esta versión.

---

## 👨‍💻 Autor

**Alain Díaz**
