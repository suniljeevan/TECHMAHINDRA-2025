# Alumni Managment System
![image](https://github.com/user-attachments/assets/c6eba977-da51-44c9-95b4-fadabf8e271e)

## Overview
This project is a robust, secure, and scalable Alumni Management System (AMS) developed as part of Tech Mahindra's campus training program in collaboration with Presidency University. The system bridges the gap between academic institutions and their alumni through a centralized platform that supports profile management, event coordination, and streamlined communication.

Built using Spring Boot, Thymeleaf, MySQL, and Spring Security, this module integrates into a larger University Management System (UMS) with full-stack capabilities. It was designed and implemented by Group 14 as part of the academic curriculum.

## Features
- Secure login system with role-based access (Admin and Alumni).
- Alumni registration and profile management.
- Admin event creation and alumni event registration.
- Internal messaging system between alumni and admins.
- Public alumni directory for verified users.
- Activity logs for admin actions.
- Responsive and accessible UI using Thymeleaf, HTML5, CSS3, and Bootstrap.

##  Modules & Libraries Used
**1. Backend**
- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA with Hibernate
- MySQL (Relational Database)
- Maven (Dependency Management)

**2. Frontend**
- Thymeleaf (Server-side templating engine)
- HTML5 / CSS3 / JavaScript
- Bootstrap (for responsive design)

## Algorithm and Workflow
User authentication and authorization are handled through role-based access using Spring Security. Alumni register through a secure portal and await admin approval. Once approved, they can log in and manage their profile. Admin users are responsible for approving alumni, creating events, and managing all data entries. Events can be posted by admins and viewed or registered for by alumni. Messaging is enabled within the system to facilitate communication between alumni and admins. Admin actions are logged for audit and transparency. The system also includes a publicly accessible alumni directory to support networking and visibility.

## Summary
The Alumni Management System was developed to provide a practical, secure, and engaging platform for connecting alumni with their alma mater. This full-stack web application allowed us to apply industry-standard practices such as MVC architecture, layered backend development, secure credential handling, and responsive UI design. Throughout this project, we gained valuable experience in teamwork, version control, modular coding, and enterprise-grade system architecture. The system is designed for future scalability with possibilities like mentorship programs, donation tracking, and job board integration, offering long-term value to institutions and their alumni communities.
