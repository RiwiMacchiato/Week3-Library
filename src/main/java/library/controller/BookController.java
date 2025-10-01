package library.controller;

import java.util.List;

import library.dao.BookDAO;
import library.dao.BookDAOImpl;
import library.model.Book;

/**
 * Controller class responsible for handling book-related business logic.
 * Acts as an intermediary between the UI layer and the DAO layer.
 */
public class BookController {
    
    private final BookDAO bookDAO;

    public BookController() {
        this.bookDAO = new BookDAOImpl();
    }

    /**
     * Adds a new book to the system
     * @param title Book's title
     * @param publishedYear Year of publication
     * @param userId Owner user ID
     * @param author Book's author
     * @param isbn Book's ISBN
     * @param pages Number of pages
     * @return ControllerResponse indicating success or failure
     */
    public ControllerResponse addBook(String title, int publishedYear, int userId, 
                                      String author, String isbn, int pages) {
        try {
            // Validate input
            if (title == null || title.trim().isEmpty()) {
                return new ControllerResponse(false, "Title cannot be empty!");
            }
            
            if (publishedYear <= 0) {
                return new ControllerResponse(false, "Published year must be a positive number!");
            }

            if (userId <= 0) {
                return new ControllerResponse(false, "User ID must be a positive number!");
            }

            if (author == null || author.trim().isEmpty()) {
                return new ControllerResponse(false, "Author cannot be empty!");
            }

            if (isbn == null || isbn.trim().isEmpty()) {
                return new ControllerResponse(false, "ISBN cannot be empty!");
            }

            if (pages <= 0) {
                return new ControllerResponse(false, "Pages must be a positive number!");
            }

            // Create and save book
            Book book = new Book(0, title.trim(), publishedYear, userId, 0, 
                               author.trim(), isbn.trim(), pages);
            bookDAO.save(book);
            
            return new ControllerResponse(true, "Book added successfully!");
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error adding book: " + e.getMessage());
        }
    }

    /**
     * Finds a book by ID
     * @param id Book ID to search for
     * @return ControllerResponse with book data or error message
     */
    public ControllerResponse findBookById(int id) {
        try {
            if (id <= 0) {
                return new ControllerResponse(false, "Invalid ID. ID must be a positive number.");
            }

            Book book = bookDAO.findById(id);
            
            if (book != null) {
                String bookInfo = "Book found:\n" +
                                "ID: " + book.getBook_id() + "\n" +
                                "Title: " + book.getTitle() + "\n" +
                                "Author: " + book.getAuthor() + "\n" +
                                "ISBN: " + book.getIsbn() + "\n" +
                                "Pages: " + book.getPages() + "\n" +
                                "Published Year: " + book.getPublishedYear() + "\n" +
                                "Owner User ID: " + book.getUserId();
                return new ControllerResponse(true, bookInfo);
            } else {
                return new ControllerResponse(false, "Book with ID '" + id + "' not found!");
            }
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error finding book: " + e.getMessage());
        }
    }

    /**
     * Retrieves all books from the system
     * @return ControllerResponse with all books data or error message
     */
    public ControllerResponse getAllBooks() {
        try {
            List<Book> books = bookDAO.findAll();
            
            if (books == null || books.isEmpty()) {
                return new ControllerResponse(false, "No books found");
            }

            StringBuilder booksList = new StringBuilder("All Books:\n\n");
            for (Book book : books) {
                booksList.append("ID: ").append(book.getBook_id())
                        .append(" | Title: ").append(book.getTitle())
                        .append(" | Author: ").append(book.getAuthor())
                        .append(" | ISBN: ").append(book.getIsbn())
                        .append("\n");
            }

            return new ControllerResponse(true, booksList.toString());
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error retrieving books: " + e.getMessage());
        }
    }

    /**
     * Checks if there are any books in the system
     * @return true if books exist, false otherwise
     */
    public boolean hasBooks() {
        try {
            List<Book> books = bookDAO.findAll();
            return books != null && !books.isEmpty();
        } catch (Exception e) {
            return false;
        }
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
