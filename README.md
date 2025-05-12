# 🏫 ExamMasterfsV0 - The 0th version of exammaster (fullstack  examination management system) 

A role-based Spring Boot application for managing university-level exams with secure JWT authentication, dynamic seating arrangements, and a user-friendly UI using Thymeleaf.

---

## 🚀 Features

### ✅ Authentication & Authorization
- **User Registration**: Register new users (Admin or Student).
- **JWT Login**: Issues a token only if the user is not already logged in.
- **Secure Logout**: Removes or expires the token from in-memory storage.
- **Role-Based Access**:
  - `ADMIN`: Can create exams, manage students/subjects, and allocate seating.
  - `STUDENT`: Can view hall allocations and download hall tickets.

---

## 🎓 User Roles
- `ADMIN`: Full system control.
- `STUDENT`: Restricted to viewing only permitted information.

---

## 🪑 Seating Arrangement
- Auto-assign students to classrooms in round-robin fashion.
- Rebuild seating allocations if needed.
- Generate hall allocation summary for each exam.

---

## 🎫 Hall Ticket System
- View assigned classroom per exam.
- Accessible by students for each scheduled exam.

---

## 🧰 Tech Stack

| Layer              | Technology                            |
|-------------------|----------------------------------------|
| Backend            | Spring Boot, Spring Security, JWT     |
| Frontend (UI)      | Thymeleaf (dynamic server-side HTML)  |
| Authentication     | JWT (stateless security)              |
| Database           | H2 (in-memory for development)        |
| ORM & Persistence  | JPA / Hibernate                       |
| API Testing        | Postman                               |
| Build Tool         | Maven                                 |
