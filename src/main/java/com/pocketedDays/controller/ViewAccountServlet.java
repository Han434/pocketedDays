package com.pocketedDays.controller;

import com.pocketedDays.entity.Quote;
import com.pocketedDays.entity.User;
import com.pocketedDays.persistence.GenericDao;
import com.pocketedDays.persistence.QuoteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * The type View Account servlet.
 */
@WebServlet (
        urlPatterns = {"/viewAccount"}
)
public class ViewAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get userInformation from the request
        Map<String, String> userInformation = (Map<String, String>) request.getAttribute("userInformation");

        //Get user form session
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        RequestDispatcher dispatcher = null;

        //If userInformation is not null"
        if (userInformation != null) {
            //Get random quote
            QuoteDao quoteDao = new QuoteDao();

            //Get data
            String userName = userInformation.get("userName");
            String birthdate = userInformation.get("birthdate");
            String familyName = userInformation.get("familyName");
            String givenName = userInformation.get("givenName");
            String gender = userInformation.get("gender");
            String email = userInformation.get("email");

            //Format the birthdateOfUser
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate brithdateOfUser = LocalDate.parse(birthdate, formatter);

            //Create new user and add it to the database if not exit.
            User userToInsert = new User(givenName, familyName, userName, gender, email, brithdateOfUser);
            GenericDao userDao = new GenericDao<>(User.class);
            List<User> users = userDao.findByPropertyEqual("userName", userName);
            int userId = 0;
            if (users.isEmpty()) {
                userId = userDao.insertEntity(userToInsert);
            } else {
                userId = users.get(0).getUserId();
            }

            //Get user from the userId
            User user = (User) userDao.getById(userId);

            //Set session attribute of user and quote
            session.setAttribute("quoteDao", quoteDao);
            session.setAttribute("user", user);

            //Forward to viewAccount.jsp
            dispatcher = request.getRequestDispatcher("viewAccount.jsp");
        } else if (sessionUser != null) {
            //Forward to viewAccount.jsp
            dispatcher = request.getRequestDispatcher("viewAccount.jsp");
        } else {
            //Forward to logIn
            dispatcher = request.getRequestDispatcher("/logIn");
        }
        dispatcher.forward(request, response);
    }
}
