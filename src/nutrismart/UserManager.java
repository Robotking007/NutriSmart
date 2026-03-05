package nutrismart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages user registration, login, and persistence.
 * Demonstrates file-based authentication and user data management.
 */
public class UserManager {
    private List<User> users;
    private User currentUser;
    private static final String USER_FILE = "data/users.txt";

    public UserManager() {
        users = new ArrayList<>();
        currentUser = null;
        loadUsers();
    }

    /**
     * Loads users from the file.
     */
    private void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    User user = User.fromFileString(line);
                    if (user != null) {
                        users.add(user);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }

    /**
     * Saves all users to the file.
     */
    private void saveUsers() {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
                for (User user : users) {
                    writer.println(user.toFileString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    /**
     * Registers a new user. Returns true if successful.
     */
    public boolean register(String username, String password, int age, double weight,
                            double height, String gender, ActivityLevel activityLevel) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already exists! Please choose a different username.");
                return false;
            }
        }

        User newUser = new User(username, password, age, weight, height, gender, activityLevel);
        users.add(newUser);
        currentUser = newUser;
        saveUsers();

        System.out.println("\n Registration successful!");
        System.out.println(newUser);
        return true;
    }

    /**
     * Authenticates a user. Returns true if login is successful.
     */
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("\n Login successful! Welcome back, " + username + "!");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("Goodbye, " + currentUser.getUsername() + "!");
            currentUser = null;
        }
    }

    /**
     * Checks if a user is currently logged in.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Gets the currently logged-in user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the count of registered users.
     */
    public int getUserCount() {
        return users.size();
    }
}
