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

@WebServlet(name = "PartsInsertServlet", urlPatterns = {
    "/PartsInsertServlet"
})
public class PartsInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String pnum = request.getParameter("pnum");
        String pname = request.getParameter("pname");
        String color = request.getParameter("color");
        int weight = Integer.parseInt(request.getParameter("weight"));
        String city = request.getParameter("city");

        Connection connection = null;

        try {
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getDataEntryConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into parts values(?,?,?,?,?)");
            preparedStatement.setString(1, pnum);
            preparedStatement.setString(2, pname);
            preparedStatement.setString(3, color);
            preparedStatement.setString(5, city);
            preparedStatement.setInt(4, weight);

            int change = preparedStatement.executeUpdate();
            if (change > 0) {
                request.setAttribute("successMessage", "Insert successful! " + change + " row(s) inserted.");
            }

        } catch (SQLException e) {

            request.setAttribute("errorMessage", e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("dataEntryHome.jsp");
        dispatcher.forward(request, response);
    }

}