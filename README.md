# Topics Exchange Web Application

A collaborative web platform where registered users can define topics of interest, post messages, and manage their discussions through a centralized database. Built as part of the *Web Technologies and Applications* course, the project demonstrates a full-stack implementation using Java Servlets, relational database management, and asynchronous client-server communication.

---

## Table of Contents

- [About the Project](#about-the-project)
- [Technical Stack](#technical-stack)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [API Overview](#api-overview)

---

## About the Project

The application allows users to register, authenticate, and participate in a community-driven topic exchange system. Each user can introduce new topics, contribute messages to existing ones, and manage their own account — all through a dynamic, single-page-style interface powered by the Fetch API.

### Key Functionality

**User Authentication**
Users can register for an account, sign in securely, and sign out. Passwords are never stored in plain text — they are hashed using SHA-256 before being persisted to the database.

**User Profile Management**
Authenticated users can update their account details, including changing their password. They also have the option to permanently delete their account from the system.

**Topic Management**
Users can submit new topics of interest, provided the topic name does not already exist in the system. Each topic has a unique name and an optional description.

**Messaging**
Users can post messages associated with specific topics. All messages are timestamped and linked to both the author and the relevant topic.

---

## Technical Stack

### Back-end
- **Java Servlets** — handle HTTP requests and implement the application's business logic
- **JDBC** — provides database connectivity between Java and MariaDB
- **Gson** — handles JSON serialization and deserialization for client-server communication

### Front-end
- **HTML5 / CSS3** — structure and styling
- **JavaScript (Fetch API)** — asynchronous communication with the server, enabling dynamic page updates without full reloads

### Database
- **MariaDB / MySQL** (hosted via XAMPP) — relational database storing users, topics, and messages
- **MariaDB JDBC Driver** — connector library (`mariadb-java-client-x.x.x.jar`) enabling Java to communicate with the database

### Deployment
- **Apache Tomcat 9+** — Java-compliant web container used to deploy and serve the application

---

## Database Schema

The application uses a relational database named `projectdb` with three core tables:

| Table | Columns | Notes |
|-------|---------|-------|
| `users` | `uname`, `upasshash` | `uname` is unique; password is SHA-256 hashed |
| `topics` | `id`, `name`, `description` | `name` is unique |
| `messages` | `id`, `topic_id`, `user_id`, `msg`, `date_sent` | Foreign keys reference `topics` and `users` |

![Database Schema](https://github.com/user-attachments/assets/3a493884-4018-4f35-8f7e-2cf39a2b2aac)

---

## Project Structure

The project follows the standard Maven-based Java Web Application layout:

```
topics-exchange/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org/hua/it2024041/
│       │       ├── [servlets]*.java          # All Servlet classes (auth, topics, messages)
│       │       ├── DatabaseConnection.java   # JDBC connection configuration
│       │       ├── Util.java          # Hashing and general utilities
│       │       └── ServletUtil.java   # JSON response helpers
│       │
│       └── webapp/
│           ├── css/                   # Stylesheets
│           ├── js/                    # Client-side JavaScript (Fetch API logic)
│           ├── imgs/                  # Static images and media
│           ├── *.html                 # Static HTML pages
│           ├── *.jsp                  # JSP pages for server-side generated content
│           └── WEB-INF/
│               ├── web.xml            # Servlet routing and security configuration
│               └── lib/
│                   └── mariadb-java-client-x.x.x.jar
│
├── projectdb.sql                      # Database dump for initial setup
└── README.md
```

---

## Setup Instructions

### 1. Database Setup

- Open the **XAMPP Control Panel** and start the **MySQL / MariaDB** service.
- Open **phpMyAdmin** (or any preferred database tool) and import the provided `projectdb.sql` file to create and populate the database.

### 2. JDBC Connector

Ensure the `mariadb-java-client-x.x.x.jar` file is present in the `WEB-INF/lib/` directory. If it is missing, download the appropriate version from the [MariaDB Connector/J page](https://mariadb.com/kb/en/mariadb-connector-j/) and place it there manually.

### 3. Database Configuration

Open `DatabaseConnection.java` and verify or update the following:

```java
String url  = "jdbc:mariadb://localhost:3306/projectdb";
String user = "root";
String pass = "";   // default XAMPP has no password
```

Adjust these values if your local environment uses different credentials.

### 4. Utility Classes

Confirm that `Util.java` and `ServletUtil.java` are correctly placed inside the `org.hua.it2024041` package. These files handle SHA-256 password hashing and standardised JSON response formatting respectively.

### 5. Deployment

Deploy the project to **Apache Tomcat 9 or higher**:

- In your IDE (e.g. IntelliJ IDEA or Eclipse), configure a Tomcat run configuration pointing to your local Tomcat installation.
- Build the project and deploy the generated `.war` file, or run it directly through the IDE's integrated server support.
- The application will be available at `http://localhost:8080/<project-name>/`.

---

## API Overview

All server communication is handled through Servlets mapped in `web.xml`. The front-end communicates exclusively via the Fetch API using JSON payloads.

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/signup` | POST | Register a new user |
| `/signin` | POST | Authenticate an existing user |
| `/signout` | POST | End the current session |
| `/profile` | GET / POST | View or update account details |
| `/deleteAccount` | POST | Permanently delete a user account |
| `/topics` | GET / POST | List all topics or create a new one |
| `/messages` | GET / POST | Retrieve or post messages for a topic |

> Exact endpoint names may vary depending on your `web.xml` configuration.
