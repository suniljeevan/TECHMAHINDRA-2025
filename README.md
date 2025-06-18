** 🏫 Admission Management System**
 
A Spring Boot-based Microservice project to manage the end-to-end admission process for educational institutions. 
The system handles authentication, student data, application tracking, and provides secure APIs for managing user roles and permissions

**📖 Description**

The Admission Management System (AMS) automates the student admission workflow using RESTful microservices. 
It includes secure login via JWT tokens, role-based access control, and provides interfaces for admin and student modules.

** 🚀 Features**

- 🔐 Secure JWT-based Authentication
- 👨‍🎓 Student Registration & Profile Management
- 🧑‍💼 Admin Dashboard for Admission Control
- 🗂️ Role-based Access Control (RBAC)
- 📨 Application Status Tracking
- 📡 RESTful API Communication
- 📄 Microservices Architecture with Independent Modules (AUTHENTICATION MICROSERVICE)


** 🧑‍💻 Tech Stack**

**Backend:**
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- JWT

**Other Tools:**
- Postman (API Testing)
- Git & GitHub
- Spring Tool Suite


** 📂 Project Structure**
 ![image](https://github.com/user-attachments/assets/add5f0c8-0d32-4176-b74a-d5752fd2afd0)
 ![image](https://github.com/user-attachments/assets/3f2ba43a-c3b6-4de1-b8bb-7b1da77e338a)

** 🧪 How to Use**
 UPDATE application.properties file with your database and password

**Run on Spring Tool Suite**
- Run as
         - Maven clean
         - Maven Install
         - Spring Boot App
- In your default browser, type localhost:3304
- For AUTHENTICATION MICROSERVICES'S verification,
         - Open Postman
         - POST data
         - URL - http://localhost:9954/api/login
         - select body, raw, and then type
         - {
                "email": "  ",
                "password": "  "
            }
         - Send the request by updating the existing credentials, and you will recieve the role of particular user and a jwt token is generated 



