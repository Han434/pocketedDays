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
import java.time.LocalDate;

/**
 * The type Edit project servlet.
 */
@WebServlet (
        urlPatterns = {"/editProject"}
)
public class EditProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String submit = request.getParameter("submit");
        int projectId = (int) session.getAttribute("projectId");

        //If not equal to "Edit Project"
        if (!submit.equals("Edit Project")) {
            // Pass project for edit page
            GenericDao projectDao = new GenericDao(Project.class);
            Project project = (Project) projectDao.getById(projectId);
            request.setAttribute("project", project);

            //Forward to editProject.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editProject.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        int projectCreatorId = (int) session.getAttribute("userId");

        //If equal to "Edit Project"
        if (submit.equals("Edit Project")) {
            //Get project by id
            GenericDao projectDao = new GenericDao(Project.class);
            Project project = (Project) projectDao.getById(projectId);

            //Set updated data
            project.setProjectName(request.getParameter("projectName"));
            project.setProjectCreatorId(projectCreatorId);
            project.setProjectPassword(request.getParameter("projectPassword"));
            project.setUpdatedDate(LocalDate.now());
            project.setProjectDescription(request.getParameter("projectDescription"));

            //Update the project in the database
            projectDao.saveOrUpdateEntity(project);

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        }
    }
}
