package com.pocketedDays.controller;

import com.pocketedDays.persistence.ProjectDao;
import com.pocketedDays.persistence.SheetDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type View sheets servlet.
 */
@WebServlet (
        urlPatterns = {"/sheet"}
)
public class ViewSheetsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SheetDao sheetDao = new SheetDao();
        ProjectDao projectDao = new ProjectDao();

        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        String sheetType = request.getParameter("sheetType");
        session.setAttribute("sheetType", sheetType);

        //request.setAttribute("sheets", sheetDao.getAllSheets());
        request.setAttribute("sheets", sheetDao.getSheetsByProjectId(projectId));
        request.setAttribute("project", projectDao.getProjectByProjectId(projectId));
        request.setAttribute("viewTypeForHeader", sheetType);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewSheets.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
