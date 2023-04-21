package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
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
import java.util.*;

/**
 * The type View sheets servlet.
 */
@WebServlet (
        urlPatterns = {"/sheet"}
)
public class ViewSheetsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao sheetDao = new GenericDao(Sheet.class);
        GenericDao projectDao = new GenericDao(Project.class);

        //Get the sheetType from the request
        String sheetType = request.getParameter("sheetType");

        //Get session variable projectId
        //Set session variable sheetType
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        session.setAttribute("sheetType", sheetType);

        //Get project by projectId
        Project project = (Project) projectDao.getById(projectId);

        //Get sheet totals and project total
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", sheetType);
        propertyMap.put("project", project);

        List<Sheet> sheets = sheetDao.findByPropertyEqual(propertyMap);

        //Pass project, sheet, sheetType
        request.setAttribute("project", project);
        request.setAttribute("sheets", sheets);
        request.setAttribute("viewTypeForHeader", sheetType);

        //Forward to viewSheets.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewSheets.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Call doGet
        this.doGet(request, response);
    }
}
