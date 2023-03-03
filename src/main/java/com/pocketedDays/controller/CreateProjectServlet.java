package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.persistence.ProjectDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Create project servlet.
 */
@WebServlet (
        urlPatterns = {"/createProject"}
)
public class CreateProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        if (submit.equals("Add New Project")) {
            String projectName = request.getParameter("projectName");
            int projectCreatorId = userId;
            String projectPassword = request.getParameter("projectPassword");
            LocalDate createdDate = LocalDate.now();
            String projectDescription = request.getParameter("projectDescription");
            Project project = new Project( projectCreatorId, projectName, projectPassword, createdDate, projectDescription);

            ProjectDao projectDao = new ProjectDao();
            int projectId = projectDao.insertProject(project);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createProject.jsp");
            dispatcher.forward(request, response);
        }
    }
}
