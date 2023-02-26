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

@WebServlet (
        urlPatterns = {"/editProject"}
)
public class EditProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        if (submit.equals("Edit")) {
            ProjectDao projectDao = new ProjectDao();
            Project project = projectDao.getProjectById(projectId);
            
            project.setProjectName(request.getParameter("projectName"));
            project.setCreatorId(Integer.parseInt(request.getParameter("creatorId")));
            project.setProjectPassword(request.getParameter("projectPassword"));
            project.setCreatedDate(LocalDate.now());
            project.setProjectDescription(request.getParameter("projectDescription"));

            projectDao.saveOrUpdateProject(project);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        } else {
            ProjectDao projectDao = new ProjectDao();
            Project project = projectDao.getProjectById(projectId);
            request.setAttribute("project", project);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/editProject.jsp");
            dispatcher.forward(request, response);
        }
    }
}
