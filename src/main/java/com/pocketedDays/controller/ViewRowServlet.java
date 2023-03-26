package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type View row servlet.
 */
@WebServlet (
        urlPatterns = {"/viewRow"}
)
public class ViewRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao projectDao = new GenericDao(Project.class);
        GenericDao sheetDao = new GenericDao(Sheet.class);

        //Get sheetId from request
        int sheetId = Integer.parseInt(request.getParameter("sheetId"));

        //Set session variable sheetId
        //Get session variable projectId
        HttpSession session = request.getSession();
        session.setAttribute("sheetId", sheetId);
        int projectId = (int) session.getAttribute("projectId");

        //Get sheet by sheetId
        Sheet sheet = (Sheet) sheetDao.getById(sheetId);

        //Get row
        Set<Row> rows = sheet.getRows();

        //Pass sheet, rows and project
        request.setAttribute("sheet", sheet);
        request.setAttribute("rows", rows);
        request.setAttribute("project", projectDao.getById(projectId));

        //Forward to viewRows.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRows.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Call doGet
        this.doGet(request, response);
    }
}
