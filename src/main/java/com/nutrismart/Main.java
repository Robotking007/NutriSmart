package com.nutrismart;

import com.nutrismart.models.*;
import com.nutrismart.controllers.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n===== Welcome to NutriSmart: Your Food & Nutrient Tracker =====\n");
        
        DatabaseManager db = DatabaseManager.getInstance();
        db.loadUsersFromFile();
        
        addTestUsers(db);
        
        System.out.println("\nDatabase Loaded Successfully!\n");
        
        User currentUser = null;
        boolean running = true;
        
        while (running && currentUser == null) {
            System.out.println("\n===== Login/Register =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. View Sample Data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    currentUser = loginScreen(db);
                    break;
                case 2:
                    registerScreen(db);
                    break;
                case 3:
                    displaySampleData();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        if (currentUser != null) {
            userDashboard(currentUser, db);
        }
        
        db.saveUsersToFile();
        
        System.out.println("\n===== Thank You for Using NutriSmart! =====");
    }
    
    private static void addTestUsers(DatabaseManager db) {
        boolean hasTrupti = false;
        boolean hasSamruddhi = false;
        
        for (String username : db.getAllUsernames()) {
            if (username.equals("truptishinde")) hasTrupti = true;
            if (username.equals("samrudhhishinde")) hasSamruddhi = true;
        }
        
        if (!hasTrupti) {
            User trupti = new User("truptishinde", "password123", 19, 50, 160, "Female", 1588.02f);
            Meal meal1 = new Meal("Quick Add");
            meal1.addFood(FoodFactory.createFood("Fruit", "Apple", 0.3f, 14.0f, 0.2f));
            trupti.addMeal(meal1);
            db.addUser(trupti);
            System.out.println("Added test user: truptishinde");
        }
        
        if (!hasSamruddhi) {
            User samruddhi = new User("samrudhhishinde", "password123", 19, 60, 160, "Female", 1946.76f);
            
            Meal day1meal1 = new Meal("Quick Add");
            day1meal1.addFood(FoodFactory.createFood("Fruit", "Apple", 0.3f, 14.0f, 0.2f));
            samruddhi.addMeal(day1meal1);
            
            Meal day1meal2 = new Meal("Quick Add");
            day1meal2.addFood(FoodFactory.createFood("Fruit", "Mango", 0.8f, 15.0f, 0.4f));
            samruddhi.addMeal(day1meal2);
            
            Meal day1meal3 = new Meal("My Lunch");
            day1meal3.addFood(FoodFactory.createFood("Protein", "Eggs", 12.0f, 1.1f, 10.0f));
            day1meal3.addFood(FoodFactory.createFood("Fruit", "Pineapple", 0.5f, 13.1f, 0.1f));
            samruddhi.addMeal(day1meal3);
            
            samruddhi.nextDay();
            Meal day2meal = new Meal("My Lunch");
            day2meal.addFood(FoodFactory.createFood("Protein", "Eggs", 12.0f, 1.1f, 10.0f));
            day2meal.addFood(FoodFactory.createFood("Fruit", "Pineapple", 0.5f, 13.1f, 0.1f));
            samruddhi.addMeal(day2meal);
            
            db.addUser(samruddhi);
            System.out.println("Added test user: samrudhhishinde");
        }
    }
    
    private static void displaySampleData() {
        System.out.println("\n=== Sample User Data ===");
        printResourceFile("resources/truptishinde_details.txt");
        printResourceFile("resources/samrudhhishinde_details.txt");
        
        System.out.println("\n=== Summary Table (samrudhhishinde) ===");
        printResourceFile("resources/samrudhhishinde_summary.csv");
    }
    
    private static void printResourceFile(String path) {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("File not found: " + path);
            return;
        }
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
    
    private static float getFloatInput() {
        while (!scanner.hasNextFloat()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        float input = scanner.nextFloat();
        scanner.nextLine();
        return input;
    }
    
    private static User loginScreen(DatabaseManager db) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = db.authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome back, " + username + "!");
            return user;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }
    
    private static void registerScreen(DatabaseManager db) {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getIntInput();
        System.out.print("Enter weight (kg): ");
        float weight = getFloatInput();
        System.out.print("Enter height (cm): ");
        float height = getFloatInput();
        
        String gender;
        while (true) {
            System.out.print("Enter gender (Male/Female): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                break;
            }
            System.out.println("Invalid gender! Please enter 'Male' or 'Female'.");
        }
        
        User newUser = new User(username, password, age, weight, height, gender);
        db.addUser(newUser);
        
        GoalTracker tracker = new GoalTracker();
        tracker.calculateAndSetGoal(newUser);
        
        System.out.println("Registration successful! You can now login.");
    }
    
    private static void userDashboard(User currentUser, DatabaseManager db) {
        NutritionAnalyzer analyzer = new NutritionAnalyzer();
        GoalTracker tracker = new GoalTracker();
        Admin admin = new Admin("Admin", "root", 2500);
        
        boolean inDashboard = true;
        while (inDashboard) {
            System.out.println("\n\n===== Welcome, " + currentUser.getUsername() + "! =====");
            System.out.println("Current Day: Day-" + currentUser.getCurrentDay());
            System.out.println("1. Add Food Item (or Existing Meal)");
            System.out.println("2. Create a New Meal");
            System.out.println("3. View All Meals");
            System.out.println("4. Analyze Today's Nutrition");
            System.out.println("5. View Weekly Progress");
            System.out.println("6. Track Goal & Get Suggestions");
            System.out.println("7. Update Calorie Goal");
            System.out.println("8. Move to Next Day");
            System.out.println("9. View Summary Table");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addFoodMenu(currentUser, db, admin);
                    break;
                case 2:
                    createNewMeal(currentUser, db);
                    break;
                case 3:
                    currentUser.displayMeals();
                    break;
                case 4:
                    analyzer.generateUserReport(currentUser);
                    break;
                case 5:
                    analyzer.generateWeeklyReport(currentUser);
                    break;
                case 6:
                    tracker.checkGoal(currentUser);
                    tracker.suggestNextMeal(currentUser);
                    break;
                case 7:
                    tracker.calculateAndSetGoal(currentUser);
                    break;
                case 8:
                    currentUser.nextDay();
                    System.out.println("Moved to Day-" + currentUser.getCurrentDay());
                    break;
                case 9:
                    displayUserSummary(currentUser);
                    break;
                case 10:
                    inDashboard = false;
                    currentUser.saveUserData();
                    System.out.println("All data saved! Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void displayUserSummary(User user) {
        System.out.println("\n=== Daily Summary for " + user.getUsername() + " ===");
        User.WeeklyProgressData[] weeklyData = user.getWeeklyProgress();
        
        System.out.println("+-------+----------+-------+");
        System.out.println("| Day   | Calories | Meals |");
        System.out.println("+-------+----------+-------+");
        
        for (User.WeeklyProgressData data : weeklyData) {
            System.out.printf("| %-5s | %8.1f | %5d |%n", 
                            data.date, data.calories, data.mealCount);
        }
        System.out.println("+-------+----------+-------+");
    }
    
    private static void addFoodMenu(User currentUser, DatabaseManager db, Admin admin) {
        System.out.println("\n--- Add Food Item or Existing Meal ---");
        System.out.println("1. Add individual food item");
        System.out.println("2. Add existing meal by name");
        System.out.print("Choose option: ");
        
        int addChoice = getIntInput();
        
        if (addChoice == 2) {
            System.out.print("Enter the name of the meal to add: ");
            String existingMealName = scanner.nextLine();
            
            Meal foundMeal = currentUser.findMealByName(existingMealName);
            if (foundMeal != null) {
                Meal newMeal = new Meal(foundMeal);
                currentUser.addMeal(newMeal);
                System.out.println("Meal '" + existingMealName + "' added successfully!");
                System.out.println("Total calories: " + newMeal.totalCalories());
            } else {
                System.out.println("Meal '" + existingMealName + "' not found!");
            }
        } else {
            System.out.print("\nEnter food type (Fruit/Protein/Vegetable): ");
            String type = scanner.nextLine();
            System.out.print("Enter food name: ");
            String name = scanner.nextLine();
            
            NutritionTuple nutrition = db.getNutritionData(name);
            float protein = nutrition.getProtein();
            float carbs = nutrition.getCarbs();
            float fats = nutrition.getFats();
            
            if (protein == 0 && carbs == 0 && fats == 0) {
                System.out.println("Food not found in database. Please enter nutrition manually:");
                System.out.print("Enter Protein (g): ");
                protein = getFloatInput();
                System.out.print("Enter Carbs (g): ");
                carbs = getFloatInput();
                System.out.print("Enter Fats (g): ");
                fats = getFloatInput();
            } else {
                System.out.println("Found nutrition data: P=" + protein + "g, C=" + carbs + "g, F=" + fats + "g");
            }
            
            FoodItem newFood = FoodFactory.createFood(type, name, protein, carbs, fats);
            if (newFood != null) {
                admin.addFoodToDatabase(newFood);
                Meal singleMeal = new Meal("Quick Add");
                singleMeal.addFood(newFood);
                currentUser.addMeal(singleMeal);
                System.out.println("Food added successfully as a meal!");
            } else {
                System.out.println("Invalid food type!");
            }
        }
    }
    
    private static void createNewMeal(User currentUser, DatabaseManager db) {
        Meal meal = new Meal("Temp");
        
        boolean more = true;
        while (more) {
            System.out.print("\nEnter food type (Fruit/Protein/Vegetable): ");
            String type = scanner.nextLine();
            System.out.print("Enter food name: ");
            String name = scanner.nextLine();
            
            NutritionTuple nutrition = db.getNutritionData(name);
            float protein = nutrition.getProtein();
            float carbs = nutrition.getCarbs();
            float fats = nutrition.getFats();
            
            if (protein == 0 && carbs == 0 && fats == 0) {
                System.out.println("Food not found. Enter nutrition manually:");
                System.out.print("Enter Protein (g): ");
                protein = getFloatInput();
                System.out.print("Enter Carbs (g): ");
                carbs = getFloatInput();
                System.out.print("Enter Fats (g): ");
                fats = getFloatInput();
            } else {
                System.out.println("Found: P=" + protein + "g, C=" + carbs + "g, F=" + fats + "g");
            }
            
            FoodItem food = FoodFactory.createFood(type, name, protein, carbs, fats);
            meal.addFood(food);
            
            System.out.print("Add another food to this meal? (y/n): ");
            String response = scanner.nextLine();
            more = response.equalsIgnoreCase("y");
        }
        
        System.out.print("\nEnter a name for this meal: ");
        String mealName = scanner.nextLine();
        meal.setMealName(mealName);
        
        currentUser.addMeal(meal);
        System.out.println("Meal '" + mealName + "' created and saved successfully!");
        System.out.println("Total calories: " + meal.totalCalories());
    }
}
