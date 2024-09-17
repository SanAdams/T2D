/* Sebastien Adam
*  CNT 4714 – Fall 2023 – Project Four
*  Assignment title: A Three-Tier Distributed Web-Based Application
*  Date: November 5, 2023
*/
package ServletsandHelpers;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(name = "AuthenticationServlet", urlPatterns = {
    "/AuthenticationServlet"
})
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

     
        String inputUsername = request.getParameter("username");
        String inputPassword = request.getParameter("password");
        ArrayList < String > credentials = new ArrayList < > ();
        InputStream input = getServletContext().getResourceAsStream("credentials.txt");

        if (input != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    credentials.add(line);
                }
            }
        }

        for (int i = 0; i < credentials.size(); i++) {
            String username = credentials.get(i).split(",")[0];
            String password = credentials.get(i).split(",")[1];

            if (inputUsername.equals(username) && password.equals(inputPassword)) {
                switch (username.charAt(0)) {
                    case 'r':
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/rootHome.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case 'c':
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/clientHome.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case 'd':
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/dataEntryHome.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case 'a':
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/accountantHome.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    default:
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.html");
                            dispatcher.forward(request, response);
                            break;
                        }
                }
            }
        }
    }
}