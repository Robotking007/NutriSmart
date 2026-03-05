# NutriSmart — Food and Nutrient Tracking System

NutriSmart is a command-line based nutrition tracking application built in Java that helps users monitor their daily food intake, calculate nutritional values, and track progress toward health goals.

---

## Features

### 1. User Management
- User registration with personal details (age, weight, height, gender)
- Login/logout authentication system
- Automatic BMR (Basal Metabolic Rate) calculation using the Mifflin-St Jeor Equation
- Personalized daily calorie goal based on activity level

### 2. Food Database
- Pre-loaded database of 27 common foods with real nutritional values
- Foods categorized as Fruit, Protein, Vegetable, Grain, and Dairy
- Search by name (case-insensitive partial match) or by category
- Add custom foods manually with nutritional information

### 3. Meal Tracking
- Create meals by combining multiple foods with custom serving sizes
- Supports 4 meal types: Breakfast, Lunch, Dinner, Snack
- Automatic calculation of total calories, protein, carbs, and fats per meal
- Nutrition values scaled by serving size (per 100g base)

### 4. Daily Nutrition Analysis
- Visual progress bar showing calorie consumption vs. goal
- Macronutrient breakdown (Protein, Carbs, Fats)
- Remaining calorie tracking
- Alerts when daily calorie goal is exceeded

### 5. Smart Suggestions
- Recommends meals based on remaining calories:
  - Full meals (~400–500 cal) when plenty of calories remain
  - Light meals (~200–300 cal) for moderate remaining intake
  - Snacks (~100–200 cal) when close to goal
- Macro-specific tips for low protein or low carb intake

### 6. Multi-Day & Weekly Tracking
- Advance to next day to track multiple days
- Weekly overview with progress bars for each day
- Average daily intake comparison against goal

---

## Project Structure

```
NutriSmart/
├── README.md
├── CONTRIBUTIONS.md
└── src/
    └── nutrismart/
        ├── NutriSmart.java          ← Main application (entry point)
        ├── User.java                ← User model with BMR calculation
        ├── UserManager.java         ← Registration, login, persistence
        ├── Food.java                ← Food item with nutritional data
        ├── FoodDatabase.java        ← Food database manager (27 pre-loaded foods)
        ├── Meal.java                ← Meal with nutrition calculation
        ├── MealType.java            ← Enum: Breakfast, Lunch, Dinner, Snack
        ├── FoodCategory.java        ← Enum: Fruit, Protein, Vegetable, Grain, Dairy
        ├── ActivityLevel.java       ← Enum: Sedentary to Extra Active
        ├── DailyLog.java            ← Daily meal log with progress tracking
        └── NutritionTracker.java    ← Core analytics, suggestions, weekly view
```

---

## How to Compile and Run

### Prerequisites
- Java JDK 11 or higher

### Steps

```bash
cd NutriSmart/src
javac nutrismart/*.java
java nutrismart.NutriSmart
```

---

## Usage Workflow

1. **Register** — Create an account with your personal details
2. **Set Activity Level** — Choose from Sedentary to Extra Active
3. **System calculates** your BMR and daily calorie goal
4. **Log Meals** — Search foods, select serving sizes, and add them to meals
5. **View Progress** — Check your daily calorie and macronutrient progress
6. **Get Suggestions** — Receive smart meal recommendations
7. **Track Multiple Days** — Advance to the next day and view weekly overview

---

## Sample Screenshots

```
============================================================
       _   _       _        _ ____                       _
      | \ | |_   _| |_ _ __(_) ___| _ __ ___   __ _ _ __| |_
      |  \| | | | | __| '__| \___ \| '_ ` _ \ / _` | '__| __|
      | |\  | |_| | |_| |  | |___) | | | | | | (_| | |  | |_
      |_| \_|\__,_|\__|_|  |_|____/|_| |_| |_|\__,_|_|   \__|

          Food and Nutrient Tracking System v1.0
============================================================

--- NutriSmart Main Menu (Day 1) ---
1. Log a Meal
2. View Today's Meals
3. View Daily Progress
4. Get Meal Suggestions
5. Browse Food Database
6. Add New Food to Database
7. Search Foods
8. Move to Next Day
9. View Weekly Overview
10. View My Profile
11. Logout
12. Exit
```

```
============================================================
  Day 1 Progress for john
============================================================
  Calorie Goal:  2200 cal
  Consumed:      845 cal
  Remaining:     1355 cal

  Progress: [###############-------------------------] 38.4% (845 / 2200 cal)

  --- Macronutrient Breakdown ---
  Protein:  52.3g
  Carbs:    78.1g
  Fats:     12.4g
============================================================
```

---

## OOP Concepts Demonstrated

| Concept | Implementation |
|---------|----------------|
| **Encapsulation** | Private fields with public getters/setters in all classes |
| **Abstraction** | Complex logic hidden behind simple method calls |
| **Composition** | NutritionTracker → DailyLog → Meal → Food |
| **Aggregation** | FoodDatabase aggregates independent Food objects |
| **Polymorphism** | `toString()` overrides in all classes |
| **Enums** | FoodCategory, MealType, ActivityLevel with fields and methods |
| **Constructors** | Parameterized and copy constructors |
| **Static Members** | Factory methods, constants, main method |

For a detailed breakdown of OOP concepts and member contributions, see [CONTRIBUTIONS.md](CONTRIBUTIONS.md).

---

## Data Persistence

User data and food database are saved to text files in a `data/` folder:
- `data/users.txt` — Registered user accounts
- `data/foods.txt` — Food database (including custom additions)

---

## Team Members

| Name | Role |
|------|------|
| Siddhant Udavant | Project Lead & Main Application Logic |
| Shruti Sonawane | User Management & Authentication |
| Trupti Shinde | Food Database & Data Management |
| Kanika Shinde | Meal Tracking & Nutrition Calculation |
| Aayush Shewale | Daily Logging, Analytics & Suggestions |

---
