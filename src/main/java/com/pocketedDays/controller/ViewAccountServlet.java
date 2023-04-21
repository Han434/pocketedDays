package com.pocketedDays.controller;

import com.pocketedDays.entity.User;
import com.pocketedDays.persistence.GenericDao;

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
        Map<String, String> userInformation = (Map<String, String>) request.getAttribute("userInformation");
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        //If equal to "Add New Project"
        if (userInformation != null) {
            String userName = userInformation.get("userName");
            String birthdate = userInformation.get("birthdate");
            String familyName = userInformation.get("familyName");
            String givenName = userInformation.get("givenName");
            String gender = userInformation.get("gender");
            String email = userInformation.get("email");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            LocalDate brithdateOfUser = LocalDate.parse(birthdate, formatter);

            User user = new User(givenName, familyName, userName, gender, email, brithdateOfUser);
            GenericDao userDao = new GenericDao<>(User.class);
            List<User> users = userDao.findByPropertyEqual("userName", userName);
            int userId = 0;
            if (users.isEmpty()) {
                userId = userDao.insertEntity(user);
            } else {
                userId = users.get(0).getUserId();
            }
            User userData = (User) userDao.getById(userId);

            session.setAttribute("user", userData);

            RequestDispatcher dispatcher = request.getRequestDispatcher("viewAccount.jsp");
            dispatcher.forward(request, response);
        } else if (sessionUser != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewAccount.jsp");
            dispatcher.forward(request, response);
        }
    }
}
