package com.nutrismart.controllers;

import com.nutrismart.models.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NutritionAnalyzer {
    
    public void generateUserReport(User user) {
        System.out.println("\n========== DAILY NUTRITION REPORT ==========");
        System.out.println("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("User: " + user.getUsername());
        System.out.println("Day: " + user.getCurrentDay());
        
        float totalCalories = user.totalCaloriesToday();
        float goal = user.getDailyCalorieGoal();
        
        System.out.printf("Total Calories: %.0f / %.0f (%.1f%%)%n", 
                         totalCalories, goal, (totalCalories / goal * 100));
    }
    
    public void generateWeeklyReport(User user) {
        System.out.println("\n========== WEEKLY PROGRESS REPORT ==========");
        
        User.WeeklyProgressData[] weeklyData = user.getWeeklyProgress();
        
        if (weeklyData.length == 0) {
            System.out.println("No meal data available yet.");
            return;
        }
        
        System.out.println("\nDaily Breakdown:");
        float weekTotal = 0;
        
        for (User.WeeklyProgressData data : weeklyData) {
            System.out.printf("%s: %.1f cal%n", data.date, data.calories);
            weekTotal += data.calories;
        }
        
        System.out.println("\n----- Summary -----");
        System.out.println("Total Days Tracked: " + weeklyData.length);
        System.out.printf("Total Calories: %.1f%n", weekTotal);
    }
}
