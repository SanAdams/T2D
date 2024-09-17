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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

@WebServlet(name = "RootUserServlet", urlPatterns = {
    "/RootUserServlet"
})
public class RootUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String query = request.getParameter("queryInput");
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getRootConnection();
            Statement statement = connection.createStatement();
            QueryHelper queryHelper = new QueryHelper();
            int change;

            if (queryHelper.determineQueryType(query).equals("INSERT")) {

                change = statement.executeUpdate(query);

                if (change > 0) {
                    request.setAttribute("successMessage", "Insert successful! " + change + " row(s) inserted.");

                } 

            } else if (queryHelper.determineQueryType(query).equals("UPDATE")) {
                change = statement.executeUpdate(query);
                
                if (change > 0) {
                    request.setAttribute("successMessage", "Update Successful! " + change + " row(s) Updated.");
                } 

            } else if (queryHelper.determineQueryType(query).equals("DELETE")) {
                change = statement.executeUpdate(query);
                
                if (change > 0) {
                    request.setAttribute("successMessage", "Deletion successful! " + change + " row(s) deleted.");
                } 
            } else if (queryHelper.determineQueryType(query).equals("REPLACE")) {
                change = statement.executeUpdate(query);
                
                if (change > 0) {
                    request.setAttribute("successMessage", "Replace successful! " + change + " row(s) Replaced.");
                } 
            } else if (queryHelper.determineQueryType(query).equals("SELECT")) {
                resultSet = statement.executeQuery(query);
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
            } else {
                request.setAttribute("errorMessage", "Invalid Query! Resubmit your query");
            }

        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("rootHome.jsp");
        dispatcher.forward(request, response);

    }

}