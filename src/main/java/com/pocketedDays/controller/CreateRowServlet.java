package com.pocketedDays.controller;

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
import java.time.LocalDate;

/**
 * The type Create row servlet.
 */
@WebServlet (
        urlPatterns = {"/createRow"}
)
public class CreateRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");

        //If not equal to "Add New"
        if (!submit.equals("Create")) {
            request.setAttribute("sheetId", sheetId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createRow.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");

        //If equal to "Add New"
        if (submit.equals("Create")) {
            //Get data
            int rowCreatorId = (int) session.getAttribute("userId");
            LocalDate createdDate = LocalDate.now();
            String rowDescription = request.getParameter("rowDescription");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int costPerItem = Integer.parseInt(request.getParameter("costPerItem"));
            String rowType = request.getParameter("rowType");
            String tag = request.getParameter("tag");

            //Create new row
            GenericDao sheetDao = new GenericDao(Sheet.class);

            Sheet sheet = (Sheet) sheetDao.getById(sheetId);
            Row row = new Row(sheet, rowCreatorId, createdDate, rowDescription, quantity, costPerItem, rowType, tag);
            GenericDao rowDao = new GenericDao(Row.class);
            rowDao.insertEntity(row);

            //Forward to viewRow
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
            dispatcher.forward(request, response);
        }
    }
}
