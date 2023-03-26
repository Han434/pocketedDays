package com.pocketedDays.controller;

import com.pocketedDays.entity.Row;
import com.pocketedDays.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Delete row servlet.
 */
@WebServlet (
        urlPatterns = {"/deleteRow"}
)
public class DeleteRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get rowId from the request
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Get sheetId from the session
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");

        //Delete row from database
        GenericDao rowDao = new GenericDao(Row.class);
        Row row = (Row) rowDao.getById(rowId);
        rowDao.deleteEntity(row);

        //Forward to viewRow
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
        dispatcher.forward(request, response);
    }
}
