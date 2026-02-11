# 🎨 Art Gallery – Endterm Project

## A. Project Overview

This project is a **Spring Boot REST API** developed as an **Endterm assignment**.  
The application models an art gallery system where artworks (paintings and sculptures) are managed and exposed through RESTful endpoints.

The main purpose of the project is to demonstrate:
- Object-Oriented Programming (OOP)
- SOLID principles
- Layered architecture
- Usage of design patterns
- REST API development with Spring Boot

---

## B. REST API Documentation

### 🔗 Base URL
http://localhost:8080

yaml
Копировать код

---

### 📌 Endpoint List

| Endpoint | HTTP Method | Description |
|--------|-------------|-------------|
| `/api/artworks` | GET | Get all artworks |

---

### 📥 Sample Request

**GET**
/api/artworks

pgsql
Копировать код

No request body is required.

---

### 📤 Sample JSON Response

```json
[
  {
    "id": 1,
    "title": "Starry Night",
    "price": 1000000.0,
    "artist": {
      "id": 1,
      "name": "Vincent van Gogh"
    },
    "type": "Painting"
  },
  {
    "id": 2,
    "title": "Mona Lisa",
    "price": 850000.0,
    "artist": {
      "id": 2,
      "name": "Leonardo da Vinci"
    },
    "type": "Painting"
  },
  {
    "id": 3,
    "title": "The Thinker",
    "price": 300000.0,
    "artist": {
      "id": 3,
      "name": "Auguste Rodin"
    },
    "type": "Sculpture"
  }
]
📸 Postman Screenshots
GET request to /api/artworks

Response showing JSON list of artworks

(Screenshots should be attached here when submitting the project)

C. Design Patterns
🔹 Singleton Pattern
Class: DatabaseConnection

Purpose:
Ensures that only one instance of the database connection exists.

Usage:
Demonstrates controlled access to a shared resource (educational purpose).

🔹 Factory Pattern
Class: ArtworkFactory

Purpose:
Creates different types of artworks (Painting, Sculpture) based on input type.

Usage:
Removes object creation logic from the service layer and improves flexibility.

🔹 Builder Pattern
Class: ArtworkBuilder

Purpose:
Constructs complex Artwork objects step by step.

Usage:
Improves readability and allows flexible object creation with optional fields.

D. Component Principles
The project follows component-based design principles:

High cohesion:
Each class has a single, well-defined responsibility.

Loose coupling:
Components interact through interfaces and abstractions.

Dependency Injection:
Used to connect controller, service, and repository layers.

This improves maintainability and testability.

E. SOLID & OOP Summary
SOLID Principles
S – Single Responsibility:
Each class handles one responsibility (Controller, Service, Repository).

O – Open/Closed:
New artwork types can be added without modifying existing logic.

L – Liskov Substitution:
Painting and Sculpture can be used as Artwork.

I – Interface Segregation:
Service interfaces expose only necessary methods.

D – Dependency Inversion:
High-level modules depend on abstractions, not implementations.

OOP Concepts
Encapsulation

Inheritance

Polymorphism

Abstraction

F. Database Schema
🗄 Tables
Artist

id (PK)

name

Artwork

id (PK)

title

price

type

artist_id (FK)

Relationship
One Artist → Many Artworks

G. System Architecture Diagram
arduino
Копировать код
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Model
The architecture follows a classic layered design.

H. Instructions to Run the Spring Boot Application
Open the project in IntelliJ IDEA

Make sure Java and Maven are installed

Run ArtGalleryApplication

Application starts on port 8080

Open browser or Postman:

bash
Копировать код
http://localhost:8080/api/artworks
I. Reflection Section
This project helped reinforce understanding of:

How to structure a Spring Boot application

Applying design patterns in real scenarios

Using REST APIs to expose data

Writing clean, maintainable, and scalable code

The most challenging part was correctly integrating multiple design patterns while keeping the architecture clean.
Overall, the project strengthened practical Java and Spring Boot skills.
