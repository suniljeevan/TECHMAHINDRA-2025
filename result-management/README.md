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
│ ├── main/
│ │ ├── java/
│ │ │ └── com/result/management/
│ │ │ ├── controller/ # Controllers for admin & results
│ │ │ ├── model/ # Entity classes (Student, Course, etc.)
│ │ │ └── service/ # Business logic and helpers
│ │ └── resources/
│ │ ├── templates/ # Thymeleaf HTML files
│ │ └── application.properties
├── pom.xml

---

🔐 Admin Login Credentials
Username :admin 
Password : admin123
	
These are default credentials. You can change them in the code or directly in the database.

---

📥 Excel Upload Template
The system supports uploading student results through an Excel sheet.

Expected format:

Reg No |	Name	| Subject1|	Subject2|	Subject3|	Total|	Grade
1001|John Doe	|89|	76|	92|	257|	A

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
