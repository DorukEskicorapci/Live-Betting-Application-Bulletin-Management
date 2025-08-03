# Live Betting Application Bulletin Management

**Project Type:** Spring Boot + Java banking‑style live betting API with bulletin management

**Start Date:** May 2025

**End Date:** July 2025



---

## 🤩 Overview

This project implements a **live sports betting bulletin management service** using Spring Boot, Java, and a relational database. Designed to handle real-time betting events and user bulletins, it provides secure user access, bulletin posting, retrieval, updates, and administrative controls.

---

## ⚙️ Features

* **User Access & Authentication** – Users authenticate with secure credentials using Spring Security.
* **Bulletin Management** – Create, view, update, and delete bulletins related to live betting events.
* **Event Endpoints** – REST API endpoints to retrieve current betting events and statuses.
* **Scheduled Updates** – Automated tasks fetch live event data and push bulletin updates.
* **Error Handling** – Centralized exception handling with detailed error response models.
* **Database Layer** – Uses JPA with a relational H2 database.
* **Integration Tests** – Covers authentication flows, bulletin CRUD, and scheduled tasks.
* **SOLID Evaluations** – Project evaluated and updated based on SOLID principles, with a documented review and scoring

---

## 📁 Project Structure

```
src
└── main/java/com/example/livebetting
    ├── controller            # REST controllers for user, bulletin, and event endpoints
    ├── service               # Business logic services
    ├── entity                # JPA entities
    ├── dao                   # Repository interfaces for database access
    ├── exceptionHandler      # Custom exceptions & API error response handlers
    └── schedulingtasks       # Scheduled tasks 
```

* `LiveBettingApplication.java` — Spring Boot main class.
* `EventRestController.java` — Handles event and bulletin REST endpoints.
* `EventRequest.java`, `EventResponse.java` — DTOs for REST payloads.
* `SOLID Principles Evaluation Report.docx` — Developer's self‑review document.

---

## 🚀 Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/DorukEskicorapci/Live-Betting-Application-Bulletin-Management.git
   cd Live-Betting-Application-Bulletin-Management
   ```
2. **Configure database settings** in `application.properties` (host, username, password).
3. **Build the project** using Maven:

   ```bash
   mvn clean package
   ```
4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```
5. **Test the API** endpoints with a tool like Postman or curl:

   * `GET /api/events` — Retrieve all events.
   * `GET /api/events/{eventId}` — Retrieve a specific event by ID.
   * `POST /api/events` — Create new events (bulk).
   * `PUT /api/events/{id}` — Update an event by ID.

---

## ✅ Key Benefits

* Production‑level API design using REST best practices.
* Secure and modular service layering.
* Automatic event-to-bulletin flow with scheduling.
* Clear exception handling and result modeling.
* SOLID compliance audit improves code maintainability and scalability.


---

## 🧰 Testing

Tests cover:

* Authentication flows.
* CRUD operations on bulletins.
* Service logic for event updates.
* Invalid input and error handling scenarios.

Run tests with:

```bash
mvn test
```

---

## 🧠 Notes

* Database and external event API configurations are abstracted in properties for flexibility.
* Scheduled tasks run at fixed intervals—adjust timing in `ScheduledTasks.java` as needed.
* Log outputs are written to `logs/live-betting.log` for debugging and audit.
* For a detailed review of how SOLID principles were evaluated and applied, refer to the SOLID Principles Evaluation Report.docx in the project root.
