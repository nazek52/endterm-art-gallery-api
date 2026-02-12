#Art Gallery – Endterm Project

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

###  Base URL
http://localhost:8080

---

### Endpoint List

| Endpoint | HTTP Method | Description |
|--------|-------------|-------------|
| `/api/artworks` | GET | Get all artworks |

---

### Sample Request

**GET**
/api/artworks


### Sample JSON Response

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
```
Postman Screenshots
GET request to /api/artworks

Response showing JSON list of artworks

(Screenshots should be attached here when submitting the project)

C. Design Patterns
* Singleton Pattern
Class: DatabaseConnection

Purpose:
Ensures that only one instance of the database connection exists.

Usage:
Demonstrates controlled access to a shared resource (educational purpose).

* Factory Pattern
Class: ArtworkFactory

Purpose:
Creates different types of artworks (Painting, Sculpture) based on input type.

Usage:
Removes object creation logic from the service layer and improves flexibility.

* Builder Pattern
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
Tables
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

http://localhost:8080/api/artworks
I. Reflection Section
This project helped reinforce understanding of:

How to structure a Spring Boot application

Applying design patterns in real scenarios

Using REST APIs to expose data

Writing clean, maintainable, and scalable code

The most challenging part was correctly integrating multiple design patterns while keeping the architecture clean.
Overall, the project strengthened practical Java and Spring Boot skills.

* Bonus Task – Caching Layer (Simple In-Memory Cache)
Objective

To enhance application performance by implementing a simple in-memory caching mechanism for frequently accessed data.

The goal of this bonus task was to reduce unnecessary database calls and improve response time when retrieving artworks.

*Implementation Overview

A custom in-memory caching layer was implemented following:

Singleton pattern

SOLID principles

Layered architecture

Thread-safe design

The cache stores results of frequently requested data from:

GET /api/artworks


Specifically, it caches the result of:

ArtworkService.getAllArtworks()

* How the Cache Works

Client sends request to /api/artworks

Service layer checks if data exists in cache

If cache hit → return cached data

If cache miss → query database → store result in cache → return data

This ensures repeated calls do not hit the database unnecessarily.

* Cache Architecture
Class: ArtworkCache

Located in:

patterns/cache

Key Characteristics:

Stored in memory using ConcurrentHashMap

Only one instance (Singleton)

Thread-safe

Supports TTL (Time-To-Live)

Supports manual clearing

Supports automatic invalidation

Tracks cache statistics (hits & misses)

* Singleton Implementation

The cache uses Double-Checked Locking:

private static volatile ArtworkCache instance;

public static ArtworkCache getInstance() {
    if (instance == null) {
        synchronized (ArtworkCache.class) {
            if (instance == null) {
                instance = new ArtworkCache();
            }
        }
    }
    return instance;
}


This ensures:

Only one cache instance exists

Thread-safe initialization

High performance

* TTL (Time-To-Live)

The cache automatically expires entries after:

60 seconds

TTL FLOW DIAGRAM 
Client → GET /api/artworks
        ↓
   Service checks Cache
        ↓
   ┌───────────────┐
   │ Cache exists? │
   └───────┬───────┘
           │
      YES  │  NO
           │
   Check TTL      → Fetch from DB
           │           ↓
    Expired?            Save to Cache
           │
    YES → Remove → Fetch from DB
    NO  → Return Cached Data

If the TTL expires:

Cached data is removed

Next request fetches fresh data from database

This prevents stale data problems.

* Automatic Cache Invalidation

The cache is automatically invalidated when:

A new artwork is created (POST)

An artwork is deleted (DELETE)

This ensures consistency between database and cache.

Example logic:

cache.invalidate("all_artworks");

* Manual Cache Clear Endpoint

Endpoint added:

DELETE /api/artworks/cache


This allows manual clearing of the cache for administrative purposes.

* Cache Statistics Endpoint

To demonstrate performance improvement, a statistics endpoint was added:

GET /api/artworks/stats


It returns:

Cache Hits: X, Cache Misses: Y


This clearly shows whether data is coming from cache or database.

* Logging Demonstration

For academic demonstration purposes, console logging was added:

When database is queried:

* Fetching from DATABASE


When cache is used:

* Returned from CACHE


This makes performance behavior visible during testing.

* Design Constraints Compliance
Requirement         |	Implementation
In-memory storage	  |ConcurrentHashMap
Singleton           |	Double-Checked Locking
Cached method	      |getAllArtworks()
Manual clear        |	DELETE endpoint
Auto invalidation	  |After insert/delete
SOLID	              |Fully respected
Layered architecture|	Not broken

The caching logic exists only in the Service layer, preserving clean architecture.

* SOLID Compliance in Caching

Single Responsibility:
ArtworkCache only manages caching logic.

Open/Closed:
TTL and statistics were added without modifying service logic.

Liskov Substitution:
Cache does not affect polymorphism of Artwork.

Interface Segregation:
Service interface exposes only necessary cache-related methods.

Dependency Inversion:
High-level modules depend on abstractions, not concrete cache storage.

* Performance Improvement

Without cache:

Every GET request hits the database

With cache:

Only first request queries DB

Subsequent requests use in-memory data

Significantly reduced database load

This demonstrates real-world backend optimization techniques.

*Final Result

This bonus task helped demonstrate:

Practical performance optimization

Advanced Singleton implementation

Thread-safe in-memory caching

Data consistency management

Clean architectural integration

The caching layer was implemented without breaking the layered structure, making the system more scalable and efficient.


