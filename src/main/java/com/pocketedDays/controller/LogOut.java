package com.pocketedDays.controller;


import com.pocketedDays.entity.User;
import com.pocketedDays.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logOut"}
)

/** Begins the authentication process using AWS Cognito
 *
 */
public class LogOut extends HttpServlet implements PropertiesLoader {
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGOUT_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        loadProperties();
    }

    /**
     * Read in the cognito props file and get the client id and required urls
     * for authenticating a user.
     */
    // TODO This code appears in a couple classes, consider using a startup servlet similar to adv java project
    // 4 to do this work a single time and put the properties in the application scope
    private void loadProperties() {
        try {
            properties = loadProperties("/cognito.properties");
            CLIENT_ID = properties.getProperty("client.id");
            LOGOUT_URL = properties.getProperty("loginURL");
            REDIRECT_URL = properties.getProperty("redirectURL");
        } catch (IOException ioException) {
            logger.error("Cannot load properties..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO if properties weren't loaded properly, route to an error page
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logIn");
            dispatcher.forward(request, response);
        } else {
            session.removeAttribute("user");
            String url = "https://pocketed.auth.us-east-2.amazoncognito.com/logout?client_id=27rfufbu3j9ro6pbub70kcu67h&logout_uri=https://pocketeddays-env.eba-znfy6w8h.us-east-2.elasticbeanstalk.com/index.jsp";
            response.sendRedirect(url);
        }
    }
}
