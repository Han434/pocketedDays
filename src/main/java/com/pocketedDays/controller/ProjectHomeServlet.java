package com.pocketedDays.controller;

import com.pocketedDays.persistence.ProjectDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Project home servlet.
 */
@WebServlet (
        urlPatterns = {"/projectHome"}
)
public class ProjectHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get projectId from the request
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        //Set session variable of projectId
        HttpSession session = request.getSession();
        session.setAttribute("projectId", projectId);

        //Pass project with the requested id
        ProjectDao projectDao = new ProjectDao();
        request.setAttribute("project", projectDao.getProjectByProjectId(projectId));

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
