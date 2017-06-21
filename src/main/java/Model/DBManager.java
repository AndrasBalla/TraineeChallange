package main.java.Model;

import main.java.View.Table;

import java.sql.*;

public class DBManager {
    private LibraryDatabase db;
    private Connection c;
    private Statement s;
    private ResultSet rs;
    private PreparedStatement ps;

    public DBManager(){
        db = new LibraryDatabase();
    }

    /**
     * Loads in all the libraries found in the database and adds them to the TableView that is the JavaFx Object that shows the list the libraries.
     * @param table
     */
    public void loadLibraries(Table table){

        String sql = "SELECT * FROM LIB";
        c = db.openConnection();
        try {
            s = c.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String language = rs.getString("language");
                String intrusive = rs.getString("intrusive");
                String openSource = rs.getString("openSource");

                table.addData(new Library(name,type,language, intrusive, openSource));
            }
        }catch (SQLException e) {
            // TODO Present this message to the user on the GUI.
            System.err.println("Loading from Database failed: " + e.getMessage());
        }
        db.close(c);

    }

    /**
     * Adds a library provided by the user to the database.
     * @param name, String name of the Library
     * @param type, String type of the Library
     * @param language, String Programming language of the Library
     * @param intrusive, Whether the library is intrusive or not
     * @param openSource, Whether the library is Open Source or not
     */
    public void add(String name, String type, String language, String intrusive, String openSource){
        c = db.openConnection();
        try {
            ps = c.prepareStatement("INSERT INTO LIB VALUES (?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, language);
            ps.setString(4, intrusive);
            ps.setString(5, openSource);
            ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Present this message to the user on the GUI.
            System.err.println("Adding to Database failed: " + e.getMessage());
        }
        db.close(c);
    }

    /**
     * Removes a library from the database.
     * @param name, The unique name of the library
     */
    public void delete(String name){
        c = db.openConnection();
        try {
            ps = c.prepareStatement("DELETE FROM LIB WHERE NAME = ?");
            ps.setString(1, name);
            ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Present this message to the user on the GUI.
            System.err.println("Deleting from Database failed: " + e.getMessage());
        }
        db.close(c);
    }

    /**
     * Updates a library found in the database
     * @param input, Library Object that is provided by the user as input.
     * @param name, String the name of the selected library to be updated.
     */
    public void update(Library input, String name){
        c = db.openConnection();
        try {
            ps = c.prepareStatement("UPDATE LIB SET NAME = ?, TYPE = ?, LANGUAGE = ?, INTRUSIVE = ?, OPENSOURCE = ? WHERE NAME = ?");
            ps.setString(1, input.getName());
            ps.setString(2, input.getType());
            ps.setString(3, input.getLanguage());
            ps.setString(4, input.getIntrusive());
            ps.setString(5, input.getOpenSource());
            ps.setString(6, name);
            ps.executeUpdate();
        }catch (SQLException e) {
            // TODO Present this message to the user on the GUI.
            System.err.println("Deleting from Database failed: " + e.getMessage());
        }
        db.close(c);
    }
}
