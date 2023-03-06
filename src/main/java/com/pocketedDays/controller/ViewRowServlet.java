package com.pocketedDays.controller;

import com.pocketedDays.entity.Sheet;
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
 * The type View row servlet.
 */
@WebServlet (
        urlPatterns = {"/viewRow"}
)
public class ViewRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectDao projectDao = new ProjectDao();
        SheetDao sheetDao = new SheetDao();

        //Get sheetId from request
        int sheetId = Integer.parseInt(request.getParameter("sheetId"));

        //Set session variable sheetId
        //Get session variable projectId
        HttpSession session = request.getSession();
        session.setAttribute("sheetId", sheetId);
        int projectId = (int) session.getAttribute("projectId");

        //Get sheet by sheetId
        Sheet sheet = sheetDao.getSheetBySheetId(sheetId);

        //Pass sheet, rows and project
        request.setAttribute("sheet", sheet);
        request.setAttribute("rows", sheet.getRows());
        request.setAttribute("project", projectDao.getProjectByProjectId(projectId));

        //Forward to viewRow.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Call doGet
        this.doGet(request, response);
    }
}
