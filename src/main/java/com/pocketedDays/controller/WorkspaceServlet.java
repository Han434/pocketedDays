package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Workspace servlet.
 */
@WebServlet (
        urlPatterns = {"/workspace"}
)
public class WorkspaceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao projectDao = new GenericDao(Project.class);

        int userId = 1;
        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

        request.setAttribute("projects", projectDao.getByProperty("projectCreatorId", 1));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/workspace.jsp");
        dispatcher.forward(request, response);
    }
}
