package nutrismart;

import java.util.ArrayList;
import java.util.List;

/**
 * Core tracker that manages daily logs, nutrition analysis, and smart suggestions.
 * Demonstrates composition, data analysis, and recommendation logic.
 */
public class NutritionTracker {
    private User user;
    private List<DailyLog> dailyLogs;
    private int currentDay;
    private FoodDatabase foodDatabase;

    public NutritionTracker(User user, FoodDatabase foodDatabase) {
        this.user = user;
        this.foodDatabase = foodDatabase;
        this.dailyLogs = new ArrayList<>();
        this.currentDay = 1;
        dailyLogs.add(new DailyLog(currentDay));
    }

    /**
     * Returns the current day's log.
     */
    public DailyLog getCurrentDayLog() {
        return dailyLogs.get(dailyLogs.size() - 1);
    }

    /**
     * Adds a meal to the current day's log.
     */
    public void addMeal(Meal meal) {
        getCurrentDayLog().addMeal(meal);
    }

    /**
     * Moves to the next day, creating a new DailyLog.
     */
    public void nextDay() {
        currentDay++;
        dailyLogs.add(new DailyLog(currentDay));
        System.out.println("\n--- Advanced to Day " + currentDay + " ---");
    }

    /**
     * Returns remaining calories for the current day.
     */
    public double getRemainingCalories() {
        return user.getDailyCalorieGoal() - getCurrentDayLog().getTotalCalories();
    }

    /**
     * Displays the current day's progress with a visual bar.
     */
    public void showDailyProgress() {
        DailyLog today = getCurrentDayLog();
        double goal = user.getDailyCalorieGoal();
        double consumed = today.getTotalCalories();
        double remaining = goal - consumed;

        System.out.println("\n" + "=".repeat(60));
        System.out.printf("  Day %d Progress for %s\n", currentDay, user.getUsername());
        System.out.println("=".repeat(60));
        System.out.println("  Calorie Goal:  " + String.format("%.0f cal", goal));
        System.out.println("  Consumed:      " + String.format("%.0f cal", consumed));

        if (remaining >= 0) {
            System.out.println("  Remaining:     " + String.format("%.0f cal", remaining));
        } else {
            System.out.println("  EXCEEDED BY:   " + String.format("%.0f cal", Math.abs(remaining)));
        }

        System.out.println("\n  Progress: " + today.getProgressBar(goal));

        // Macronutrient breakdown
        System.out.println("\n  --- Macronutrient Breakdown ---");
        System.out.printf("  Protein:  %.1fg\n", today.getTotalProtein());
        System.out.printf("  Carbs:    %.1fg\n", today.getTotalCarbs());
        System.out.printf("  Fats:     %.1fg\n", today.getTotalFats());
        System.out.println("=".repeat(60));
    }

    /**
     * Shows a weekly overview of all tracked days.
     */
    public void showWeeklyOverview() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  WEEKLY OVERVIEW");
        System.out.println("=".repeat(70));

        double goal = user.getDailyCalorieGoal();
        double totalWeekCal = 0;

        for (DailyLog log : dailyLogs) {
            double dayCal = log.getTotalCalories();
            totalWeekCal += dayCal;

            System.out.printf("  Day %d: ", log.getDayNumber());
            System.out.println(log.getProgressBar(goal));
        }

        System.out.println("-".repeat(70));
        System.out.printf("  Average daily intake: %.0f cal (Goal: %.0f cal)\n",
                totalWeekCal / dailyLogs.size(), goal);
        System.out.printf("  Total days tracked: %d\n", dailyLogs.size());
        System.out.println("=".repeat(70));
    }

    /**
     * Provides smart meal suggestions based on remaining calories and macros.
     */
    public void showSuggestions() {
        double remaining = getRemainingCalories();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  SMART MEAL SUGGESTIONS");
        System.out.println("=".repeat(60));

        if (remaining <= 0) {
            System.out.println("  You've reached your calorie goal for today!");
            System.out.println("  Consider light options if still hungry:");
            System.out.println("  - Cucumber slices (16 cal/100g)");
            System.out.println("  - Herbal tea (0 cal)");
            System.out.println("  - A small portion of watermelon (30 cal/100g)");
            System.out.println("=".repeat(60));
            return;
        }

        DailyLog today = getCurrentDayLog();
        double currentProtein = today.getTotalProtein();
        double currentCarbs = today.getTotalCarbs();

        System.out.printf("  You have %.0f calories remaining.\n\n", remaining);

        // Suggest balanced meal
        if (remaining >= 400) {
            System.out.println("  Suggested Full Meal (~400-500 cal):");
            System.out.println("  - Chicken Breast (150g) + Rice (100g) + Broccoli (100g)");
            System.out.printf("    = ~%.0f cal | P:%.0fg | C:%.0fg | F:%.0fg\n",
                    165 * 1.5 + 130 + 34, 31 * 1.5 + 2.7 + 2.8, 0 + 28 + 7, 3.6 * 1.5 + 0.3 + 0.4);
        }

        if (remaining >= 200 && remaining < 400) {
            System.out.println("  Suggested Light Meal (~200-300 cal):");
            System.out.println("  - Egg (2 whole) + Toast (1 slice) + Spinach (50g)");
            System.out.printf("    = ~%.0f cal\n", 155 * 2 * 0.5 + 265 * 0.3 + 23 * 0.5);
        }

        if (remaining < 200) {
            System.out.println("  Suggested Snack (~100-200 cal):");
            System.out.println("  - Apple (150g) + Greek Yogurt (100g)");
            System.out.printf("    = ~%.0f cal\n", 52 * 1.5 + 59.0);
        }

        // Macro-specific suggestions
        System.out.println();
        if (currentProtein < 50) {
            System.out.println("  TIP: Your protein intake is low today. Consider:");
            System.out.println("  - Greek Yogurt, Chicken Breast, Eggs, or Lentils");
        }
        if (currentCarbs < 100) {
            System.out.println("  TIP: You need more carbs for energy. Consider:");
            System.out.println("  - Oatmeal, Rice, Sweet Potato, or Banana");
        }

        System.out.println("=".repeat(60));
    }

    /**
     * Displays detailed meal history for the current day.
     */
    public void showTodayMeals() {
        System.out.println(getCurrentDayLog().toString());
    }

    // Getters
    public int getCurrentDay() { return currentDay; }
    public List<DailyLog> getDailyLogs() { return dailyLogs; }
    public User getUser() { return user; }
    public FoodDatabase getFoodDatabase() { return foodDatabase; }
}
