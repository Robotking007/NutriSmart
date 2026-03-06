package com.nutrismart.utils;

import com.nutrismart.models.User;

public class TIASummary {
    
    public static void displaySummaryTable(User.WeeklyProgressData[] data) {
        System.out.println("\n+-------+----------+-------+");
        System.out.println("| Day   | Calories | Meals |");
        System.out.println("+-------+----------+-------+");
        
        for (User.WeeklyProgressData d : data) {
            System.out.printf("| %-5s | %8.1f | %5d |%n", 
                            d.date, d.calories, d.mealCount);
        }
        
        System.out.println("+-------+----------+-------+");
    }
}
