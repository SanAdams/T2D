/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "SuppliersInsertServlet", urlPatterns = {
    "/SuppliersInsertServlet"
})
public class SuppliersInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String snum = request.getParameter("snum");
        String sname = request.getParameter("sname");
        int status = Integer.parseInt(request.getParameter("status"));
        String city = request.getParameter("city");

        Connection connection = null;

        try {
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getDataEntryConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into suppliers values(?,?,?,?)");
            preparedStatement.setString(1, snum);
            preparedStatement.setString(2, sname);
            preparedStatement.setString(4, city);
            preparedStatement.setInt(3, status);
            
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