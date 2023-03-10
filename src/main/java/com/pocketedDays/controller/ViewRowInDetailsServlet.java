package com.pocketedDays.controller;

import com.pocketedDays.persistence.ProjectDao;
import com.pocketedDays.persistence.RowDao;
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
 * The type View row in details servlet.
 */
@WebServlet (
        urlPatterns = {"/viewRowInDetails"}
)
public class ViewRowInDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable of projectId, sheetId, rowId
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        int sheetId = (int) session.getAttribute("sheetId");
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Pass row, sheet, project
        RowDao rowDao = new RowDao();
        SheetDao sheetDao = new SheetDao();
        ProjectDao projectDao = new ProjectDao();
        request.setAttribute("row", rowDao.getRowsByRowId(rowId));
        request.setAttribute("sheet", sheetDao.getSheetBySheetId(sheetId));
        request.setAttribute("project", projectDao.getProjectByProjectId(projectId));

        //Forward to viewRowInDetails.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRowInDetails.jsp");
        dispatcher.forward(request, response);
    }
}
