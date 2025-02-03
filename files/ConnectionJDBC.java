package newPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionJDBC {
    Connection connection;
    Statement statement;

    // Constructor to initialize the JDBC connection
    public ConnectionJDBC() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection (replace with your credentials)
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/projects",  // Database URL
                "root",                         // Replace with your MySQL username
                "Java"                          // Replace with your MySQL password
            );
            
            // Create a statement object
            statement = connection.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}