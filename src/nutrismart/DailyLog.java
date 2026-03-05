package nutrismart;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single day's food log, containing multiple meals.
 * Tracks nutrition totals for the day.
 */
public class DailyLog {
    private int dayNumber;
    private List<Meal> meals;

    public DailyLog(int dayNumber) {
        this.dayNumber = dayNumber;
        this.meals = new ArrayList<>();
    }

    /**
     * Adds a meal to the daily log.
     */
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    /**
     * Gets all meals of a specific type (e.g., all breakfast meals).
     */
    public List<Meal> getMealsByType(MealType type) {
        List<Meal> result = new ArrayList<>();
        for (Meal meal : meals) {
            if (meal.getMealType() == type) {
                result.add(meal);
            }
        }
        return result;
    }

    /**
     * Calculates total calories consumed for the day.
     */
    public double getTotalCalories() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.getTotalCalories();
        }
        return total;
    }

    /**
     * Calculates total protein consumed for the day.
     */
    public double getTotalProtein() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.getTotalProtein();
        }
        return total;
    }

    /**
     * Calculates total carbs consumed for the day.
     */
    public double getTotalCarbs() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.getTotalCarbs();
        }
        return total;
    }

    /**
     * Calculates total fats consumed for the day.
     */
    public double getTotalFats() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.getTotalFats();
        }
        return total;
    }

    // Getters
    public int getDayNumber() { return dayNumber; }
    public List<Meal> getMeals() { return meals; }
    public int getMealCount() { return meals.size(); }

    /**
     * Generates a visual progress bar for calorie consumption.
     */
    public String getProgressBar(double calorieGoal) {
        double consumed = getTotalCalories();
        double percentage = (consumed / calorieGoal) * 100;
        int barLength = 40;
        int filled = (int) Math.min((percentage / 100) * barLength, barLength);
        int empty = barLength - filled;

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < filled; i++) bar.append("#");
        for (int i = 0; i < empty; i++) bar.append("-");
        bar.append("]");
        bar.append(String.format(" %.1f%% (%.0f / %.0f cal)", percentage, consumed, calorieGoal));

        return bar.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n===== Day %d Summary =====\n", dayNumber));

        if (meals.isEmpty()) {
            sb.append("No meals logged today.\n");
        } else {
            for (Meal meal : meals) {
                sb.append(meal.toString());
            }
            sb.append(String.format("\n--- Day %d Totals ---\n", dayNumber));
            sb.append(String.format("Calories: %.1f | Protein: %.1fg | Carbs: %.1fg | Fats: %.1fg\n",
                    getTotalCalories(), getTotalProtein(), getTotalCarbs(), getTotalFats()));
        }
        return sb.toString();
    }
}
