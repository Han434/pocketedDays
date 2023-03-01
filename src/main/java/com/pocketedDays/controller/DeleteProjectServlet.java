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

@WebServlet (
        urlPatterns = {"/deleteProject"}
)
public class DeleteProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        ProjectDao projectDao = new ProjectDao();
        Project project = projectDao.getProjectByProjectId(projectId);
        projectDao.deleteProject(project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace");
        dispatcher.forward(request, response);
    }
}
