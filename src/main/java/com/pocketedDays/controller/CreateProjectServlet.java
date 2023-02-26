package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.persistence.ProjectDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet (
        urlPatterns = {"/createProject"}
)
public class CreateProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit.equals("Add New")) {
            Project project = new Project();
            project.setProjectName(request.getParameter("projectName"));
            project.setCreatorId(Integer.parseInt(request.getParameter("creatorId")));
            project.setProjectPassword(request.getParameter("projectPassword"));
            project.setCreatedDate(LocalDate.now());
            project.setProjectDescription(request.getParameter("projectDescription"));

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
