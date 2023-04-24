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

        GenericDao projectDao = new GenericDao(Project.class);
        GenericDao userProjectDao = new GenericDao(UserProject.class);
        GenericDao userDao = new GenericDao(User.class);

        //If equal to "Add New Project"
        if (submit.equals("Add New Project")) {
            //Get data
            String projectName = request.getParameter("projectName");
            String projectPassword = request.getParameter("projectPassword");
            String projectDescription = request.getParameter("projectDescription");
            LocalDate createdDate = LocalDate.now();

            //Crate and Insert project into the database
            Project project = new Project(projectName, projectPassword, createdDate, projectDescription);
            user.addProject(project);
            userDao.saveOrUpdateEntity(user);
            int projectId = projectDao.insertEntity(project);

            //Create and Insert userProject into the database
            UserProject userProject = new UserProject(user, project, "creator", createdDate);
            userProjectDao.insertEntity(userProject);

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        }
    }
}
