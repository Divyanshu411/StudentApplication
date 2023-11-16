package Model;

import java.sql.*;

/**
 Represents a student model that connects to a MySQL database and performs operations.
 */
public class studentModel implements StudentModelInterface{

    private Connection connection;

    /**
     Default constructor for the studentModel class.
     */
    public studentModel() {
    }
    /**
     Connects to the MySQL database.
     @throws RuntimeException if there is an error connecting to the database.
     */
    public void connect(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the mysql driver
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/record", "root", "root");
            System.out.println("Success");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    /**
     Returns the current connection to the MySQL database.
     @return the current connection to the MySQL database.
     */
    public Connection getConnection() {
        return connection;
    }
}