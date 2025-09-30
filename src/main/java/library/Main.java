package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.config.ConnectionManager;
import library.ui.MenuUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===");
        
        ConnectionManager manager = ConnectionManager.getInstance();
        
        // Test basic connection
        if (manager.testConnection()) {
            System.out.println("Database connection successful");
            
            // Simple query demonstration
            demonstrateQuery(manager);
            
            // Start the main UI
            System.out.println("Starting Library Management System UI...");
            MenuUI menuUI = new MenuUI();
            menuUI.start();
            
        } else {
            System.out.println("Database connection failed");
            System.out.println("Please check your database configuration and try again.");
        }
    }
    
    private static void demonstrateQuery(ConnectionManager manager) {
        try (Connection conn = manager.getConnection()) {
            // Simple query to show JDBC in action
            String query = "SELECT DATABASE() as current_db, VERSION() as mysql_version";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Connected to: " + resultSet.getString("current_db"));
                System.out.println("MySQL Version: " + resultSet.getString("mysql_version"));
            }
            
        } catch (SQLException e) {
            System.err.println("Query error: " + e.getMessage());
        }   
    }
}
