# 📘 Result Management System — TECHMAHINDRA 2025

This project is a **Result Management System (RMS)** developed as part of the TECHMAHINDRA 2025 initiative. It allows admin users to upload and manage student results, view performance reports, and generate marksheets. The system is built using **Spring Boot**, **Thymeleaf**, and **MySQL**, and follows the MVC architecture.

---

## 🔍 Overview

- Admin Panel to manage students, courses, and semester.
- Faculty can upload results in bulk using Excel templates.
- Students View individual and department-wise performance.
- Export results to downloadable formats.
- Responsive UI using Bootstrap and Thymeleaf.

---

## 🚀 Features

- ✅ Admin authentication
- ✅ Add, update, and delete student details
- ✅ Upload marks using Excel templates
- ✅ View student performance per semester
- ✅ Filter by department and course
- ✅ Generate and download marksheets
- ✅ View all courses and credits

---

## 🛠️ Tech Stack

| Layer        | Technology          |
|--------------|---------------------|
| Backend      | Spring Boot         |
| Frontend     | Thymeleaf, Bootstrap|
| Database     | MySQL               |
| Build Tool   | Maven               |
| Language     | Java                |

---

## 📁 Project Structure

TECHMAHINDRA-2025/  
└── result-management/  
  ├── src/  
  │  ├── main/  
  │  │  ├── java/  
  │  │  │  └── com/result/management/  
  │  │  │    ├── controller/    # Controllers for admin & results  
  │  │  │    ├── model/        # Entity classes (Student, Course, etc.)  
  │  │  │    └── service/      # Business logic and helpers  
  │  ├── resources/  
  │  │  ├── templates/    # Thymeleaf HTML templates  
  │  │  └── application.properties # App configuration  
  ├── pom.xml       # Maven build configuration


---
## 🔐 Login Credentials

| User Role | Username | Password   | Notes                          |
|-----------|----------|------------|--------------------------------|
| Admin     | admin    | admin123   | Full access to upload, edit, and manage results |
| Faculty   | faculty1 | faculty123 | Access to view and upload marks for assigned subjects |
| Student   | student1 | student123 | View personal results and performance |

> 📝 These credentials can be updated manually in the database or configured via the user registration/login module.

---

## 📄 Data Upload Format

The system supports bulk data upload via Excel files. Below are the required formats for each type of data:

---

### 📘 1. Student Upload Format

| Reg No | Name        | Department | Semester | Email               |
|--------|-------------|------------|----------|---------------------|
| 1001   | John Doe    | CSE        | 4        | john@example.com    |
| 1002   | Jane Smith  | ECE        | 3        | jane@example.com    |

---

### 📘 2. Course Upload Format

| Course Code | Course Name         | Credits | Department |
|-------------|---------------------|---------|------------|
| CSE101      | Data Structures     | 4       | CSE        |
| ECE201      | Digital Electronics | 3       | ECE        |

---

### 📘 3. Result Upload Format

| Reg No | Name        | Subject 1 | Subject 2 | Subject 3 | Total | Grade |
|--------|-------------|-----------|-----------|-----------|-------|--------|
| 1001   | John Doe    | 80        | 75        | 85        | 240   | A      |
| 1002   | Jane Smith  | 60        | 70        | 65        | 195   | B      |

> ✅ Ensure that Reg No matches the student database before uploading results.

---


To upload:

Navigate to "Upload Results"

Choose Excel file

Click Submit

---

📌 Future Enhancements
🖨 Export results to PDF

📧 Email notification system

📱 Mobile app version

📊 Interactive charts for performance analytics
