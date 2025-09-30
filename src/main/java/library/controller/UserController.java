package library.controller;

import java.util.List;

import library.dao.UserDAO;
import library.dao.UserDAOImpl;
import library.model.User;

/**
 * Controller class responsible for handling user-related business logic.
 * Acts as an intermediary between the UI layer and the DAO layer.
 */
public class UserController {
    
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Adds a new user to the system
     * @param name User's name
     * @param email User's email
     * @return ControllerResponse indicating success or failure
     */
    public ControllerResponse addUser(String name, String email) {
        try {
            // Validate input
            if (name == null || name.trim().isEmpty()) {
                return new ControllerResponse(false, "Name cannot be empty!");
            }
            
            if (email == null || email.trim().isEmpty()) {
                return new ControllerResponse(false, "Email cannot be empty!");
            }

            // Basic email validation
            if (!isValidEmail(email.trim())) {
                return new ControllerResponse(false, "Invalid email format!");
            }

            // Create and save user
            User user = new User(name.trim(), 0, email.trim());
            userDAO.save(user);
            
            return new ControllerResponse(true, "User added successfully!");
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error adding user: " + e.getMessage());
        }
    }

    /**
     * Finds a user by ID
     * @param id User ID to search for
     * @return ControllerResponse with user data or error message
     */
    public ControllerResponse findUserById(int id) {
        try {
            if (id <= 0) {
                return new ControllerResponse(false, "Invalid ID. ID must be a positive number.");
            }

            User user = userDAO.findById(id);
            
            if (user != null) {
                String userInfo = "User found:\n" +
                                "ID: " + user.getId() + "\n" +
                                "Name: " + user.getName() + "\n" +
                                "Email: " + user.getEmail();
                return new ControllerResponse(true, userInfo);
            } else {
                return new ControllerResponse(false, "User with ID '" + id + "' not found!");
            }
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error finding user: " + e.getMessage());
        }
    }

    /**
     * Retrieves all users from the system
     * @return ControllerResponse with all users data or error message
     */
    public ControllerResponse getAllUsers() {
        try {
            List<User> users = userDAO.findAll();
            
            if (users.isEmpty()) {
                return new ControllerResponse(false, "No users found");
            }

            StringBuilder usersList = new StringBuilder("All Users:\n\n");
            for (User user : users) {
                usersList.append("ID: ").append(user.getId())
                        .append(" | Name: ").append(user.getName())
                        .append(" | Email: ").append(user.getEmail())
                        .append("\n");
            }

            return new ControllerResponse(true, usersList.toString());
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error retrieving users: " + e.getMessage());
        }
    }

    /**
     * Checks if there are any users in the system
     * @return true if users exist, false otherwise
     */
    public boolean hasUsers() {
        try {
            List<User> users = userDAO.findAll();
            return !users.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Basic email validation
     * @param email Email to validate
     * @return true if email format is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }

    /**
     * Inner class to represent controller responses
     */
    public static class ControllerResponse {
        private final boolean success;
        private final String message;

        public ControllerResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}