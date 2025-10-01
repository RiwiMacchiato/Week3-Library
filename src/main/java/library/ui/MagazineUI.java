package library.ui;

import javax.swing.JOptionPane;

import library.controller.MagazineController;
import library.controller.MagazineController.ControllerResponse;

/**
 * Magazine-specific UI class for handling magazine-related operations.
 */
public class MagazineUI {
    
    private final MagazineController magazineController;

    public MagazineUI() {
        this.magazineController = new MagazineController();
    }

    /**
     * Displays a detailed magazine management interface
     */
    public void showMagazineManagement() {
        boolean running = true;
        
        while (running) {
            String menu = """
                    === MAGAZINE MANAGEMENT ===
                    
                    Select an option:
                    1. Add Magazine
                    2. Find Magazine by ID
                    3. List All Magazines
                    4. Update Magazine (Coming Soon)
                    5. Delete Magazine (Coming Soon)
                    6. Back to Main Menu
                    
                    Enter your choice (1-6):""";

            String choice = JOptionPane.showInputDialog(null, menu, "Magazine Management", JOptionPane.QUESTION_MESSAGE);

            if (choice == null) {
                running = false;
                continue;
            }

            try {
                int option = Integer.parseInt(choice.trim());

                switch (option) {
                    case 1:
                        addMagazine();
                        break;
                    case 2:
                        findMagazine();
                        break;
                    case 3:
                        listAllMagazines();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Update Magazine - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Delete Magazine - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
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

    public void addMagazine() {
        String title = JOptionPane.showInputDialog(null, "Enter magazine title:", "Add New Magazine", JOptionPane.QUESTION_MESSAGE);
        if (title == null || title.trim().isEmpty()) return;

        String publisherInput = JOptionPane.showInputDialog(null, "Enter publisher name:", "Add New Magazine", JOptionPane.QUESTION_MESSAGE);
        if (publisherInput == null || publisherInput.trim().isEmpty()) return;

        String issueInput = JOptionPane.showInputDialog(null, "Enter issue number:", "Add New Magazine", JOptionPane.QUESTION_MESSAGE);
        if (issueInput == null || issueInput.trim().isEmpty()) return;

        String yearInput = JOptionPane.showInputDialog(null, "Enter published year:", "Add New Magazine", JOptionPane.QUESTION_MESSAGE);
        if (yearInput == null || yearInput.trim().isEmpty()) return;

        String userIdInput = JOptionPane.showInputDialog(null, "Enter owner user ID:", "Add New Magazine", JOptionPane.QUESTION_MESSAGE);
        if (userIdInput == null || userIdInput.trim().isEmpty()) return;

        try {
            int issueNumber = Integer.parseInt(issueInput.trim());
            int publishedYear = Integer.parseInt(yearInput.trim());
            int userId = Integer.parseInt(userIdInput.trim());

            ControllerResponse response = magazineController.addMagazine(title, publishedYear, userId, 
                                                                        issueNumber, publisherInput);
            
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter valid numbers for issue, year, and user ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findMagazine() {
        if (!magazineController.hasMagazines()) {
            JOptionPane.showMessageDialog(null, "No magazines found in the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String idInput = JOptionPane.showInputDialog(null, "Enter magazine ID:", "Find Magazine", JOptionPane.QUESTION_MESSAGE);
        if (idInput == null || idInput.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.trim());
            ControllerResponse response = magazineController.findMagazineById(id);
            
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Magazine Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllMagazines() {
        ControllerResponse response = magazineController.getAllMagazines();
        
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "All Magazines", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
