package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * The type Workspace servlet.
 */
@WebServlet (
        urlPatterns = {"/workspace"}
)
public class WorkspaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userProjectDao = new GenericDao(UserProject.class);
        GenericDao userDao = new GenericDao(User.class);
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logIn");
            dispatcher.forward(request, response);
        } else {
            List<Project> projects = new ArrayList<>();

            List<UserProject> userProjects = userProjectDao.findByPropertyEqual("user", user);
            for (UserProject userProject : userProjects) {
                Project project = userProject.getProject();
                projects.add(project);
            }

            request.setAttribute("projects", projects);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace.jsp");
            dispatcher.forward(request, response);
        }
    }
}
