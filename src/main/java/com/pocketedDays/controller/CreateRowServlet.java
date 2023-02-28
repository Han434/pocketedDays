package com.pocketedDays.controller;

import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
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
import java.time.LocalDate;

@WebServlet (
        urlPatterns = {"/createRow"}
)
public class CreateRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit.equals("Add New")) {
            HttpSession session = request.getSession();

            int sheetId = (int) session.getAttribute("sheetId");
            int rowCreatorId = (int) session.getAttribute("userId");
            LocalDate createdDate = LocalDate.now();
            String rowDescription = request.getParameter("rowDescription");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int costPerItem = Integer.parseInt(request.getParameter("costPerItem"));
            String rowType = request.getParameter("rowType");
            String tag = request.getParameter("tag");
            Row row = new Row(sheetId, rowCreatorId, createdDate, rowDescription, quantity, costPerItem, rowType, tag);

            RowDao rowDao = new RowDao();
            rowDao.insertRow(row);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createRow.jsp");
            dispatcher.forward(request, response);
        }
    }
}
