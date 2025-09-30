package library.ui;

import javax.swing.JOptionPane;

/**
 * Main menu UI class responsible for navigation and routing to appropriate UI classes.
 * This class handles only the presentation and navigation logic.
 */
public class MenuUI {
    
    private final UserUI userUI;

    public MenuUI() {
        this.userUI = new UserUI();
    }

    /**1
     * Starts the main menu application
     */
    public void start() {
        JOptionPane.showMessageDialog(null, 
            "Welcome to Library Management System!", 
            "Library Management System", 
            JOptionPane.INFORMATION_MESSAGE);

        boolean running = true;
        while (running) {
            running = showMainMenu();
        }
    }

    /**
     * Displays the main menu and handles user choice
     * @return false if user wants to exit, true otherwise
     */
    private boolean showMainMenu() {
        String menu = """
                === LIBRARY MANAGEMENT SYSTEM ===
                
                Select an option:
                1. User Management
                2. Book Management (Coming Soon)
                3. Magazine Management (Coming Soon)
                4. Exit
                
                Enter your choice (1-4):""";

        String choice = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);

        // If user cancels or closes dialog
        if (choice == null) {
            return false;
        }

        try {
            int option = Integer.parseInt(choice.trim());

            switch (option) {
                case 1:
                    showUserMenu();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Book Management - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Magazine Management - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Thank you for using Library Management System!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select 1-4.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    /**
     * Displays the user management submenu
     */
    private void showUserMenu() {
        boolean inUserMenu = true;
        
        while (inUserMenu) {
            String userMenu = """
                    === USER MANAGEMENT ===
                    
                    Select an option:
                    1. Add User
                    2. Find User by ID
                    3. List All Users
                    4. Advanced User Management
                    5. Back to Main Menu
                    
                    Enter your choice (1-5):""";

            String choice = JOptionPane.showInputDialog(null, userMenu, "User Management", JOptionPane.QUESTION_MESSAGE);

            // If user cancels or closes dialog
            if (choice == null) {
                inUserMenu = false;
                continue;
            }

            try {
                int option = Integer.parseInt(choice.trim());

                switch (option) {
                    case 1:
                        userUI.addUser();
                        break;
                    case 2:
                        userUI.findUser();
                        break;
                    case 3:
                        userUI.listAllUsers();
                        break;
                    case 4:
                        userUI.showUserManagement();
                        break;
                    case 5:
                        inUserMenu = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please select 1-5.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
