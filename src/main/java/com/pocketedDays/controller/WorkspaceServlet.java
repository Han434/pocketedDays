package com.pocketedDays.controller;

import com.pocketedDays.persistence.ProjectDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (
        urlPatterns = {"/workspace"}
)
public class WorkspaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectDao projectDao = new ProjectDao();
        //request.setAttribute("projects", projectDao.getAllProjects());
        request.setAttribute("projects", projectDao.getProjectByUserId(1));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace.jsp");
        dispatcher.forward(request, response);
    }
}
