package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.entity.User;
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
 * The type RunReport servlet.
 */
@WebServlet (
        urlPatterns = {"/runReportServlet"}
)
public class RunReportServlet extends HttpServlet {
    private GenericDao projectDao = new GenericDao(Project.class);
    private GenericDao sheetDao = new GenericDao(Sheet.class);
    private GenericDao rowDao = new GenericDao(Row.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable of projectId
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        //Get project with projectId
        Project project = (Project) projectDao.getById(projectId);

        //Get List of List of Row
        List<List<Row>> listOfListOfRowsForRevenue = getListOfListOfRow("Revenue", project);
        List<List<Row>> listOfListOfRowsForExpense = getListOfListOfRow("Expense", project);

        //Set attibutes of project, listOfListOfRowsForRevenue and listOfListOfRowsForExpense
        request.setAttribute("project", project);
        request.setAttribute("listOfListOfRowsForRevenue", listOfListOfRowsForRevenue);
        request.setAttribute("listOfListOfRowsForExpense", listOfListOfRowsForExpense);

        //Forward to runReport.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/runReport.jsp");
        dispatcher.forward(request, response);
    }

    protected List<List<Row>> getListOfListOfRow(String sheetType, Project project) {
        //Get list of sheet
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", sheetType);
        propertyMap.put("project", project);
        List<Sheet> sheets = sheetDao.findByPropertyEqual(propertyMap);

        //Get list of list of rows
        List<List<Row>> listOfListOfRows = new ArrayList<>();
        for (Sheet sheet : sheets) {
            List<Row> rows = rowDao.findByPropertyEqual("sheet", sheet);
            listOfListOfRows.add(rows);
        }
        return  listOfListOfRows;
    }
}
