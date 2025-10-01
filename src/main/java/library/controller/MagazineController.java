package library.controller;

import java.util.List;

import library.dao.MagazineDAO;
import library.dao.MagazineDAOImpl;
import library.model.Magazine;

/**
 * Controller class responsible for handling magazine-related business logic.
 * Acts as an intermediary between the UI layer and the DAO layer.
 */
public class MagazineController {
    
    private final MagazineDAO magazineDAO;

    public MagazineController() {
        this.magazineDAO = new MagazineDAOImpl();
    }

    /**
     * Adds a new magazine to the system
     * @param title Magazine's title
     * @param publishedYear Year of publication
     * @param userId Owner user ID
     * @param issueNumber Magazine's issue number
     * @param publisher Magazine's publisher
     * @return ControllerResponse indicating success or failure
     */
    public ControllerResponse addMagazine(String title, int publishedYear, int userId, 
                                         int issueNumber, String publisher) {
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

            if (issueNumber <= 0) {
                return new ControllerResponse(false, "Issue number must be a positive number!");
            }

            if (publisher == null || publisher.trim().isEmpty()) {
                return new ControllerResponse(false, "Publisher cannot be empty!");
            }

            // Create and save magazine
            Magazine magazine = new Magazine(0, title.trim(), publishedYear, userId, 0, 
                                           issueNumber, publisher.trim());
            magazineDAO.save(magazine);
            
            return new ControllerResponse(true, "Magazine added successfully!");
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error adding magazine: " + e.getMessage());
        }
    }

    /**
     * Finds a magazine by ID
     * @param id Magazine ID to search for
     * @return ControllerResponse with magazine data or error message
     */
    public ControllerResponse findMagazineById(int id) {
        try {
            if (id <= 0) {
                return new ControllerResponse(false, "Invalid ID. ID must be a positive number.");
            }

            Magazine magazine = magazineDAO.findById(id);
            
            if (magazine != null) {
                String magazineInfo = "Magazine found:\n" +
                                    "ID: " + magazine.getMagazine_id() + "\n" +
                                    "Title: " + magazine.getTitle() + "\n" +
                                    "Issue Number: " + magazine.getIssue_number() + "\n" +
                                    "Publisher: " + magazine.getPublisher() + "\n" +
                                    "Published Year: " + magazine.getPublishedYear() + "\n" +
                                    "Owner User ID: " + magazine.getUserId();
                return new ControllerResponse(true, magazineInfo);
            } else {
                return new ControllerResponse(false, "Magazine with ID '" + id + "' not found!");
            }
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error finding magazine: " + e.getMessage());
        }
    }

    /**
     * Retrieves all magazines from the system
     * @return ControllerResponse with all magazines data or error message
     */
    public ControllerResponse getAllMagazines() {
        try {
            List<Magazine> magazines = magazineDAO.findAll();
            
            if (magazines == null || magazines.isEmpty()) {
                return new ControllerResponse(false, "No magazines found");
            }

            StringBuilder magazinesList = new StringBuilder("All Magazines:\n\n");
            for (Magazine magazine : magazines) {
                magazinesList.append("ID: ").append(magazine.getMagazine_id())
                           .append(" | Title: ").append(magazine.getTitle())
                           .append(" | Issue #: ").append(magazine.getIssue_number())
                           .append(" | Publisher: ").append(magazine.getPublisher())
                           .append("\n");
            }

            return new ControllerResponse(true, magazinesList.toString());
            
        } catch (Exception e) {
            return new ControllerResponse(false, "Error retrieving magazines: " + e.getMessage());
        }
    }

    /**
     * Checks if there are any magazines in the system
     * @return true if magazines exist, false otherwise
     */
    public boolean hasMagazines() {
        try {
            List<Magazine> magazines = magazineDAO.findAll();
            return magazines != null && !magazines.isEmpty();
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
