This is a robust, production-grade backend REST API application built to digitize the food ordering ecosystem. The system manages the entire workflow—from menu exploration and user authentication to final order processing. The primary focus was to build a secure and scalable middleware that facilitates seamless data exchange between customers and the restaurant database.

User Lifecycle Management: Developed a secure registration and login system for customers to maintain profiles and track their order history.

Dynamic Menu Control: Engineered comprehensive CRUD (Create, Read, Update, Delete) APIs that allow administrators to manage food items, categories, and real-time pricing updates.

Advanced Ordering Logic: Built complex backend services to handle the transition of items from the menu to a final "Placed Order" state, ensuring data consistency.

Relational Database Architecture: Designed a normalized MySQL schema using Spring Data JPA to manage One-to-Many (User to Orders) and Many-to-Many (Orders to Food Items) relationships.

API Reliability: Integrated global exception handling and data validation to ensure the API remains resilient and returns user-friendly error messages.

Technical Stack:

Backend: Java, Spring Boot (Web, JPA)

Database: MySQL

Build Tool: Maven

API Testing: Postman
