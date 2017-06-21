package main.java.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryDatabase {
    private Connection c;
    private Statement s;

    public void init(){
        System.out.println("Database setup initiated!");
        try {
            openConnection();
            s.setQueryTimeout(30);
            // s.executeUpdate("DROP TABLE LIB");
            setupDb();
        }catch (SQLException e){
            e.getStackTrace();
            System.err.println(e);
        }
        finally {
            if (c != null){
                close(c);
            }
        }
    }

    /**
     * Helper function that opens the connection to the Database.
     * @return Object of type Connection.
     */
    public Connection openConnection(){
        try {
            c = DriverManager.getConnection("jdbc:sqlite:LIB.db");
            s = c.createStatement();

            System.out.println("Database connection open");
            return c;
        }catch (SQLException e) {
            System.err.println("Opening connection failed: " + e.getMessage());
        }
        return null;
    }

    /**
     * Helper function that closes the connection to the database.
     * @param c, Object of type Connection to be closed.
     */
    public void close(Connection c){
        try {
            c.close();
        }catch (SQLException e){
            e.getStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Database connection close!");
    }

    /**
     * This method creates the database table LIB if it doesn't already exist.
     */
    private void setupDb(){
        try {
            openConnection();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS LIB (NAME text UNIQUE , TYPE text, LANGUAGE text, INTRUSIVE text, OPENSOURCE text)");
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally {
            if (c != null){
                close(c);
            }
        }
    }
}
