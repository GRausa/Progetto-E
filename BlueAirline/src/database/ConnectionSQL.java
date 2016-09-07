/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides the credentials in order to connect to the Database.
 * 
 * @author Giovanni
 */
public class ConnectionSQL {
    private java.sql.Connection connection;
    private Statement statement;
    private final String db;
    private final String nameDb;
    private final String username; 
    private final String password;
    /**
     * Construct a ConnectionSQL with the credentials.
     */
    public ConnectionSQL() {
        this.db = "sql7.freemysqlhosting.net";
        this.nameDb = "sql7130256";
        this.username = "sql7130256"; 
        this.password = "urHL9UR5SD";
    }
    /**
     * Reads a query.
     * 
     * @param query Query to be read.
     * @return Result.
     * @throws SQLException 
     */
    public ResultSet queryRead(String query) throws SQLException {
        return statement.executeQuery(query);
    }
   /**
    * Write a query.
    * 
    * @param query Query to be wrote.
    * @throws SQLException 
    */
    public void queryWrite(String query) throws SQLException {  
        statement.executeUpdate(query);
    }
    /**
     * Start a connection with the database.
     * 
     * @return <code>true</code> if the connection came to a good end;
     *         <code>false</code> otherwise.
     */
    public boolean startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + db + ":3306/" + nameDb + "?user=" + username + "&password=" + password);
            statement = connection.createStatement();
            return true;

        } catch (SQLException E) {
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
            return false;
        } catch (Exception E) {
            System.err.println("Driver non trovato");
            E.printStackTrace();
            return false;
        }
    }
    /**
     * 
     * @return <code>true</code> if the connection is closen rightly;
     *         <code>false</code> otherwise.
     */
    public boolean closeConnection() {
        try {
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
