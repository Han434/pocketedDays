package com.pocketedDays.controller;

import com.pocketedDays.entity.UserProject;
import com.pocketedDays.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/removeUser"}
)
public class RemoveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userProjectDao = new GenericDao(UserProject.class);

        //Gets session variable projectId and userType
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        String userType = (String) session.getAttribute("userType");

        if ((!(userType.equals("visitor")))) {
            //Remove User form database
            int userProjectId = Integer.parseInt(request.getParameter("userProjectId"));
            UserProject userProject = (UserProject) userProjectDao.getById(userProjectId);
            userProjectDao.deleteEntity(userProject);

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/unauthorized.jsp");
            dispatcher.forward(request, response);
        }
    }
}
