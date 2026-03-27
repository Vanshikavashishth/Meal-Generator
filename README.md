# 🍽️ What Should I Eat? — Meal Generator

A fun full-stack web app built with **Spring Boot** (backend) and **HTML/CSS/JS** (frontend).  
Can't decide what to eat? Let the app pick for you! Filter by veg/non-veg, healthy/indulgent, and save your favorites.

---

## 🚀 Features

- 🎲 Random meal generator with filters
- 🔐 Login & Register (Admin / User roles)
- ⚙️ Admin panel — Add, Edit, Delete meals
- ❤️ Save favorite meals (stored in browser)
- 📋 View all meals in a table

---

## 🛠️ Tech Stack

| Layer    | Technology         |
|----------|--------------------|
| Backend  | Java 17, Spring Boot 3.2 |
| Frontend | HTML, CSS, JavaScript |
| Build    | Maven |
| Storage  | In-memory (no database) |

---

## 📁 Project Structure

```
meal-generator/
└── src/
    └── main/
        ├── java/com/meal/
        │   ├── MealApplication.java     ← App entry point
        │   ├── Meal.java                ← Meal data model
        │   ├── MealController.java      ← Meal REST APIs
        │   ├── User.java                ← User data model
        │   ├── AuthController.java      ← Login & Register APIs
        │   └── UserController.java      ← User APIs
        └── resources/
            └── static/
                └── index.html           ← Frontend (UI)
```

---

## ▶️ How to Run

### Prerequisites
- Java 17+
- Maven

### Steps
```bash
# 1. Clone the repo
git clone https://github.com/YOUR_USERNAME/meal-generator.git
cd meal-generator

# 2. Run the app
mvn spring-boot:run

# 3. Open in browser
http://localhost:8080
```

---

## 🔑 Test Accounts

| Role  | Email              | Password  |
|-------|--------------------|-----------|
| Admin | admin@meal.com     | admin123  |
| User  | john@meal.com      | user123   |

> Admins can add, edit, and delete meals. Users can browse and save favorites.

---

## 🌐 API Endpoints

### Auth
| Method | URL                  | Description        |
|--------|----------------------|--------------------|
| POST   | `/api/auth/login`    | Login              |
| POST   | `/api/auth/register` | Register new user  |

### Meals
| Method | URL                        | Description              |
|--------|----------------------------|--------------------------|
| GET    | `/api/meals/all`           | Get all meals            |
| GET    | `/api/meals/random`        | Get a random meal        |
| GET    | `/api/meals/{id}`          | Get meal by ID           |
| GET    | `/api/meals/filter?type=veg&category=healthy` | Filter meals |
| POST   | `/api/meals`               | Add new meal (admin)     |
| PUT    | `/api/meals/{id}`          | Update meal (admin)      |
| DELETE | `/api/meals/{id}`          | Delete meal (admin)      |

---

## 👩‍💻 Made by Vanshika