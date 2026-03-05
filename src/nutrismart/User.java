package nutrismart;

/**
 * Represents a user of the NutriSmart system.
 * Handles BMR calculation and daily calorie goal based on activity level.
 * Demonstrates encapsulation and single responsibility principle.
 */
public class User {
    private String username;
    private String password;
    private int age;
    private double weight;       // in kg
    private double height;       // in cm
    private String gender;       // "Male" or "Female"
    private ActivityLevel activityLevel;
    private double bmr;
    private double dailyCalorieGoal;

    // Constructor for registration
    public User(String username, String password, int age, double weight, double height,
                String gender, ActivityLevel activityLevel) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.activityLevel = activityLevel;
        calculateBMR();
        calculateDailyCalorieGoal();
    }

    /**
     * Calculates Basal Metabolic Rate using the Mifflin-St Jeor Equation.
     * Men:   BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age - 161 + 166
     * Women: BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age - 161
     */
    public void calculateBMR() {
        if (gender.equalsIgnoreCase("Male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
    }

    /**
     * Calculates the daily calorie goal based on BMR and activity level.
     */
    public void calculateDailyCalorieGoal() {
        dailyCalorieGoal = bmr * activityLevel.getMultiplier();
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getGender() { return gender; }
    public ActivityLevel getActivityLevel() { return activityLevel; }
    public double getBmr() { return bmr; }
    public double getDailyCalorieGoal() { return dailyCalorieGoal; }

    // Setters
    public void setWeight(double weight) {
        this.weight = weight;
        calculateBMR();
        calculateDailyCalorieGoal();
    }

    public void setHeight(double height) {
        this.height = height;
        calculateBMR();
        calculateDailyCalorieGoal();
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
        calculateDailyCalorieGoal();
    }

    /**
     * Converts user data to a file-saveable string.
     */
    public String toFileString() {
        return username + "," + password + "," + age + "," + weight + "," + height + ","
                + gender + "," + activityLevel.name();
    }

    /**
     * Creates a User from a file-formatted string.
     */
    public static User fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            return new User(
                parts[0].trim(),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                Double.parseDouble(parts[3].trim()),
                Double.parseDouble(parts[4].trim()),
                parts[5].trim(),
                ActivityLevel.valueOf(parts[6].trim())
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format(
            "User: %s | Age: %d | Weight: %.1fkg | Height: %.1fcm | Gender: %s\n" +
            "BMR: %.1f cal | Daily Goal: %.1f cal | Activity: %s",
            username, age, weight, height, gender, bmr, dailyCalorieGoal, activityLevel.getDescription()
        );
    }
}
