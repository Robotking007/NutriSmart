package nutrismart;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for NutriSmart - Food and Nutrient Tracking System.
 * Handles the command-line user interface and orchestrates all components.
 *
 * OOP Concepts Demonstrated:
 * - Encapsulation: Private fields with public getters/setters in all classes
 * - Inheritance: Enum types extend Java's Enum class
 * - Polymorphism: toString() overrides, method overloading
 * - Abstraction: Clean interfaces hiding implementation details
 * - Composition: NutritionTracker contains DailyLog, Meal contains Food
 * - Aggregation: FoodDatabase aggregates Food objects
 */
public class NutriSmart {
    private Scanner scanner;
    private UserManager userManager;
    private FoodDatabase foodDatabase;
    private NutritionTracker tracker;

    public NutriSmart() {
        scanner = new Scanner(System.in);
        userManager = new UserManager();
        foodDatabase = new FoodDatabase();
    }

    /**
     * Starts the application.
     */
    public void start() {
        printBanner();
        boolean running = true;

        while (running) {
            if (!userManager.isLoggedIn()) {
                running = showAuthMenu();
            } else {
                running = showMainMenu();
            }
        }

        System.out.println("\nThank you for using NutriSmart! Stay healthy!");
        scanner.close();
    }

    /**
     * Displays the welcome banner.
     */
    private void printBanner() {
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("       _   _       _        _ ____                       _   ");
        System.out.println("      | \\ | |_   _| |_ _ __(_) ___| _ __ ___   __ _ _ __| |_ ");
        System.out.println("      |  \\| | | | | __| '__| \\___ \\| '_ ` _ \\ / _` | '__| __|");
        System.out.println("      | |\\  | |_| | |_| |  | |___) | | | | | | (_| | |  | |_ ");
        System.out.println("      |_| \\_|\\__,_|\\__|_|  |_|____/|_| |_| |_|\\__,_|_|   \\__|");
        System.out.println();
        System.out.println("          Food and Nutrient Tracking System v1.0");
        System.out.println("=".repeat(60));
    }

    // ========================== AUTH MENU ==========================

    /**
     * Shows authentication menu (login/register).
     */
    private boolean showAuthMenu() {
        System.out.println("\n--- Welcome to NutriSmart ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = readInt();
        switch (choice) {
            case 1: handleLogin(); return true;
            case 2: handleRegister(); return true;
            case 3: return false;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    /**
     * Handles user login.
     */
    private void handleLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        if (userManager.login(username, password)) {
            initializeTracker();
        }
    }

    /**
     * Handles new user registration.
     */
    private void handleRegister() {
        System.out.println("\n--- Registration ---");

        System.out.print("Choose a username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Choose a password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter your age: ");
        int age = readInt();

        System.out.print("Enter your weight (kg): ");
        double weight = readDouble();

        System.out.print("Enter your height (cm): ");
        double height = readDouble();

        System.out.print("Enter your gender (Male/Female): ");
        String gender = scanner.nextLine().trim();

        System.out.println("\nSelect your activity level:");
        ActivityLevel[] levels = ActivityLevel.values();
        for (int i = 0; i < levels.length; i++) {
            System.out.printf("  %d. %s\n", i + 1, levels[i].getDescription());
        }
        System.out.print("Choose (1-" + levels.length + "): ");
        int levelChoice = readInt() - 1;

        if (levelChoice < 0 || levelChoice >= levels.length) {
            levelChoice = 0;
        }

        if (userManager.register(username, password, age, weight, height, gender, levels[levelChoice])) {
            initializeTracker();
        }
    }

    /**
     * Initializes the nutrition tracker after login/register.
     */
    private void initializeTracker() {
        tracker = new NutritionTracker(userManager.getCurrentUser(), foodDatabase);
        System.out.printf("\nYour daily calorie goal: %.0f cal\n",
                userManager.getCurrentUser().getDailyCalorieGoal());
    }

    // ========================== MAIN MENU ==========================

    /**
     * Shows the main menu after login.
     */
    private boolean showMainMenu() {
        System.out.println("\n--- NutriSmart Main Menu (Day " + tracker.getCurrentDay() + ") ---");
        System.out.println("1. Log a Meal");
        System.out.println("2. View Today's Meals");
        System.out.println("3. View Daily Progress");
        System.out.println("4. Get Meal Suggestions");
        System.out.println("5. Browse Food Database");
        System.out.println("6. Add New Food to Database");
        System.out.println("7. Search Foods");
        System.out.println("8. Move to Next Day");
        System.out.println("9. View Weekly Overview");
        System.out.println("10. View My Profile");
        System.out.println("11. Logout");
        System.out.println("12. Exit");
        System.out.print("Choose an option: ");

        int choice = readInt();
        switch (choice) {
            case 1: handleLogMeal(); break;
            case 2: tracker.showTodayMeals(); break;
            case 3: tracker.showDailyProgress(); break;
            case 4: tracker.showSuggestions(); break;
            case 5: foodDatabase.displayAllFoods(); break;
            case 6: handleAddFood(); break;
            case 7: handleSearchFoods(); break;
            case 8: tracker.nextDay(); break;
            case 9: tracker.showWeeklyOverview(); break;
            case 10: showProfile(); break;
            case 11:
                userManager.logout();
                tracker = null;
                break;
            case 12:
                userManager.logout();
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

    // ========================== MEAL LOGGING ==========================

    /**
     * Handles the process of logging a new meal.
     */
    private void handleLogMeal() {
        System.out.println("\n--- Log a Meal ---");
        System.out.println("Select meal type:");
        MealType[] types = MealType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("  %d. %s\n", i + 1, types[i].getDisplayName());
        }
        System.out.print("Choose (1-" + types.length + "): ");
        int typeChoice = readInt() - 1;

        if (typeChoice < 0 || typeChoice >= types.length) {
            System.out.println("Invalid meal type.");
            return;
        }

        Meal meal = new Meal(types[typeChoice]);
        boolean addingFoods = true;

        while (addingFoods) {
            System.out.println("\n  Add food to " + types[typeChoice].getDisplayName() + ":");
            System.out.println("  1. Search food by name");
            System.out.println("  2. Browse all foods");
            System.out.println("  3. Browse by category");
            System.out.println("  4. Done adding foods");
            System.out.print("  Choose: ");

            int foodChoice = readInt();
            switch (foodChoice) {
                case 1:
                    addFoodBySearch(meal);
                    break;
                case 2:
                    addFoodFromList(meal);
                    break;
                case 3:
                    addFoodByCategory(meal);
                    break;
                case 4:
                    addingFoods = false;
                    break;
                default:
                    System.out.println("  Invalid option.");
            }
        }

        if (meal.getFoodCount() > 0) {
            tracker.addMeal(meal);
            System.out.println("\n Meal logged successfully!");
            System.out.println(meal);
        } else {
            System.out.println("  No foods added. Meal not saved.");
        }
    }

    /**
     * Adds a food to a meal by searching by name.
     */
    private void addFoodBySearch(Meal meal) {
        System.out.print("  Enter food name to search: ");
        String searchTerm = scanner.nextLine().trim();
        List<Food> results = foodDatabase.searchByName(searchTerm);

        if (results.isEmpty()) {
            System.out.println("  No foods found matching '" + searchTerm + "'.");
            System.out.print("  Would you like to add it manually? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                Food newFood = createCustomFood(searchTerm);
                if (newFood != null) {
                    foodDatabase.addFood(newFood);
                    System.out.print("  Serving size in grams: ");
                    double serving = readDouble();
                    meal.addFood(newFood, serving);
                    System.out.println("  Added " + newFood.getName() + " (" + serving + "g) to meal.");
                }
            }
            return;
        }

        System.out.println("\n  Search Results:");
        for (int i = 0; i < results.size(); i++) {
            System.out.printf("  %d. %s\n", i + 1, results.get(i));
        }
        System.out.print("  Select food # (0 to cancel): ");
        int sel = readInt() - 1;

        if (sel >= 0 && sel < results.size()) {
            System.out.print("  Serving size in grams: ");
            double serving = readDouble();
            meal.addFood(results.get(sel), serving);
            System.out.println("  Added " + results.get(sel).getName() + " (" + serving + "g) to meal.");
        }
    }

    /**
     * Adds a food from the full food list.
     */
    private void addFoodFromList(Meal meal) {
        foodDatabase.displayAllFoods();
        System.out.print("  Select food # (0 to cancel): ");
        int sel = readInt() - 1;

        if (sel >= 0 && sel < foodDatabase.size()) {
            Food selected = foodDatabase.getFood(sel);
            System.out.print("  Serving size in grams: ");
            double serving = readDouble();
            meal.addFood(selected, serving);
            System.out.println("  Added " + selected.getName() + " (" + serving + "g) to meal.");
        }
    }

    /**
     * Adds a food by browsing a specific category.
     */
    private void addFoodByCategory(Meal meal) {
        System.out.println("  Select category:");
        FoodCategory[] categories = FoodCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("    %d. %s\n", i + 1, categories[i].getDisplayName());
        }
        System.out.print("  Choose: ");
        int catChoice = readInt() - 1;

        if (catChoice < 0 || catChoice >= categories.length) {
            System.out.println("  Invalid category.");
            return;
        }

        List<Food> categoryFoods = foodDatabase.searchByCategory(categories[catChoice]);
        if (categoryFoods.isEmpty()) {
            System.out.println("  No foods found in this category.");
            return;
        }

        System.out.println("\n  " + categories[catChoice].getDisplayName() + " Foods:");
        for (int i = 0; i < categoryFoods.size(); i++) {
            System.out.printf("  %d. %s\n", i + 1, categoryFoods.get(i));
        }
        System.out.print("  Select food # (0 to cancel): ");
        int sel = readInt() - 1;

        if (sel >= 0 && sel < categoryFoods.size()) {
            System.out.print("  Serving size in grams: ");
            double serving = readDouble();
            meal.addFood(categoryFoods.get(sel), serving);
            System.out.println("  Added " + categoryFoods.get(sel).getName() + " (" + serving + "g) to meal.");
        }
    }

    // ========================== FOOD MANAGEMENT ==========================

    /**
     * Handles adding a new food to the database.
     */
    private void handleAddFood() {
        System.out.println("\n--- Add New Food ---");
        System.out.print("Food name: ");
        String name = scanner.nextLine().trim();

        Food newFood = createCustomFood(name);
        if (newFood != null) {
            foodDatabase.addFood(newFood);
            System.out.println("\n Food added to database: " + newFood);
        }
    }

    /**
     * Creates a custom food from user input.
     */
    private Food createCustomFood(String name) {
        System.out.println("  Select category:");
        FoodCategory[] categories = FoodCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("    %d. %s\n", i + 1, categories[i].getDisplayName());
        }
        System.out.print("  Choose: ");
        int catChoice = readInt() - 1;

        if (catChoice < 0 || catChoice >= categories.length) {
            System.out.println("  Invalid category. Food not added.");
            return null;
        }

        System.out.print("  Calories (per 100g): ");
        double cal = readDouble();
        System.out.print("  Protein (g per 100g): ");
        double protein = readDouble();
        System.out.print("  Carbs (g per 100g): ");
        double carbs = readDouble();
        System.out.print("  Fats (g per 100g): ");
        double fats = readDouble();

        return new Food(name, categories[catChoice], cal, protein, carbs, fats);
    }

    /**
     * Handles food search.
     */
    private void handleSearchFoods() {
        System.out.println("\n--- Search Foods ---");
        System.out.println("1. Search by name");
        System.out.println("2. Search by category");
        System.out.print("Choose: ");

        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("Enter search term: ");
                String term = scanner.nextLine().trim();
                List<Food> results = foodDatabase.searchByName(term);
                if (results.isEmpty()) {
                    System.out.println("No foods found matching '" + term + "'.");
                } else {
                    System.out.println("\nSearch Results (" + results.size() + " found):");
                    for (int i = 0; i < results.size(); i++) {
                        System.out.printf("  %d. %s\n", i + 1, results.get(i));
                    }
                }
                break;

            case 2:
                FoodCategory[] categories = FoodCategory.values();
                System.out.println("Select category:");
                for (int i = 0; i < categories.length; i++) {
                    System.out.printf("  %d. %s\n", i + 1, categories[i].getDisplayName());
                }
                System.out.print("Choose: ");
                int catChoice = readInt() - 1;

                if (catChoice >= 0 && catChoice < categories.length) {
                    List<Food> catResults = foodDatabase.searchByCategory(categories[catChoice]);
                    System.out.println("\n" + categories[catChoice].getDisplayName() + " Foods:");
                    for (int i = 0; i < catResults.size(); i++) {
                        System.out.printf("  %d. %s\n", i + 1, catResults.get(i));
                    }
                }
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    // ========================== PROFILE ==========================

    /**
     * Shows the user's profile information.
     */
    private void showProfile() {
        User user = userManager.getCurrentUser();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  MY PROFILE");
        System.out.println("=".repeat(50));
        System.out.println(user);
        System.out.println("=".repeat(50));
    }

    // ========================== UTILITY METHODS ==========================

    /**
     * Safely reads an integer from input.
     */
    private int readInt() {
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Safely reads a double from input.
     */
    private double readDouble() {
        try {
            String line = scanner.nextLine().trim();
            return Double.parseDouble(line);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // ========================== MAIN METHOD ==========================

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        NutriSmart app = new NutriSmart();
        app.start();
    }
}
