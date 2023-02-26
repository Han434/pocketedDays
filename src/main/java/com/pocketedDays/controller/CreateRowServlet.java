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
            Row row = new Row();
            row.setSheetId(Integer.parseInt(request.getParameter("sheetId")));
            row.setRowCreatorId(Integer.parseInt(request.getParameter("rowCreatorId")));
            row.setRowDescription(request.getParameter("rowDescription"));
            row.setCreatedDate(LocalDate.now());
            row.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            row.setCostPerItem(Integer.parseInt(request.getParameter("costPerItem")));
            row.setType(request.getParameter("type"));
            row.setTag(request.getParameter("tag"));

            RowDao rowDao = new RowDao();
            rowDao.insertRow(row);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createRow.jsp");
            dispatcher.forward(request, response);
        }
    }
}
