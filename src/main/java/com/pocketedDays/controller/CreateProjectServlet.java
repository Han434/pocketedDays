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
        //Forward to createProject.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createProject.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //If equal to "Add New Project"
        if (submit.equals("Add New Project")) {
            //Get data
            String projectName = request.getParameter("projectName");
            int projectCreatorId = user.getUserId();
            String projectPassword = request.getParameter("projectPassword");
            LocalDate createdDate = LocalDate.now();
            String projectDescription = request.getParameter("projectDescription");

            //Create new project
            Project project = new Project( projectCreatorId, projectName, projectPassword, createdDate, projectDescription);

            //Insert it to the database
            GenericDao projectDao = new GenericDao(Project.class);
            int projectId = projectDao.insertEntity(project);

            GenericDao userProjectDao = new GenericDao(UserProject.class);
            UserProject userProject = new UserProject(user, project, "creator", createdDate);
            int userProjectId = userProjectDao.insertEntity(userProject);

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        }
    }
}
