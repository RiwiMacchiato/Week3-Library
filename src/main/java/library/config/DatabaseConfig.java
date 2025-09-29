package library.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Database configuration class containing connection parameters
 * for MySQL Type 4 thin driver JDBC connection
 */
public class DatabaseConfig {
    
    // Loaded configuration properties
    private static Properties props = loadProperties();
    
    // Database connection parameters (loaded from properties)
    public static final String DB_URL = props.getProperty("db.url");
    public static final String DB_USERNAME = props.getProperty("db.username");
    public static final String DB_PASSWORD = props.getProperty("db.password");
    public static final String DB_DRIVER = props.getProperty("db.driver");
    
    // Connection pool settings
    public static final int MAX_CONNECTIONS = Integer.parseInt(props.getProperty("db.max.connections", "5"));
    public static final int CONNECTION_TIMEOUT = Integer.parseInt(props.getProperty("db.connection.timeout", "900"));
    
    /**
     * Load database properties from resources
     * @return Properties object or empty properties if file not found
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (input != null) {
                properties.load(input);
                System.out.println("Database properties loaded successfully");
            } else {
                throw new RuntimeException("database.properties file not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties: " + e.getMessage(), e);
        }
        return properties;
    }
}
