# 📚 Course Management System (EduSync)

A full-stack Course Management System built using **Spring Boot** for the backend and **MySQL** for database management. This project helps administrators manage courses and students efficiently, and allows students to view and enroll in courses.

## 🚀 Features

### 👤 Role-based Access
- **Admin**: Can add, update, delete courses, and view enrollments.
- **Student**: Can view available courses, enroll and view their own enrollments.

### 📦 Backend Functionality
- Add, update, delete courses
- Enroll/Unenroll students to/from courses
- Retrieve enrolled courses by student or course
- Role-based authorization using **JWT**

### 🌐 Optional Frontend
- Built with **React**
- Interfaces for admin and student users
- Dynamic course listing and enrollment management

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **MySQL**
- **Maven**

### Frontend (Optional)
- **React**
- **Axios**
- **Bootstrap**

---

## ⚙️ Setup Instructions

### 🔧 Backend (Spring Boot)
1. Clone the repository:
   ```bash
   git clone https://github.com/chinni-03/course-management-system.git
   cd course-management-system

2. Configure the MySQL database:
   ```application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/cmsdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   security.jwt.secret-key=yoursecretkey
   security.jwt.issuer=yourissuer

3. Run the application:
   ```bash
   mvn spring-boot:run

---

## 📁 Project Structure

```
cms-backend/
├── controller/
├── entity/
├── repository/
├── service/
├── security/ (JWT config)
├── CourseManagementApplication.java
└── resources/
    └── application.properties

cms-frontend/
├── components/
├── pages/
├── App.jsx
└── main.jsx
```

---

## 🔐 API Endpoints

### Courses
- `GET /api/courses`
- `GET /api/courses/{courseId}`
- `POST /api/courses`
- `PUT /api/courses/{courseId}`
- `DELETE /api/courses/{courseId}`

### Enrollment
- `POST /api/enrollments/{studentId}/{courseId}`
- `GET /api/enrollments/student/{studentId}`
- `GET /api/enrollments/course/{courseId}`

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

---

## 🤝 Contributors

- Harshini — Full-stack Developer

---

## 📃 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.

---

## 🙌 Acknowledgements

- Spring Boot Team
- React Docs
- JWT.io
