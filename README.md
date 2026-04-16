# 📇 Smart Contact Manager (Spring Boot)

A full-stack **Contact Management Web Application** built using **Spring Boot**, **Thymeleaf**, and **MySQL**.
It allows users to securely manage their contacts with authentication, search, pagination, and modern UI features.

---

## 🚀 Features

* 🔐 User Authentication (Spring Security)
* 🌐 Google Login (OAuth2 Integration)
* 📇 Contact Management (Add, View, Edit, Delete)
* 🔍 Search Contacts (by multiple fields)
* 📄 Pagination & Sorting
* 🖼️ Image Upload & Preview
* 🌗 Dark/Light Theme Toggle
* 📢 Flash Messages (Session-based alerts)
* 🎨 Responsive UI (Tailwind CSS + Flowbite)

---

## 🏗️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA (Hibernate)
* Spring Security
* OAuth2 Client

### Frontend

* Thymeleaf
* Tailwind CSS
* Flowbite
* Font Awesome

### Database

* MySQL

---

## 🧱 Project Structure

```
src/main/java/com/scm/
│
├── controllers       # Handles HTTP requests
├── services          # Business logic
|   ├── serviceImpl   # Service implementations
├── repositories      # JPA repositories
├── entities          # JPA entities (User, Contact)
├── forms             # Form binding classes
├── config            # Security & OAuth configuration
└── helpers           # Utility classes (SessionHelper, Message), Custom exceptions
```

---

## ⚙️ Setup & Installation

### 1. Clone Repository

```bash
git clone https://github.com/gunjan-n/Smart-Contact-Manager.git
cd smart-contact-manager
```

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Google OAuth2 Login

* Configure in `application.properties`:

```properties
spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET
spring.security.oauth2.client.registration.google.scope=email,profile
```

---

### 4. Run the Application

```bash
mvn spring-boot:run
```

App will start at:

```
http://localhost:8080
```

---

## 📇 Contact Management

* Add new contact with image
* Edit existing contact
* Delete with confirmation dialog
* View contact details in modal
* Paginated contact list
* Search contacts

---

## 🧠 Key Concepts Implemented

* MVC Architecture
* Dependency Injection
* ORM with JPA/Hibernate
* Custom UserDetails & UserDetailsService
* Form Validation (Jakarta Validation)
* Session Handling
* File Upload Handling

---

## 🎨 UI Highlights

* Tailwind CSS utility-first design
* Flowbite components for faster UI
* Dark/Light theme using Local Storage
* Responsive design for all devices

---

## 🙌 Author

**Gunjan Naraniya**

* GitHub: https://github.com/gunjan-n
* LinkedIn: https://linkedin.com/in/gunjan-naraniya

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and feel free to contribute!
