# NutriSmart - Food and Nutrient Tracking System

## Project Contributions

---

### Team Members & Individual Contributions

| Sr. No. | Name                | Role                          |
|---------|---------------------|-------------------------------|
| 1       | Siddhant Udavant    | Project Lead & Main Application Logic |
| 2       | Shruti Sonawane     | User Management & Authentication      |
| 3       | Trupti Shinde       | Food Database & Data Management       |
| 4       | Kanika Shinde       | Meal Tracking & Nutrition Calculation  |
| 5       | Aayush Shewale      | Daily Logging, Analytics & Suggestions |

---

### 1. Siddhant Udavant — Project Lead & Main Application Logic

**Files Worked On:** `NutriSmart.java`

**Contributions:**
- Designed the overall project architecture and class structure
- Implemented the main application class `NutriSmart.java` — the entry point of the program
- Built the complete command-line user interface (CLI) with menu-driven navigation
- Developed the authentication menu (`showAuthMenu()`) and main menu (`showMainMenu()`)
- Implemented meal logging workflow — search food by name, browse all foods, browse by category
- Created the `handleLogMeal()`, `addFoodBySearch()`, `addFoodFromList()`, `addFoodByCategory()` methods for food selection during meal creation
- Implemented `handleAddFood()`, `createCustomFood()` for manual food entry
- Built `handleSearchFoods()` for food search functionality
- Developed `showProfile()` to display user information
- Created safe input utility methods `readInt()` and `readDouble()` for parsing user input
- Coordinated integration of all components (UserManager, FoodDatabase, NutritionTracker)
- Implemented the `printBanner()` method for the ASCII art welcome screen

---

### 2. Shruti Sonawane — User Management & Authentication

**Files Worked On:** `User.java`, `UserManager.java`, `ActivityLevel.java`

**Contributions:**
- Designed and implemented the `User` class with full encapsulation of user attributes (username, password, age, weight, height, gender, activity level)
- Implemented **BMR (Basal Metabolic Rate) calculation** using the Mifflin-St Jeor Equation:
  - Men: `BMR = 10 × weight(kg) + 6.25 × height(cm) − 5 × age + 5`
  - Women: `BMR = 10 × weight(kg) + 6.25 × height(cm) − 5 × age − 161`
- Developed daily calorie goal calculation: `dailyCalorieGoal = BMR × activityMultiplier`
- Created the `ActivityLevel` enum with 5 levels (Sedentary, Light, Moderate, Active, Extra Active) each with a corresponding TDEE multiplier
- Implemented the `UserManager` class for managing user registration and login
- Built the `register()` method with duplicate username validation
- Built the `login()` method for secure authentication
- Implemented `logout()` and `isLoggedIn()` session management
- Developed file-based user persistence with `loadUsers()` and `saveUsers()` using `BufferedReader` and `PrintWriter`
- Created `toFileString()` and `fromFileString()` serialization methods for both `User` and `ActivityLevel`

---

### 3. Trupti Shinde — Food Database & Data Management

**Files Worked On:** `Food.java`, `FoodDatabase.java`, `FoodCategory.java`

**Contributions:**
- Designed and implemented the `Food` class representing a food item with nutritional data (calories, protein, carbs, fats per 100g)
- Implemented the **parameterized constructor** and **copy constructor** in the `Food` class
- Created full getter/setter methods for all food attributes (encapsulation)
- Developed the `FoodCategory` enum with 5 categories: Fruit, Protein, Vegetable, Grain, Dairy
- Built the `FoodDatabase` class to manage the complete food collection
- Initialized a database of **27 pre-loaded foods** across all categories with real nutritional values
- Implemented `searchByName()` — case-insensitive partial match search using `String.toLowerCase()` and `String.contains()`
- Implemented `searchByCategory()` — filtering foods by `FoodCategory` enum
- Built file I/O for food persistence:
  - `saveToFile()` using `PrintWriter` and `FileWriter`
  - `loadFromFile()` using `BufferedReader` and `FileReader`
  - `loadDefaultFoods()` — loads from file if exists, otherwise initializes defaults
- Created `displayAllFoods()` for formatted tabular display of all foods
- Implemented `toFileString()` and `fromFileString()` for CSV-based food serialization
- Used `static` keyword for the `fromFileString()` factory method and `FOOD_FILE` constant

---

### 4. Kanika Shinde — Meal Tracking & Nutrition Calculation

**Files Worked On:** `Meal.java`, `MealType.java`

**Contributions:**
- Designed and implemented the `Meal` class which aggregates multiple `Food` objects with serving sizes
- Created the `MealType` enum with 4 types: Breakfast, Lunch, Dinner, Snack
- Implemented `addFood()` method using the **copy constructor** to safely store food copies in the meal
- Built `removeFood()` for removing food items by index with bounds checking
- Developed all **nutrition calculation methods** considering per-serving scaling:
  - `getTotalCalories()` — sums `(food.calories × serving) / 100` across all foods
  - `getTotalProtein()` — sums `(food.protein × serving) / 100`
  - `getTotalCarbs()` — sums `(food.carbs × serving) / 100`
  - `getTotalFats()` — sums `(food.fats × serving) / 100`
- Used **parallel lists** (`List<Food>` and `List<Double>`) to associate foods with their serving sizes
- Implemented `toFileString()` for file serialization using `StringBuilder` with `|` and `:` delimiters
- Overrode `toString()` to display a formatted meal summary with per-item and total nutrition breakdown
- Used `String.format()` with formatting specifiers for aligned output

---

### 5. Aayush Shewale — Daily Logging, Analytics & Suggestions

**Files Worked On:** `DailyLog.java`, `NutritionTracker.java`

**Contributions:**
- Designed and implemented the `DailyLog` class to track all meals consumed in a single day
- Built `addMeal()`, `getMealsByType()`, and day-level nutrition aggregation methods (`getTotalCalories()`, `getTotalProtein()`, `getTotalCarbs()`, `getTotalFats()`)
- Created the **visual progress bar** (`getProgressBar()`) using `#` and `-` symbols to show calorie consumption percentage
- Implemented the `NutritionTracker` class — the core analytics engine
- Built `showDailyProgress()` — displays calorie goal, consumed, remaining with visual progress bar and macronutrient breakdown
- Developed `showWeeklyOverview()` — displays progress bars for all tracked days with average daily intake
- Implemented `showSuggestions()` — **smart meal recommendation engine** that:
  - Suggests full meals (~400-500 cal) when plenty of calories remain
  - Suggests light meals (~200-300 cal) for moderate remaining calories
  - Suggests snacks (~100-200 cal) when close to goal
  - Provides low-calorie options when goal is exceeded
  - Gives macro-specific tips when protein < 50g or carbs < 100g
- Built `nextDay()` to advance to the next day creating a new `DailyLog`
- Implemented `getRemainingCalories()` for calorie deficit tracking
- Created `showTodayMeals()` for viewing current day's meal history

---

## OOP Concepts Used (In Detail)

### 1. Encapsulation

Encapsulation is the bundling of data (fields) and methods that operate on that data within a single class, while restricting direct access to the internal state using access modifiers.

**Where it is used:**
- **All classes** use `private` fields with `public` getter and setter methods
- `Food.java` — private fields (`name`, `category`, `calories`, `protein`, `carbs`, `fats`) with public `getName()`, `setName()`, `getCalories()`, `setCalories()`, etc.
- `User.java` — private fields (`username`, `password`, `age`, `weight`, `height`, `gender`, `bmr`, `dailyCalorieGoal`) accessed only through getters
- `UserManager.java` — `private List<User> users` and `private User currentUser` are hidden from outside; only `login()`, `register()`, `isLoggedIn()` are exposed
- `FoodDatabase.java` — `private List<Food> foods` is never directly exposed; accessed through `searchByName()`, `searchByCategory()`, `getFood()`, `addFood()`
- `Meal.java` — `private List<Food> foods` and `private List<Double> servings` are internal; calorie totals are exposed via calculated getters
- `NutritionTracker.java` — private internal state (`dailyLogs`, `currentDay`) with controlled public methods

**Why it matters:**
- Prevents invalid data (e.g., negative calories)
- Internal implementation can change without affecting other classes
- Setter methods in `User.java` automatically recalculate BMR when weight/height changes

---

### 2. Abstraction

Abstraction hides complex implementation details and exposes only the essential features to the user.

**Where it is used:**
- `NutritionTracker.showDailyProgress()` — hides the complexity of calorie calculation, progress bar rendering, and formatting behind a single method call
- `NutritionTracker.showSuggestions()` — hides the recommendation algorithm behind one method
- `User.calculateBMR()` — the Mifflin-St Jeor equation is hidden; callers just call the method
- `FoodDatabase.searchByName()` — hides the iteration and comparison logic
- `DailyLog.getProgressBar()` — hides the math and ASCII bar construction
- `Meal.getTotalCalories()` — hides the serving-size-adjusted calculation loop
- `Food.fromFileString()` — parsing CSV data is abstracted into a single static method call

---

### 3. Composition (Has-A Relationship)

Composition is when one class contains objects of other classes as fields, establishing a "has-a" relationship where the contained objects cannot exist without the container.

**Where it is used:**
- `NutritionTracker` **has-a** `User`, `List<DailyLog>`, and `FoodDatabase`
- `DailyLog` **has-a** `List<Meal>` — a daily log is composed of multiple meals
- `Meal` **has-a** `List<Food>` and `List<Double>` — a meal is composed of foods with servings
- `NutriSmart` **has-a** `Scanner`, `UserManager`, `FoodDatabase`, and `NutritionTracker`
- `UserManager` **has-a** `List<User>` and `User currentUser`

**Composition chain:** `NutritionTracker` → `DailyLog` → `Meal` → `Food`

---

### 4. Aggregation

Aggregation is a weaker form of composition where the contained objects can exist independently of the container.

**Where it is used:**
- `FoodDatabase` **aggregates** `Food` objects — Food objects (like "Apple", "Chicken Breast") have an independent identity and can exist without the database
- `NutriSmart` **aggregates** `UserManager` and `FoodDatabase` — these components can logically exist independently

---

### 5. Polymorphism

Polymorphism allows objects to take many forms. In this project, it is demonstrated through method overriding.

**Where it is used (Method Overriding):**
- `Food.toString()` — overrides `Object.toString()` to display formatted nutrition info
- `User.toString()` — overrides to display user profile with BMR and goal
- `Meal.toString()` — overrides to display all foods in the meal with totals
- `DailyLog.toString()` — overrides to display full day summary with all meals
- `FoodCategory.toString()` — overrides to return display name instead of enum constant name
- `MealType.toString()` — overrides to return "Breakfast" instead of "BREAKFAST"
- `ActivityLevel.toString()` — overrides to return human-readable description

All these classes can be passed to `System.out.println()` and Java automatically calls the correct `toString()` version — that's polymorphism in action.

---

### 6. Enum Types

Enums are a special class type in Java that represent a fixed set of constants with associated data and behavior.

**Where it is used:**
- `FoodCategory` — FRUIT, PROTEIN, VEGETABLE, GRAIN, DAIRY — each with a `displayName` field
- `MealType` — BREAKFAST, LUNCH, DINNER, SNACK — each with a `displayName` field
- `ActivityLevel` — SEDENTARY, LIGHT, MODERATE, ACTIVE, EXTRA_ACTIVE — each with a `description` and `multiplier` field
- All enums have **constructors**, **private fields**, **getter methods**, and **toString() overrides**, demonstrating that enums are full-fledged classes in Java

---

### 7. Constructors (Parameterized & Copy)

**Where it is used:**
- `Food(String, FoodCategory, double, double, double, double)` — **Parameterized constructor** initializing all fields
- `Food(Food other)` — **Copy constructor** creating an independent copy of a food item (used in `Meal.addFood()` to avoid aliasing)
- `User(String, String, int, double, double, String, ActivityLevel)` — Parameterized constructor that also calls `calculateBMR()` and `calculateDailyCalorieGoal()`
- `Meal(MealType)` — Initializes empty food and serving lists
- `DailyLog(int)` — Initializes with a day number and empty meal list
- `NutritionTracker(User, FoodDatabase)` — Initializes tracking with day 1
- All enum constructors — `FoodCategory(String)`, `MealType(String)`, `ActivityLevel(String, double)`

---

### 8. Static Members

**Where it is used:**
- `Food.fromFileString(String)` — **static factory method** to create Food from CSV string
- `User.fromFileString(String)` — **static factory method** to create User from CSV string
- `FoodDatabase.FOOD_FILE` — **static final constant** for file path (`"data/foods.txt"`)
- `UserManager.USER_FILE` — **static final constant** for file path (`"data/users.txt"`)
- `NutriSmart.main(String[])` — **static main method** as the program entry point

---

### 9. Access Modifiers

| Modifier    | Usage                                                                              |
|-------------|------------------------------------------------------------------------------------|
| `private`   | All data fields in every class, helper methods (`loadDefaultFoods()`, `saveUsers()`, `initializeDefaults()`, `readInt()`, `readDouble()`) |
| `public`    | All getter/setter methods, constructor methods, main business logic methods         |
| `static`    | Factory methods (`fromFileString()`), constants (`FOOD_FILE`), `main()`             |
| `final`     | Constants (`FOOD_FILE`, `USER_FILE`), enum fields (`displayName`, `multiplier`)     |

---

### 10. `this` Keyword

**Where it is used:**
- In every constructor to distinguish between parameters and instance fields:
  ```java
  this.name = name;           // Food constructor
  this.username = username;   // User constructor
  this.mealType = mealType;   // Meal constructor
  ```
- In the copy constructor: `this.name = other.name;`

---

## Java Functions & Methods Used

### String Methods
| Method | Usage | File |
|--------|-------|------|
| `String.format()` | Formatted output with `%s`, `%d`, `%.1f`, `%-20s` specifiers | All classes |
| `String.split(",")` | Parsing CSV data from files | `Food.java`, `User.java` |
| `String.trim()` | Removing whitespace from user input and parsed data | All classes |
| `String.toLowerCase()` | Case-insensitive food search | `FoodDatabase.java` |
| `String.contains()` | Partial name matching in food search | `FoodDatabase.java` |
| `String.equalsIgnoreCase()` | Case-insensitive gender and username comparison | `User.java`, `UserManager.java` |
| `String.equals()` | Exact match for login authentication | `UserManager.java` |
| `String.isEmpty()` | Checking for empty lines when reading files | `FoodDatabase.java`, `UserManager.java` |
| `"=".repeat(60)` | Creating separator lines for UI formatting | `NutritionTracker.java`, `NutriSmart.java` |

### Collection Methods (`java.util.ArrayList`, `java.util.List`)
| Method | Usage | File |
|--------|-------|------|
| `new ArrayList<>()` | Creating lists for foods, meals, users, servings, daily logs | All classes |
| `list.add()` | Adding items to lists | All classes |
| `list.get(index)` | Accessing list elements by index | `Meal.java`, `NutritionTracker.java` |
| `list.remove(index)` | Removing food from meal | `Meal.java` |
| `list.size()` | Getting count of items | All classes |
| `list.isEmpty()` | Checking if list has no elements | `Meal.java`, `DailyLog.java` |

### I/O Methods (`java.io.*`)
| Method | Usage | File |
|--------|-------|------|
| `new BufferedReader(new FileReader())` | Reading user and food data from text files | `FoodDatabase.java`, `UserManager.java` |
| `reader.readLine()` | Reading file line by line | `FoodDatabase.java`, `UserManager.java` |
| `new PrintWriter(new FileWriter())` | Writing user and food data to text files | `FoodDatabase.java`, `UserManager.java` |
| `writer.println()` | Writing a line to file | `FoodDatabase.java`, `UserManager.java` |
| `new File()` | Creating File objects to check existence | `FoodDatabase.java`, `UserManager.java` |
| `file.exists()` | Checking if data file exists before loading | `FoodDatabase.java`, `UserManager.java` |
| `dir.mkdirs()` | Creating the `data/` directory if it doesn't exist | `FoodDatabase.java`, `UserManager.java` |
| `try-with-resources` | Auto-closing readers/writers after use | `FoodDatabase.java`, `UserManager.java` |

### Scanner Methods (`java.util.Scanner`)
| Method | Usage | File |
|--------|-------|------|
| `new Scanner(System.in)` | Creating input scanner for CLI | `NutriSmart.java` |
| `scanner.nextLine()` | Reading user input as string | `NutriSmart.java` |
| `scanner.close()` | Closing scanner on exit | `NutriSmart.java` |

### Parsing Methods
| Method | Usage | File |
|--------|-------|------|
| `Integer.parseInt()` | Parsing age from file, menu choices | `User.java`, `NutriSmart.java` |
| `Double.parseDouble()` | Parsing nutritional values and weight/height from file | `Food.java`, `User.java`, `NutriSmart.java` |

### Math Methods (`java.lang.Math`)
| Method | Usage | File |
|--------|-------|------|
| `Math.min()` | Clamping progress bar fill to maximum bar length | `DailyLog.java` |
| `Math.abs()` | Showing absolute value when calories are exceeded | `NutritionTracker.java` |

### StringBuilder Methods
| Method | Usage | File |
|--------|-------|------|
| `new StringBuilder()` | Building complex formatted strings efficiently | `Meal.java`, `DailyLog.java` |
| `sb.append()` | Appending text to the builder | `Meal.java`, `DailyLog.java` |
| `sb.toString()` | Converting builder to final string | `Meal.java`, `DailyLog.java` |

### Enum Methods
| Method | Usage | File |
|--------|-------|------|
| `EnumType.values()` | Getting all enum constants for menu display | `NutriSmart.java` |
| `EnumType.valueOf()` | Converting string to enum constant (from file) | `Food.java`, `User.java` |
| `enumValue.name()` | Getting the enum constant name as string (for file storage) | `Food.java`, `User.java` |

### System & Output Methods
| Method | Usage | File |
|--------|-------|------|
| `System.out.println()` | Printing output with newline | All classes |
| `System.out.printf()` | Formatted printing with specifiers | `NutriSmart.java`, `NutritionTracker.java`, `FoodDatabase.java` |
| `System.out.print()` | Printing without newline (for input prompts) | `NutriSmart.java` |

---

## Project Structure

```
NutriSmart/
├── CONTRIBUTIONS.md            ← This file
└── src/
    └── nutrismart/             (Java package)
        ├── NutriSmart.java          ← Main class (entry point) — Siddhant Udavant
        ├── User.java                ← User model with BMR — Shruti Sonawane
        ├── UserManager.java         ← Login/Register system — Shruti Sonawane
        ├── ActivityLevel.java       ← Activity level enum — Shruti Sonawane
        ├── Food.java                ← Food item model — Trupti Shinde
        ├── FoodDatabase.java        ← Food database manager — Trupti Shinde
        ├── FoodCategory.java        ← Food category enum — Trupti Shinde
        ├── Meal.java                ← Meal with nutrition calc — Kanika Shinde
        ├── MealType.java            ← Meal type enum — Kanika Shinde
        ├── DailyLog.java            ← Daily tracking & progress — Aayush Shewale
        └── NutritionTracker.java    ← Analytics & suggestions — Aayush Shewale
```

---

## How to Compile and Run

```bash
cd NutriSmart/src
javac nutrismart/*.java
java nutrismart.NutriSmart
```

**Requirements:** Java JDK 11 or higher

---
