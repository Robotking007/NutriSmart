package com.nutrismart.models;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String mealName;
    private List<FoodItem> foods;
    private static final int MAX_FOODS = 20;
    
    public Meal() {
        this.mealName = "Unnamed Meal";
        this.foods = new ArrayList<>();
    }
    
    public Meal(String name) {
        this.mealName = name;
        this.foods = new ArrayList<>();
    }
    
    public Meal(Meal other) {
        this.mealName = other.mealName + " (Copy)";
        this.foods = new ArrayList<>(other.foods);
    }
    
    public void addFood(FoodItem food) {
        if (food != null && foods.size() < MAX_FOODS) {
            foods.add(food);
        }
    }
    
    public float totalCalories() {
        float total = 0;
        for (FoodItem food : foods) {
            total += food.getCalories();
        }
        return total;
    }
    
    public void display() {
        System.out.println("\nMeal: " + mealName);
        if (foods.isEmpty()) {
            System.out.println("  No foods in this meal");
        } else {
            for (FoodItem food : foods) {
                System.out.println("  - " + food);
            }
        }
        System.out.println("Total Calories: " + totalCalories());
    }
    
    public NutritionTuple totalNutrition() {
        float protein = 0, carbs = 0, fats = 0;
        for (FoodItem food : foods) {
            NutritionTuple nut = food.getNutrition();
            protein += nut.getProtein();
            carbs += nut.getCarbs();
            fats += nut.getFats();
        }
        return new NutritionTuple(protein, carbs, fats);
    }
    
    public String getMealName() { return mealName; }
    public void setMealName(String mealName) { this.mealName = mealName; }
    
    public List<FoodItem> getFoods() { return foods; }
    public int getFoodCount() { return foods.size(); }
    
    public String toFileString(int day) {
        StringBuilder sb = new StringBuilder();
        sb.append(day).append("|").append(mealName).append("|");
        for (FoodItem food : foods) {
            sb.append(food.toFileString()).append(";");
        }
        return sb.toString();
    }
}
