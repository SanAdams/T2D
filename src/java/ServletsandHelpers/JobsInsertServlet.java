/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;

@WebServlet(name = "JobsInsertServlet", urlPatterns = {
    "/JobsInsertServlet"
})
public class JobsInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String jnum = request.getParameter("jnum");
        String jname = request.getParameter("jname");
        int numworkers = Integer.parseInt(request.getParameter("numworkers"));
        String city = request.getParameter("city");

        Connection connection = null;
        try {
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getDataEntryConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into jobs values(?,?,?,?)");
            preparedStatement.setString(1, jnum);
            preparedStatement.setString(2, jname);
            preparedStatement.setString(4, city);
            preparedStatement.setInt(3, numworkers);

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
