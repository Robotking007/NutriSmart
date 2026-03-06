package com.nutrismart.models;

import java.util.*;
import java.io.*;

public class User {
    private String username;
    private String password;
    private int age;
    private float weight;
    private float height;
    private String gender;
    private float dailyCalorieGoal;
    private int currentDay;
    private Map<Integer, List<Meal>> mealsByDay;
    
    public User(String username, String password, int age, 
                float weight, float height, String gender, float goal) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.dailyCalorieGoal = goal;
        this.currentDay = 1;
        this.mealsByDay = new HashMap<>();
        mealsByDay.put(currentDay, new ArrayList<>());
    }
    
    public User(String username, String password, int age, 
                float weight, float height, String gender) {
        this(username, password, age, weight, height, gender, 2000);
    }
    
    public void addMeal(Meal meal) {
        List<Meal> todayMeals = mealsByDay.computeIfAbsent(currentDay, k -> new ArrayList<>());
        todayMeals.add(meal);
    }
    
    public float totalCaloriesToday() {
        float total = 0;
        List<Meal> todayMeals = mealsByDay.get(currentDay);
        if (todayMeals != null) {
            for (Meal meal : todayMeals) {
                total += meal.totalCalories();
            }
        }
        return total;
    }
    
    public Meal findMealByName(String name) {
        for (List<Meal> meals : mealsByDay.values()) {
            for (Meal meal : meals) {
                if (meal.getMealName().equalsIgnoreCase(name)) {
                    return meal;
                }
            }
        }
        return null;
    }
    
    public void displayMeals() {
        System.out.println("\n===== All Meals for Day " + currentDay + " =====");
        List<Meal> todayMeals = mealsByDay.get(currentDay);
        if (todayMeals == null || todayMeals.isEmpty()) {
            System.out.println("No meals recorded for today.");
        } else {
            for (Meal meal : todayMeals) {
                meal.display();
                System.out.println("---");
            }
        }
    }
    
    public WeeklyProgressData[] getWeeklyProgress() {
        List<WeeklyProgressData> weeklyData = new ArrayList<>();
        int startDay = Math.max(1, currentDay - 6);
        
        for (int day = startDay; day <= currentDay; day++) {
            List<Meal> dayMeals = mealsByDay.get(day);
            float dayCalories = 0;
            int mealCount = 0;
            
            if (dayMeals != null) {
                for (Meal meal : dayMeals) {
                    dayCalories += meal.totalCalories();
                }
                mealCount = dayMeals.size();
            }
            
            weeklyData.add(new WeeklyProgressData("Day-" + day, dayCalories, mealCount));
        }
        
        return weeklyData.toArray(new WeeklyProgressData[0]);
    }
    
    public void nextDay() {
        currentDay++;
        mealsByDay.put(currentDay, new ArrayList<>());
    }
    
    public void saveUserData() {
        String filename = "resources/" + username + "_meals.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<Integer, List<Meal>> entry : mealsByDay.entrySet()) {
                int day = entry.getKey();
                for (Meal meal : entry.getValue()) {
                    writer.println(meal.toFileString(day));
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving meals: " + e.getMessage());
        }
    }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public float getWeight() { return weight; }
    public float getHeight() { return height; }
    public String getGender() { return gender; }
    public int getCurrentDay() { return currentDay; }
    public float getDailyCalorieGoal() { return dailyCalorieGoal; }
    public void setDailyCalorieGoal(float goal) { this.dailyCalorieGoal = goal; }
    
    public static class WeeklyProgressData {
        public String date;
        public float calories;
        public int mealCount;
        
        public WeeklyProgressData(String date, float calories, int mealCount) {
            this.date = date;
            this.calories = calories;
            this.mealCount = mealCount;
        }
    }
}
