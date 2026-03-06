package com.nutrismart.models;

public class FoodFactory {
    
    public static FoodItem createFood(String type, String name, 
                                      float protein, float carbs, float fats) {
        if (type == null || name == null) return null;
        
        String lowerType = type.toLowerCase().trim();
        
        if (lowerType.contains("fruit")) {
            return new Fruit(name, protein, carbs, fats);
        } else if (lowerType.contains("protein")) {
            return new ProteinFood(name, protein, carbs, fats);
        } else if (lowerType.contains("veg")) {
            return new Vegetable(name, protein, carbs, fats);
        }
        
        return null;
    }
    
    public static FoodItem createFood(String type, String name, NutritionTuple nutrition) {
        return createFood(type, name, 
                         nutrition.getProtein(), 
                         nutrition.getCarbs(), 
                         nutrition.getFats());
    }
}
