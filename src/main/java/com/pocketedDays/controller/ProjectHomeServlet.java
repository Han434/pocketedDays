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

@WebServlet (
        urlPatterns = {"/projectHome"}
)
public class ProjectHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        HttpSession session = request.getSession();
        session.setAttribute("projectId", projectId);

        ProjectDao projectDao = new ProjectDao();
        request.setAttribute("project", projectDao.getProjectByProjectId(projectId));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/projectHome.jsp");
        dispatcher.forward(request, response);
    }
}
