Finance Dashboard Backend

Overview
---------
This project is a backend system for a Finance Dashboard that allows users to manage financial records and view analytics based on their roles. The system is designed with a clean architecture, role-based access control, and secure authentication using JWT.

Key Features
-------------
User management with role-based access (Admin, Analyst, Viewer)
Secure authentication using JWT tokens
Financial records management (CRUD operations)
Dashboard analytics (income, expenses, trends, category-wise data)
Input validation and global exception handling
API documentation using Swagger (OpenAPI)


Role-Based Access
----------------------
Viewer: Can view dashboard data only
Analyst: Can view records and analytics
Admin: Full access including managing users and records

Tech Stack
-----------
Java, Spring Boot
Spring Security (JWT Authentication)
Spring Data JPA (Hibernate)
MySQL Database
Swagger (OpenAPI)
ModelMapper
Architecture
-----------------------

Client → Controller → Service → Repository → Database

Security
--------------
JWT-based authentication
Role-based authorization using Spring Security
Password encryption using BCrypt

Dashboard Analytics
-----------------------
Total income and expenses
Net balance
Category-wise breakdown
Monthly trends
Recent transactions
