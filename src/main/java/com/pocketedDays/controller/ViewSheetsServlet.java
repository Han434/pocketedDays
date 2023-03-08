package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
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
        SheetDao sheetDao = new SheetDao();
        ProjectDao projectDao = new ProjectDao();

        //Get the sheetType from the request
        String sheetType = request.getParameter("sheetType");

        //Get session variable projectId
        //Set session variable sheetType
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        session.setAttribute("sheetType", sheetType);

        //Get project by projectId
        Project project = projectDao.getProjectByProjectId(projectId);

        //Get sheet totals and project total
        Set<Sheet> sheets = project.getSheets();
        Map<Integer,Integer> sheetTotals = new HashMap<Integer,Integer>();
        int projectTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculatedTotal();
            projectTotal += sheetTotal;
            sheetTotals.put(sheet.getSheetId(), sheetTotal);
        }

        //Pass project, sheet, sheetTotals, projectTotal and sheetType
        request.setAttribute("project", project);
        request.setAttribute("sheets", project.getSheets());
        request.setAttribute("sheetTotals", sheetTotals);
        request.setAttribute("projectTotal", projectTotal);
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
