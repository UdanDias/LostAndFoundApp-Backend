# 🔐 Lost and Found Application – Backend API (CMJD - Batch 108/109)

This is the backend implementation of a **Lost and Found System** designed for an educational institute. Developed as part of **Assignment 1** for the **Comprehensive Master Java Developer (CMJD)** course, the application offers full RESTful API support for managing lost/found items, claim requests, and user authentication/authorization.

---

## 🎯 Objectives

- ✅ Implement a clean and scalable **Spring Boot** application.
- ✅ Use **Spring Data JPA** with **MySQL** for data persistence.
- ✅ Secure the application using **JWT** and **Spring Security**.
- ✅ Organize code with clear separation of concerns (Controller, Service, Repository).
- ✅ Follow best practices in logging, exception handling, and RESTful standards.

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **JWT (JSON Web Token)**
- **MySQL**
- **Lombok**
- **Maven**
- **Git & GitHub**

---

## 🗃️ Core Entities & Enums

### 👤 User
- `id`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `dateOfBirth`, `gender`, `role`
- **Role Enum**: `ADMIN`, `STAFF`, `USER`

### 📦 Item
- `id`, `name`, `description`, `date`, `location`, `status`, `user`
- **Status Enum**: `LOST`, `FOUND`, `CLAIMED`

### 📥 Request
- `id`, `item`, `requestedBy`, `status`, `message`, `date`
- **Status Enum**: `PENDING`, `APPROVED`, `REJECTED`

---

## 🔐 Authentication & Authorization

- **JWT Token-based Authentication**
- Endpoints for:
  - `POST /api/auth/signup` – Register new users
  - `POST /api/auth/signin` – Login and receive JWT
- Role-based access restrictions (`@PreAuthorize`)
- Token validation on each request with Spring Security filter chain

---

## 🧪 API Endpoints Overview

### 🔑 AuthController
| Method | Endpoint           | Description           |
|--------|--------------------|-----------------------|
| POST   | `/api/auth/signup` | Register new user     |
| POST   | `/api/auth/signin` | Login and get JWT     |

### 📦 ItemController
| Method | Endpoint            | Description               |
|--------|---------------------|---------------------------|
| GET    | `/api/items`        | Get all items             |
| POST   | `/api/items`        | Add a new item            |
| PUT    | `/api/items/{id}`   | Update an existing item   |
| DELETE | `/api/items/{id}`   | Delete an item            |
| GET    | `/api/items/{id}`   | Get item by ID            |

### 📥 RequestController
| Method | Endpoint               | Description                 |
|--------|------------------------|-----------------------------|
| GET    | `/api/requests`        | Get all requests            |
| POST   | `/api/requests`        | Create a new request        |
| PUT    | `/api/requests/{id}`   | Update request status       |

### 👤 UserController
| Method | Endpoint            | Description              |
|--------|---------------------|--------------------------|
| GET    | `/api/users`        | Get all users (admin)    |
| GET    | `/api/users/{id}`   | Get user by ID           |
| PUT    | `/api/users/{id}`   | Update user profile      |

---

## 🛠️ Setup & Run

### 📦 Prerequisites
- Java 17+
- MySQL 8+
- Maven
- IDE (IntelliJ, Eclipse, etc.)

### 🔧 Steps

1. **Clone the Repository**
```bash
git clone https://github.com/your-username/lost-and-found-backend.git
cd lost-and-found-backend
