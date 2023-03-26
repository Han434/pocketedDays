package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Delete project servlet.
 */
@WebServlet (
        urlPatterns = {"/deleteProject"}
)
public class DeleteProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Gets session variable projectId
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        //Delete project from database
        GenericDao projectDao = new GenericDao(Project.class);
        Project project = (Project) projectDao.getById(projectId);
        projectDao.deleteEntity(project);

        //Forward to workspace
        RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace");
        dispatcher.forward(request, response);
    }
}
