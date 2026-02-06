# 📘 University Course Registration System

## 🚀 Overview: 
University Course Registration System is a full-stack web application built with Spring Boot + JavaScript that simulates a real-world university management system.

### This project goes beyond simple CRUD operations and focuses on:
- real business logic
* relational database modeling
+ layered backend architecture
- full REST API integration
* frontend + backend communication

Students can register for courses, teachers manage courses, and the system handles complex relationships between entities.

### Designed as a mini production-like system, not just a tutorial project.

### 🎯 Features: Students, Teachers, Courses, Registration System

👨‍🎓 Students
```
Create student
Update student
Delete student
Search by email
Search by name + surname
View registered courses
```
👨‍🏫 Teachers
```
Create teacher
Update teacher
Delete teacher
Filter by department
View teacher’s courses
```
📚 Courses
```
Create course
Update course
Delete course
Assign teacher
Search by name
Filter by department
View enrolled students
```
📝 Registration System
```
Register student to course
Prevent duplicate registration
List student's courses
List course's students
Remove student from course
Unassign teacher from course
```

### 🧠 Architecture
This project follows Layered Architecture:
```
Controller → Service → Repository → Database
```

### Project Structure
```
src/main/java/com/example/universitycourseregistration

controller
service
service/imp
repository
entity
dto
exception

src/main/resources
static (frontend files)
```

### 🛠 Tech Stack
1. Backend
- Java 17+
* Spring Boot
+ Spring Web (REST API)
- Spring Data JPA
* Hibernate
+ PostgreSQL / H2 
- Lombok

2. Frontend
- HTML
* CSS
+ JavaScript (Fetch API)

3. Tools
- IntelliJ IDEA
* Postman
+ GitHub

### Database Design

Entities
1. Student
```
id
name
surname
email
```
2. Teacher
```
id
name
surname
department
```
3. Course
```
id
courseName
description
credits
teacher (Many-to-One)
```
4. Registration
```
registrationId
student (Many-to-One)
course (Many-to-One)
```

## How to Run
1. Clone

git clone https://github.com/Kanan-peoiks/University_Course_Registration.git
2. Open in İntelliJ
3. Configure database

Edit:
```
application.properties
```

Example:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/YOURPROJECT
spring.datasource.username=YOUR_USURNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```
4. Run
```
UniversityCourseRegistrationApp
```
5. Open browser
```
http://localhost:8080
```

### What I Learned 
Through this project I practiced:
- REST API design
- Layered architecture
- DTO mapping
- JPA relationships
- Business logic implementation
- Full-stack integration
- Clean code structure

### Future Improvements
- Authentication (JWT)
- Role system (Admin/Student/Teacher)
- Pagination
- Docker
- React frontend
- Deployment (AWS/Render)

## 👤 Author
Kanan

GitHub:

https://github.com/Kanan-peoiks

## ⭐ If you like this project, feel free to star the repo!

