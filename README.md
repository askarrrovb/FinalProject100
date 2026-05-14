# University Management System

Backend application for managing university students, teachers, courses, departments, grades and enrollments.

## Technologies

- Java 21, Spring Boot 3.4.5
- Spring Security + JWT
- PostgreSQL
- Swagger UI
- Docker + Docker Compose

## Run with Docker

```bash
docker-compose up --build
```

Application will be available at: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html

## Run locally

1. Start PostgreSQL and create database `university_db`
2. Update `application.yml` with your DB credentials
3. Run:

```bash
./gradlew bootRun
```

## API Endpoints

| Method | URL | Description | Auth |
|--------|-----|-------------|------|
| POST | /api/auth/register | Register new user | No |
| POST | /api/auth/login | Login | No |
| GET | /api/students | Get all students | Yes |
| GET | /api/students/search | Search with pagination | Yes |
| POST | /api/students | Create student | ADMIN/TEACHER |
| DELETE | /api/students/{id} | Delete student | ADMIN |
| GET | /api/teachers | Get all teachers | Yes |
| GET | /api/courses | Get all courses | Yes |
| GET | /api/departments | Get all departments | Yes |
| GET | /api/grades/student/{id} | Get student grades | Yes |
| POST | /api/enrollments/enroll | Enroll student | Yes |
| POST | /api/files/upload | Upload file | Yes |
| GET | /api/files/download/{name} | Download file | Yes |

## Author

Askar Bagdaulet
