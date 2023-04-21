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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Create project servlet.
 */
@WebServlet (
        urlPatterns = {"/projectLogIn"}
)
public class ProjectLogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Forward to createProject.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/projectLogIn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        //If equal to "Add New Project"
        if (submit.equals("Log In to the Project")) {
            GenericDao userDao = new GenericDao(User.class);
            GenericDao projectDao = new GenericDao(Project.class);
            //Get data
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            int projectCreatorId = userId;
            User user = (User) userDao.getById(userId);
            String projectPassword = request.getParameter("projectPassword");
            LocalDate joinInDate = LocalDate.now();

            Map<String, Object> propertyMap = new HashMap<String, Object>();
            propertyMap.put("projectId", projectId);
            propertyMap.put("projectPassword", projectPassword);

            //Create new project
            List<Project> listOfProject = (List<Project>) projectDao.findByPropertyEqual(propertyMap);
            Project project = listOfProject.get(0);
            projectDao.getById(projectId);
            UserProject userProject = new UserProject(user, project, "Visitor", joinInDate);

            //Insert it to the database
            GenericDao userProjectDao = new GenericDao(UserProject.class);
            int userProjectId = userProjectDao.insertEntity(userProject);

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        }
    }
}
