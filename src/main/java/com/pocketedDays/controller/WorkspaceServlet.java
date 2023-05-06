package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Quote;
import com.pocketedDays.entity.User;
import com.pocketedDays.entity.UserProject;
import com.pocketedDays.persistence.GenericDao;
import com.pocketedDays.persistence.QuoteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * The type Workspace servlet.
 */
@WebServlet (
        urlPatterns = {"/workspace"}
)
public class WorkspaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable of user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        GenericDao<UserProject> userProjectDao = new GenericDao<>(UserProject.class);

        if (user == null) {
            //Forward to logIn
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logIn");
            dispatcher.forward(request, response);
        } else {
            //Get listOfProject
            List<Project> projects = new ArrayList<>();
            List<UserProject> userProjects = userProjectDao.findByPropertyEqual("user", user);
            //Set<UserProject> userProjects = user.getProjects();
            for (UserProject userProject : userProjects) {
                Project project = userProject.getProject();
                projects.add(project);
            }

            //Get attribute of projects
            request.setAttribute("projects", projects);

            //Forward to workspace.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace.jsp");
            dispatcher.forward(request, response);
        }
    }
}
