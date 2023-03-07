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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //Pass project sheet and sheetType
        Project project = projectDao.getProjectByProjectId(projectId);
        request.setAttribute("project", project);

        List<Sheet> sheets = project.getSheets();
        Map<Integer,Integer> sheetTotals = new HashMap<Integer,Integer>();
        for (Sheet sheet : sheets) {
            List<Row> rows = sheet.getRows();
            int sheetTotal = 0;
            for (Row row : rows) {
                int rowTotal = row.getQuantity() * row.getCostPerItem();
                sheetTotal += rowTotal;
            }
            sheetTotals.put(sheet.getSheetId(), sheetTotal);
        }

        request.setAttribute("sheets", project.getSheets());
        request.setAttribute("sheetTotals", sheetTotals);
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
