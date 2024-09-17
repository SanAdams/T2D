/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

import java.io.IOException;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;

@WebServlet(name = "AccountantUserServlet", urlPatterns = {
    "/AccountantUserServlet"
})
public class AccountantUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            String selectedOption = request.getParameter("selection");
            ConnectionHandler connectionManager = new ConnectionHandler();
            connection = connectionManager.getAccountantConnection();
            CallableStatement statement = null;
            if (null == selectedOption) {
                request.setAttribute("errorMessage", "Please Select Any Statement ");
            } else 
            switch (selectedOption) {
            case "Get_The_Maximum_Status_Of_All_Suppliers":
                statement = connection.prepareCall("{CALL Get_The_Maximum_Status_Of_All_Suppliers()}");
                statement.executeQuery();
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
                break;
            case "Get_The_Sum_Of_All_Parts_Weights":
                statement = connection.prepareCall("{CALL Get_The_Sum_Of_All_Parts_Weights()}");
                statement.executeQuery();
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
                break;
            case "Get_The_Total_Number_Of_Shipments":
                statement = connection.prepareCall("{CALL Get_The_Total_Number_Of_Shipments()}");
                statement.executeQuery();
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
                break;
            case "Get_The_Name_Of_The_Job_With_The_Most_Workers":
                statement = connection.prepareCall("{CALL Get_The_Name_Of_The_Job_With_The_Most_Workers()}");
                statement.executeQuery();
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
                break;
            case "List_The_Name_And_Status_Of_All_Suppliers":
                statement = connection.prepareCall("{CALL List_The_Name_And_Status_Of_All_Suppliers()}");
                statement.executeQuery();
                resultSet = statement.getResultSet();
                request.setAttribute("resultSet", resultSet);
                break;
            default:
                request.setAttribute("errorMessage", "Please Select Any Statement ");
                break;
            }
        } catch (Exception ex) {

            request.setAttribute("errorMessage", ex.getMessage());

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("accountantHome.jsp");
        dispatcher.forward(request, response);
    }

}