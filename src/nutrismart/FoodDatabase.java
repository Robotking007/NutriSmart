package nutrismart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the food database: loading, searching, adding, and saving food items.
 * Demonstrates file I/O and collection management.
 */
public class FoodDatabase {
    private List<Food> foods;
    private static final String FOOD_FILE = "data/foods.txt";

    public FoodDatabase() {
        foods = new ArrayList<>();
        loadDefaultFoods();
    }

    /**
     * Loads default foods into the database if no file exists.
     */
    private void loadDefaultFoods() {
        File file = new File(FOOD_FILE);
        if (file.exists()) {
            loadFromFile();
        } else {
            initializeDefaults();
            saveToFile();
        }
    }

    /**
     * Initializes the database with common food items.
     */
    private void initializeDefaults() {
        // Fruits
        foods.add(new Food("Apple", FoodCategory.FRUIT, 52, 0.3, 14, 0.2));
        foods.add(new Food("Banana", FoodCategory.FRUIT, 89, 1.1, 23, 0.3));
        foods.add(new Food("Orange", FoodCategory.FRUIT, 47, 0.9, 12, 0.1));
        foods.add(new Food("Strawberry", FoodCategory.FRUIT, 33, 0.7, 8, 0.3));
        foods.add(new Food("Grapes", FoodCategory.FRUIT, 69, 0.7, 18, 0.2));
        foods.add(new Food("Watermelon", FoodCategory.FRUIT, 30, 0.6, 8, 0.2));
        foods.add(new Food("Mango", FoodCategory.FRUIT, 60, 0.8, 15, 0.4));

        // Proteins
        foods.add(new Food("Chicken Breast", FoodCategory.PROTEIN, 165, 31, 0, 3.6));
        foods.add(new Food("Egg", FoodCategory.PROTEIN, 155, 13, 1.1, 11));
        foods.add(new Food("Salmon", FoodCategory.PROTEIN, 208, 20, 0, 13));
        foods.add(new Food("Beef", FoodCategory.PROTEIN, 250, 26, 0, 15));
        foods.add(new Food("Tuna", FoodCategory.PROTEIN, 132, 28, 0, 1.3));
        foods.add(new Food("Tofu", FoodCategory.PROTEIN, 76, 8, 1.9, 4.8));
        foods.add(new Food("Lentils", FoodCategory.PROTEIN, 116, 9, 20, 0.4));
        foods.add(new Food("Greek Yogurt", FoodCategory.DAIRY, 59, 10, 3.6, 0.4));

        // Vegetables
        foods.add(new Food("Broccoli", FoodCategory.VEGETABLE, 34, 2.8, 7, 0.4));
        foods.add(new Food("Spinach", FoodCategory.VEGETABLE, 23, 2.9, 3.6, 0.4));
        foods.add(new Food("Carrot", FoodCategory.VEGETABLE, 41, 0.9, 10, 0.2));
        foods.add(new Food("Tomato", FoodCategory.VEGETABLE, 18, 0.9, 3.9, 0.2));
        foods.add(new Food("Cucumber", FoodCategory.VEGETABLE, 16, 0.7, 3.6, 0.1));
        foods.add(new Food("Sweet Potato", FoodCategory.VEGETABLE, 86, 1.6, 20, 0.1));
        foods.add(new Food("Bell Pepper", FoodCategory.VEGETABLE, 31, 1, 6, 0.3));

        // Grains
        foods.add(new Food("Rice", FoodCategory.GRAIN, 130, 2.7, 28, 0.3));
        foods.add(new Food("Bread", FoodCategory.GRAIN, 265, 9, 49, 3.2));
        foods.add(new Food("Oatmeal", FoodCategory.GRAIN, 68, 2.4, 12, 1.4));
        foods.add(new Food("Pasta", FoodCategory.GRAIN, 131, 5, 25, 1.1));
    }

    /**
     * Loads food data from file.
     */
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FOOD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Food food = Food.fromFileString(line);
                    if (food != null) {
                        foods.add(food);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading food database. Using defaults.");
            initializeDefaults();
        }
    }

    /**
     * Saves the food database to file.
     */
    public void saveToFile() {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(FOOD_FILE))) {
                for (Food food : foods) {
                    writer.println(food.toFileString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving food database: " + e.getMessage());
        }
    }

    /**
     * Searches for foods by name (case-insensitive partial match).
     */
    public List<Food> searchByName(String name) {
        List<Food> results = new ArrayList<>();
        for (Food food : foods) {
            if (food.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(food);
            }
        }
        return results;
    }

    /**
     * Searches for foods by category.
     */
    public List<Food> searchByCategory(FoodCategory category) {
        List<Food> results = new ArrayList<>();
        for (Food food : foods) {
            if (food.getCategory() == category) {
                results.add(food);
            }
        }
        return results;
    }

    /**
     * Adds a new food to the database.
     */
    public void addFood(Food food) {
        foods.add(food);
        saveToFile();
    }

    /**
     * Gets a food by its index in the list.
     */
    public Food getFood(int index) {
        if (index >= 0 && index < foods.size()) {
            return foods.get(index);
        }
        return null;
    }

    /**
     * Returns all foods in the database.
     */
    public List<Food> getAllFoods() {
        return new ArrayList<>(foods);
    }

    /**
     * Returns the number of foods in the database.
     */
    public int size() {
        return foods.size();
    }

    /**
     * Displays all foods in a formatted table.
     */
    public void displayAllFoods() {
        System.out.println("\n" + "=".repeat(85));
        System.out.printf("%-4s %-20s %-10s %8s %8s %8s %8s\n",
                "#", "Name", "Category", "Cal", "Protein", "Carbs", "Fats");
        System.out.println("-".repeat(85));
        for (int i = 0; i < foods.size(); i++) {
            Food f = foods.get(i);
            System.out.printf("%-4d %-20s %-10s %7.1f %7.1fg %7.1fg %7.1fg\n",
                    i + 1, f.getName(), f.getCategory(), f.getCalories(),
                    f.getProtein(), f.getCarbs(), f.getFats());
        }
        System.out.println("=".repeat(85));
        System.out.println("(Values are per 100g serving)");
    }
}
