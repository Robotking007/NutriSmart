package com.nutrismart.models;

import com.nutrismart.controllers.DatabaseManager;

public class Admin extends User {
    
    public Admin(String username, String password, float goal) {
        super(username, password, 0, 0, 0, "Admin", goal);
    }
    
    public void addFoodToDatabase(FoodItem food) {
        DatabaseManager.getInstance().addFood(food);
        System.out.println("Food added to database: " + food.getName());
    }
}
