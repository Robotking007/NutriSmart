package nutrismart;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a meal consisting of multiple food items.
 * Demonstrates aggregation (has-a relationship with Food).
 */
public class Meal {
    private MealType mealType;
    private List<Food> foods;
    private List<Double> servings; // serving size in grams for each food

    public Meal(MealType mealType) {
        this.mealType = mealType;
        this.foods = new ArrayList<>();
        this.servings = new ArrayList<>();
    }

    /**
     * Adds a food item to the meal with a specified serving size.
     */
    public void addFood(Food food, double servingGrams) {
        foods.add(new Food(food)); // use copy constructor
        servings.add(servingGrams);
    }

    /**
     * Removes a food item from the meal by index.
     */
    public boolean removeFood(int index) {
        if (index >= 0 && index < foods.size()) {
            foods.remove(index);
            servings.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Calculates total calories for this meal considering serving sizes.
     */
    public double getTotalCalories() {
        double total = 0;
        for (int i = 0; i < foods.size(); i++) {
            total += (foods.get(i).getCalories() * servings.get(i)) / 100.0;
        }
        return total;
    }

    /**
     * Calculates total protein for this meal.
     */
    public double getTotalProtein() {
        double total = 0;
        for (int i = 0; i < foods.size(); i++) {
            total += (foods.get(i).getProtein() * servings.get(i)) / 100.0;
        }
        return total;
    }

    /**
     * Calculates total carbs for this meal.
     */
    public double getTotalCarbs() {
        double total = 0;
        for (int i = 0; i < foods.size(); i++) {
            total += (foods.get(i).getCarbs() * servings.get(i)) / 100.0;
        }
        return total;
    }

    /**
     * Calculates total fats for this meal.
     */
    public double getTotalFats() {
        double total = 0;
        for (int i = 0; i < foods.size(); i++) {
            total += (foods.get(i).getFats() * servings.get(i)) / 100.0;
        }
        return total;
    }

    // Getters
    public MealType getMealType() { return mealType; }
    public List<Food> getFoods() { return foods; }
    public List<Double> getServings() { return servings; }
    public int getFoodCount() { return foods.size(); }

    /**
     * Converts meal data to file-saveable format.
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mealType.name());
        for (int i = 0; i < foods.size(); i++) {
            sb.append("|").append(foods.get(i).getName()).append(":").append(servings.get(i));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== %s ===\n", mealType.getDisplayName()));

        if (foods.isEmpty()) {
            sb.append("  No foods added yet.\n");
        } else {
            for (int i = 0; i < foods.size(); i++) {
                double serving = servings.get(i);
                Food f = foods.get(i);
                sb.append(String.format("  %d. %-18s | %5.0fg | %6.1f cal | P:%.1fg C:%.1fg F:%.1fg\n",
                        i + 1, f.getName(), serving,
                        (f.getCalories() * serving) / 100.0,
                        (f.getProtein() * serving) / 100.0,
                        (f.getCarbs() * serving) / 100.0,
                        (f.getFats() * serving) / 100.0));
            }
            sb.append(String.format("  ---- Total: %.1f cal | P:%.1fg | C:%.1fg | F:%.1fg\n",
                    getTotalCalories(), getTotalProtein(), getTotalCarbs(), getTotalFats()));
        }
        return sb.toString();
    }
}
