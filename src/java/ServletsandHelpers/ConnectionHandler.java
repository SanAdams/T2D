/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;
import java.sql.*;

public class ConnectionHandler {

    public Connection getAccountantConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "accountant", "accountant");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    public Connection getDataEntryConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "dataentryuser", "dataentryuser");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    public Connection getClientConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "client", "client");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {

            return null;
        }
    }

    public Connection getRootConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "root", "YouTuBe1");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}