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
        //Forward to projectLogIn.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/projectLogIn.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable of user
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        //Get userId form sessionUser
        int userId = sessionUser.getUserId();

        //Get value of submit
        String submit = request.getParameter("submit");


        //If equal to "Add New Project"
        if (submit.equals("Log In to the Project")) {
            GenericDao userProjectDao = new GenericDao(UserProject.class);
            GenericDao userDao = new GenericDao(User.class);
            GenericDao projectDao = new GenericDao(Project.class);

            //Get data
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            User user = (User) userDao.getById(userId);
            String projectPassword = request.getParameter("projectPassword");
            LocalDate joinInDate = LocalDate.now();

            //Get list of project
            Map<String, Object> propertyMap = new HashMap<String, Object>();
            propertyMap.put("projectId", projectId);
            propertyMap.put("projectPassword", projectPassword);
            List<Project> listOfProject = projectDao.findByPropertyEqual(propertyMap);

            //Get required project
            Project project = listOfProject.get(0);

            //Get list of userProject
            Map<String, Object> propertyMapForUserProject = new HashMap<String, Object>();
            propertyMapForUserProject.put("project", project);
            propertyMapForUserProject.put("user", user);
            List<UserProject> listOfUserProject = userProjectDao.findByPropertyEqual(propertyMapForUserProject);

            //If there is no record
            if (listOfUserProject.size() == 0) {
                //Create new UserProject
                UserProject userProject = new UserProject(user, project, "visitor", joinInDate);
                //Insert it to the database
                userProjectDao.insertEntity(userProject);
            }

            //Forward to projectHome
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome?projectId=" + projectId);
            dispatcher.forward(request, response);
        }
    }
}
