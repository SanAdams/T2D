/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "ClientUserServlet", urlPatterns = {
    "/ClientUserServlet"
})
public class ClientUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String query = request.getParameter("queryInput");
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getClientConnection();
            Statement statement = connection.createStatement();
            int change = 0;
           
            QueryHelper queryHelper = new QueryHelper();

            switch (queryHelper.determineQueryType(query)) {
                case "INSERT":
                    change = statement.executeUpdate(query);
                    if (change > 0) {
                        request.setAttribute("successMessage", "Insert successful! " + change + " row(s) inserted."); 
                    }   break;
                case "UPDATE":
                    change = statement.executeUpdate(query);
                    if (change > 0) {
                        request.setAttribute("successMessage", "Update Successful! " + change + " row(s) Updated.");
                    }   break;
                case "DELETE":
                    change = statement.executeUpdate(query);
                    if (change > 0) {
                        request.setAttribute("successMessage", "Delete successful! " + change + " row(s) deleted.");
                    }   break;
                case "REPLACE":
                    change = statement.executeUpdate(query);
                    if (change > 0) {
                        request.setAttribute("successMessage", "Replace successful! " + change + " row(s) Replaced.");
                        
                    }   break;
                case "SELECT":
                    resultSet = statement.executeQuery(query);
                    resultSet = statement.getResultSet();
                    request.setAttribute("resultSet", resultSet);
                    break;
                default:
                    break;
            } 
            
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("clientHome.jsp");
        dispatcher.forward(request, response);
    }
}