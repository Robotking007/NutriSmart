package com.nutrismart.models;

public class ProteinFood extends FoodItem {
    
    public ProteinFood(String name, float protein, float carbs, float fats) {
        super(name, protein, carbs, fats);
    }
    
    @Override
    public void display() {
        System.out.println("Protein: " + name + " | Calories: " + calories +
                          " | P: " + protein + "g C: " + carbs + "g F: " + fats + "g");
    }
    
    @Override
    public String getCategory() {
        return "Protein";
    }
}
