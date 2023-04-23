package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
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
import java.util.List;

/**
 * The type Delete project servlet.
 */
@WebServlet (
        urlPatterns = {"/deleteProject"}
)
public class DeleteProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao projectDao = new GenericDao(Project.class);
        GenericDao userProjectDao = new GenericDao(UserProject.class);

        //Gets session variable projectId
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        //Get project
        Project project = (Project) projectDao.getById(projectId);

        //Delete userProject related to the project from database
        List<UserProject> userProjectList = userProjectDao.findByPropertyEqual("project", project);
        for (UserProject userProject : userProjectList) {
            userProjectDao.deleteEntity(userProject);
        }

        //Delete project from database
        projectDao.deleteEntity(project);

        //Forward to workspace
        RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace");
        dispatcher.forward(request, response);
    }
}
