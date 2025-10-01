package library.ui;

import javax.swing.JOptionPane;

import library.controller.BookController;
import library.controller.BookController.ControllerResponse;

/**
 * Book-specific UI class for handling book-related operations.
 */
public class BookUI {
    
    private final BookController bookController;

    public BookUI() {
        this.bookController = new BookController();
    }

    /**
     * Displays a detailed book management interface
     */
    public void showBookManagement() {
        boolean running = true;
        
        while (running) {
            String menu = """
                    === BOOK MANAGEMENT ===
                    
                    Select an option:
                    1. Add Book
                    2. Find Book by ID
                    3. List All Books
                    4. Update Book 
                    5. Delete Book 
                    6. Back to Main Menu
                    
                    Enter your choice (1-6):""";

            String choice = JOptionPane.showInputDialog(null, menu, "Book Management", JOptionPane.QUESTION_MESSAGE);

            if (choice == null) {
                running = false;
                continue;
            }

            try {
                int option = Integer.parseInt(choice.trim());

                switch (option) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        findBook();
                        break;
                    case 3:
                        listAllBooks();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Update Book - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Delete Book - Coming Soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
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

    public void addBook() {
        String title = JOptionPane.showInputDialog(null, "Enter book title:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (title == null || title.trim().isEmpty()) return;

        String authorInput = JOptionPane.showInputDialog(null, "Enter book author:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (authorInput == null || authorInput.trim().isEmpty()) return;

        String isbnInput = JOptionPane.showInputDialog(null, "Enter book ISBN:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (isbnInput == null || isbnInput.trim().isEmpty()) return;

        String pagesInput = JOptionPane.showInputDialog(null, "Enter number of pages:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (pagesInput == null || pagesInput.trim().isEmpty()) return;

        String yearInput = JOptionPane.showInputDialog(null, "Enter published year:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (yearInput == null || yearInput.trim().isEmpty()) return;

        String userIdInput = JOptionPane.showInputDialog(null, "Enter owner user ID:", "Add New Book", JOptionPane.QUESTION_MESSAGE);
        if (userIdInput == null || userIdInput.trim().isEmpty()) return;

        try {
            int pages = Integer.parseInt(pagesInput.trim());
            int publishedYear = Integer.parseInt(yearInput.trim());
            int userId = Integer.parseInt(userIdInput.trim());

            ControllerResponse response = bookController.addBook(title, publishedYear, userId, 
                                                                authorInput, isbnInput, pages);
            
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter valid numbers for pages, year, and user ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findBook() {
        if (!bookController.hasBooks()) {
            JOptionPane.showMessageDialog(null, "No books found in the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String idInput = JOptionPane.showInputDialog(null, "Enter book ID:", "Find Book", JOptionPane.QUESTION_MESSAGE);
        if (idInput == null || idInput.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idInput.trim());
            ControllerResponse response = bookController.findBookById(id);
            
            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Book Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listAllBooks() {
        ControllerResponse response = bookController.getAllBooks();
        
        if (response.isSuccess()) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "All Books", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
