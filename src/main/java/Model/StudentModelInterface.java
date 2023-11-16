package Model;

import java.sql.Connection;

/**
 The StudentModelInterface provides an interface for the model in the MVC pattern for the Student Management System.
 */
public interface StudentModelInterface {
    /**
     * Connects to the MySQL database.
     * @throws RuntimeException if there is an error connecting to the database.
     */
    void connect();

    /**
     * Returns the current connection to the MySQL database.
     * @return the current connection to the MySQL database.
     */
    Connection getConnection();
}
