package com.nutrismart.models;

public abstract class FoodItem {
    protected String name;
    protected float protein;
    protected float carbs;
    protected float fats;
    protected float calories;
    
    public FoodItem(String name, float protein, float carbs, float fats) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calculateCalories();
    }
    
    public float calculateCalories() {
        return (protein * 4) + (carbs * 4) + (fats * 9);
    }
    
    public abstract void display();
    public abstract String getCategory();
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public float getProtein() { return protein; }
    public void setProtein(float protein) { 
        this.protein = protein; 
        this.calories = calculateCalories();
    }
    
    public float getCarbs() { return carbs; }
    public void setCarbs(float carbs) { 
        this.carbs = carbs; 
        this.calories = calculateCalories();
    }
    
    public float getFats() { return fats; }
    public void setFats(float fats) { 
        this.fats = fats; 
        this.calories = calculateCalories();
    }
    
    public float getCalories() { return calories; }
    
    public NutritionTuple getNutrition() {
        return new NutritionTuple(protein, carbs, fats);
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s | Calories: %.1f | P:%.1fg C:%.1fg F:%.1fg", 
                            getCategory(), name, calories, protein, carbs, fats);
    }
    
    public String toFileString() {
        return String.format("%s|%s|%.2f|%.2f|%.2f", 
                            getCategory(), name, protein, carbs, fats);
    }
}
