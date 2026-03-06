package com.nutrismart.controllers;

import com.nutrismart.models.*;
import java.io.*;
import java.util.*;

public class DatabaseManager {
    private static DatabaseManager instance;
    private List<User> users;
    private List<FoodItem> foodDatabase;
    private Map<String, NutritionTuple> nutritionData;
    
    private static final String USERS_FILE = "resources/users.csv";
    
    private DatabaseManager() {
        users = new ArrayList<>();
        foodDatabase = new ArrayList<>();
        nutritionData = new HashMap<>();
        loadNutritionData();
    }
    
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    private void loadNutritionData() {
        // Fruits
        addNutritionData("apple", 0.3f, 14.0f, 0.2f);
        addNutritionData("banana", 1.1f, 22.8f, 0.3f);
        addNutritionData("orange", 0.9f, 11.8f, 0.1f);
        addNutritionData("mango", 0.8f, 15.0f, 0.4f);
        addNutritionData("pineapple", 0.5f, 13.1f, 0.1f);
        
        // Proteins
        addNutritionData("chicken breast", 31.0f, 0.0f, 3.6f);
        addNutritionData("eggs", 12.0f, 1.1f, 10.0f);
        addNutritionData("paneer", 18.0f, 1.2f, 20.0f);
        
        // Vegetables
        addNutritionData("broccoli", 2.8f, 6.0f, 0.4f);
        addNutritionData("spinach", 2.9f, 3.6f, 0.4f);
        addNutritionData("carrot", 0.9f, 9.6f, 0.2f);
        addNutritionData("potato", 2.0f, 17.5f, 0.1f);
    }
    
    private void addNutritionData(String foodName, float protein, float carbs, float fats) {
        nutritionData.put(foodName.toLowerCase(), new NutritionTuple(protein, carbs, fats));
    }
    
    public NutritionTuple getNutritionData(String foodName) {
        NutritionTuple result = nutritionData.get(foodName.toLowerCase());
        return result != null ? result : new NutritionTuple(0, 0, 0);
    }
    
    public void addFood(FoodItem food) {
        foodDatabase.add(food);
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public String[] getAllUsernames() {
        String[] usernames = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            usernames[i] = users.get(i).getUsername();
        }
        return usernames;
    }
    
    public void loadUsersFromFile() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String username = parts[0];
                    String password = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    float weight = Float.parseFloat(parts[3]);
                    float height = Float.parseFloat(parts[4]);
                    String gender = parts[5];
                    float goal = Float.parseFloat(parts[6]);
                    
                    User user = new User(username, password, age, weight, height, gender, goal);
                    addUser(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
    
    public void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.printf("%s,%s,%d,%.1f,%.1f,%s,%.1f%n",
                    user.getUsername(), user.getPassword(),
                    user.getAge(), user.getWeight(), user.getHeight(),
                    user.getGender(), user.getDailyCalorieGoal());
                user.saveUserData();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
