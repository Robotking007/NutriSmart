package com.nutrismart.models;

public class NutritionTuple {
    private float protein;
    private float carbs;
    private float fats;
    
    public NutritionTuple(float protein, float carbs, float fats) {
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }
    
    public float getProtein() { return protein; }
    public void setProtein(float protein) { this.protein = protein; }
    
    public float getCarbs() { return carbs; }
    public void setCarbs(float carbs) { this.carbs = carbs; }
    
    public float getFats() { return fats; }
    public void setFats(float fats) { this.fats = fats; }
    
    public float getCalories() {
        return (protein * 4) + (carbs * 4) + (fats * 9);
    }
    
    @Override
    public String toString() {
        return String.format("P:%.1fg C:%.1fg F:%.1fg (%.0f cal)", 
                            protein, carbs, fats, getCalories());
    }
}
