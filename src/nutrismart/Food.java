package nutrismart;

/**
 * Represents a food item with its nutritional information.
 * Demonstrates encapsulation with private fields and public getters.
 */
public class Food {
    private String name;
    private FoodCategory category;
    private double calories;    // per 100g
    private double protein;     // grams per 100g
    private double carbs;       // grams per 100g
    private double fats;        // grams per 100g

    // Constructor
    public Food(String name, FoodCategory category, double calories, double protein, double carbs, double fats) {
        this.name = name;
        this.category = category;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    // Copy constructor
    public Food(Food other) {
        this.name = other.name;
        this.category = other.category;
        this.calories = other.calories;
        this.protein = other.protein;
        this.carbs = other.carbs;
        this.fats = other.fats;
    }

    // Getters
    public String getName() {
        return name;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    /**
     * Converts food data to a file-saveable string format.
     */
    public String toFileString() {
        return name + "," + category.name() + "," + calories + "," + protein + "," + carbs + "," + fats;
    }

    /**
     * Creates a Food object from a file-formatted string.
     */
    public static Food fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            return new Food(
                parts[0].trim(),
                FoodCategory.valueOf(parts[1].trim()),
                Double.parseDouble(parts[2].trim()),
                Double.parseDouble(parts[3].trim()),
                Double.parseDouble(parts[4].trim()),
                Double.parseDouble(parts[5].trim())
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-10s | %7.1f cal | P: %5.1fg | C: %5.1fg | F: %5.1fg",
                name, category, calories, protein, carbs, fats);
    }
}
