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

@WebServlet (
        urlPatterns = {"/deleteRow"}
)
public class DeleteRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rowId = Integer.parseInt(request.getParameter("rowId"));
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");

        RowDao rowDao = new RowDao();
        Row row = rowDao.getRowsByRowId(rowId);
        rowDao.deleteRow(row);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
        dispatcher.forward(request, response);
    }
}
