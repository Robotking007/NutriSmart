package com.nutrismart.models;

public class Fruit extends FoodItem {
    
    public Fruit(String name, float protein, float carbs, float fats) {
        super(name, protein, carbs, fats);
    }
    
    @Override
    public void display() {
        System.out.println("Fruit: " + name + " | Calories: " + calories +
                          " | P: " + protein + "g C: " + carbs + "g F: " + fats + "g");
    }
    
    @Override
    public String getCategory() {
        return "Fruit";
    }
}
