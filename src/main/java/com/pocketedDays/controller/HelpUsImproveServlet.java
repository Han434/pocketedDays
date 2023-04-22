package com.pocketedDays.controller;

import com.pocketedDays.entity.User;
import com.pocketedDays.entity.UserProject;
import com.pocketedDays.persistence.GenericDao;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
/**
 * The type Workspace servlet.
 */
@WebServlet (
        urlPatterns = {"/helpUsImprove"}
)
public class HelpUsImproveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userProjectDao = new GenericDao(UserProject.class);
        GenericDao userDao = new GenericDao(User.class);
        HttpSession session = request.getSession();
        String submit = request.getParameter("submit");

        String feedbackDescription = request.getParameter("feedbackDescription");

        User user = (User) session.getAttribute("user");

        if (user == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logIn");
            dispatcher.forward(request, response);
        } else if (submit == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/helpUsImprove.jsp");
            dispatcher.forward(request, response);
        } else {
            String userEmail = user.getEmail();
            String userName = user.getFirstName() + " " + user.getLastName();
            GMailer gMailer = null;
            try {
                String reply = "Dear " + userName + ",\n\tThank you for reaching out and providing us with valuable "
                        + "feedback.\n\nYour Feedback:\n\t" + feedbackDescription;
                gMailer = new GMailer();
                gMailer.sendMail("Reply for your feedback.", reply, userEmail);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
