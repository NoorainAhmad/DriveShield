# 🚗 Drive Shield – Vehicle Management System

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-14+-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

**Drive Shield** is a full-stack, role-based Vehicle Management System designed to automate and streamline vehicle insurance and underwriting operations. The system provides dedicated functionalities for Admins and Underwriters, ensuring secure access, efficient policy handling, and scalable data management.

---

## 📌 Project Overview

Drive Shield serves as a centralized platform to manage vehicle insurance policies, underwriter accounts, and insurance history records. The application follows **MVC architecture**, exposes secure **REST APIs**, and implements **JWT-based authentication** to protect endpoints.

### 🔑 Key Functionalities
* **Secure Authentication:** JWT-based authentication and Role-Based Access Control (RBAC).
* **User Roles:** Distinct dashboards for **Admins** and **Underwriters**.
* **Data Management:** Complete CRUD operations on insurance records.
* **Architecture:** Scalable Backend (Spring Boot) and Responsive Frontend (Angular).

---

## 🛠️ Tech Stack

### Backend
* **Language:** Java
* **Framework:** Spring Boot, Spring MVC
* **Security:** Spring Security, JWT (JSON Web Token)
* **Build Tool:** Maven

### Frontend
* **Framework:** Angular
* **Styling:** HTML, CSS, TypeScript
* **Communication:** HTTP Client (REST API Integration)

### Database
* **Database:** MySQL
* **ORM:** Hibernate / JPA

---

## 👥 User Roles & Features

### 🔹 Admin
* Register new Underwriters.
* Delete or deactivate Underwriters.
* Manage and monitor overall insurance-related data.

### 🔹 Underwriter
* Add new vehicle insurance policies.
* Search for existing insurance policies.
* View insurance history.
* Update own account credentials/password.

---

## 🏗️ System Architecture

The project follows a layered architecture to ensure separation of concerns:

1.  **Controller Layer:** Handles HTTP requests and maps endpoints via REST APIs.
2.  **Service Layer:** Contains core business logic and validations.
3.  **DAO / Repository Layer:** Manages database interactions using JPA/Hibernate.
4.  **Database Layer:** MySQL storage for users, vehicles, and insurance policies.
5.  **Security Layer:** Implements JWT authentication filters and role-based access control.

---

## ▶️ Getting Started

Follow these instructions to set up the project locally.

### Prerequisites
* Java JDK 17+
* Node.js & npm (for Angular)
* MySQL Server
* Maven

### 1. Clone the Repository
```bash
git clone [https://github.com/](https://github.com/)<your-username>/drive-shield.git
cd drive-shield
### 2. Backend Setup (Spring Boot)

1.  Navigate to `src/main/resources/application.properties`.
2.  Update your MySQL credentials:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/drive_shield
    spring.datasource.username=root
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  Build and run the application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4.  The backend server will start at: `http://localhost:8080`

### 3. Frontend Setup (Angular)

1.  Navigate to the frontend directory:

    ```bash
    cd vehicleFrontend
    ```

2.  Install dependencies:

    ```bash
    npm install
    ```

3.  Start the Angular development server:

    ```bash
    ng serve
    ```

4.  Access the UI at: `http://localhost:4200`

---

## 🔐 Security & API

* **Authentication:** All sensitive endpoints are protected using **JWT Tokens**.
* **Authorization:** Endpoints are secured based on roles (`ROLE_ADMIN`, `ROLE_UNDERWRITER`).
* **Testing:** You can test the APIs using **Postman**.
    * **Base URL:** `http://localhost:8080/api`
