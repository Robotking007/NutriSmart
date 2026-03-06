package com.nutrismart.controllers;

import com.nutrismart.models.*;

public class GoalTracker {
    
    public void calculateAndSetGoal(User user) {
        float bmr;
        if (user.getGender().equalsIgnoreCase("Male")) {
            bmr = 66 + (13.7f * user.getWeight()) + 
                  (5 * user.getHeight()) - (6.8f * user.getAge());
        } else {
            bmr = 655 + (9.6f * user.getWeight()) + 
                  (1.8f * user.getHeight()) - (4.7f * user.getAge());
        }
        
        float maintenance = bmr * 1.55f;
        user.setDailyCalorieGoal(maintenance);
        
        System.out.printf("%n=== Daily Calorie Goal Updated ===%n");
        System.out.printf("Your recommended daily calorie intake: %.0f cal%n", maintenance);
    }
    
    public void checkGoal(User user) {
        float totalCalories = user.totalCaloriesToday();
        float goal = user.getDailyCalorieGoal();
        float remaining = goal - totalCalories;
        
        System.out.printf("%n=== Daily Goal Tracking ===%n");
        System.out.printf("Goal: %.0f cal%n", goal);
        System.out.printf("Consumed: %.0f cal%n", totalCalories);
        System.out.printf("Remaining: %.0f cal%n", remaining);
    }
    
    public void suggestNextMeal(User user) {
        float remaining = user.getDailyCalorieGoal() - user.totalCaloriesToday();
        
        System.out.printf("%n=== Meal Suggestion ===%n");
        if (remaining <= 0) {
            System.out.println("You've met your goal. Consider a light snack if hungry.");
        } else if (remaining < 300) {
            System.out.println("Light snack: Apple (95 cal) or Yogurt (150 cal)");
        } else if (remaining < 600) {
            System.out.println("Regular meal: Grilled chicken with veggies (400 cal)");
        } else {
            System.out.println("Substantial meal: Chicken breast with rice (550 cal)");
        }
    }
}
