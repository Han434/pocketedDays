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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Project home servlet.
 */
@WebServlet (
        urlPatterns = {"/projectHome"}
)
public class ProjectHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao projectDao = new GenericDao(Project.class);
        GenericDao userProjectDao = new GenericDao(UserProject.class);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Get projectId from the request
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        //Get project with projectId
        Project project = (Project) projectDao.getById(projectId);

        //Create and Set the property map
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("userType", "creator");
        propertyMap.put("project", project);

        //Get userProjectList
        List<UserProject> userProjectList = userProjectDao.findByPropertyEqual(propertyMap);

        //Get userProjectListForProjectMember
        List<UserProject> userProjectListForProjectMember = userProjectDao.findByPropertyEqual("project", project);

        //Create memberNames
        List<String> memberNames = new ArrayList<>();

        //Get memberNames
        User creator = userProjectList.get(0).getUser();
        for (UserProject userProject : userProjectListForProjectMember) {
            User member = userProject.getUser();
            String memberName = member.getUserName();
            memberNames.add(memberName);
        }

        Map<String, Object> propertyMapForUserProject = new HashMap<String, Object>();
        propertyMapForUserProject.put("project", project);
        propertyMapForUserProject.put("user", user);
        List<UserProject> listOfUserProject = userProjectDao.findByPropertyEqual(propertyMapForUserProject);

        String userType = listOfUserProject.get(0).getUserType();

        //Set session variable of projectId
        session.setAttribute("projectId", projectId);
        session.setAttribute("userType", userType);

        //Set attributes of creator, memberNames and project
        request.setAttribute("creator", creator);
        request.setAttribute("memberNames", memberNames);
        request.setAttribute("project", project);

        //Forward to projectHome.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Call doGet
        this.doGet(request, response);
    }
}
