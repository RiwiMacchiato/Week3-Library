package library.ui;

import javax.swing.JOptionPane;

import library.controller.UserController;
import library.controller.UserController.ControllerResponse;

/**
 * User-specific UI class for handling user-related operations.
 * This class can be extended in the future for more complex user operations.
 * Currently, most functionality is handled by MenuUI for simplicity.
 */
public class UserUI {
    
    private final UserController userController;

    public UserUI() {
        this.userController = new UserController();
    }

    /**
     * Displays a detailed user management interface
     * This can be used for more complex user operations in the future
     */
    public void showUserManagement() {
        boolean running = true;
        
        while (running) {
            String menu = """
                    === ADVANCED USER MANAGEMENT ===
                    
                    Select an option:
                    1. Add User
                    2. Find User by ID
                    3. List All Users
                    4. Update User (Coming Soon)
                    5. Delete User (Coming Soon)
                    6. Back to Main Menu
                    
                    Enter your choice (1-6):""";

            String choice = JOptionPane.showInputDialog(null, menu, "User Management", JOptionPane.QUESTION_MESSAGE);

            if (choice == null) {
                running = false;
                continue;
            }

            try {
                int option = Integer.parseInt(choice.trim());

                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        findUser();
                        break;
                    case 3:
                        listAllUsers();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Update User - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Delete User - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please select 1-6.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void addUser() {
        String name = JOptionPane.showInputDialog(null, "Enter User name:", "Add New User", JOptionPane.QUESTION_MESSAGE);
        if (name == null) return;

        String email = JOptionPane.showInputDialog(null, "Enter user email:", "Add New User", JOptionPane.QUESTION_MESSAGE);
        if (email == null) return;

        ControllerResponse response = userController.addUser(name, email);
        
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findUser() {
        if (!userController.hasUsers()) {
            JOptionPane.showMessageDialog(null, "No users found in the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String idInput = JOptionPane.showInputDialog(null, "Enter user ID:", "Find User", JOptionPane.QUESTION_MESSAGE);
        if (idInput == null || idInput.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.trim());
            ControllerResponse response = userController.findUserById(id);
            
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "User Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllUsers() {
        ControllerResponse response = userController.getAllUsers();
        
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "All Users", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
