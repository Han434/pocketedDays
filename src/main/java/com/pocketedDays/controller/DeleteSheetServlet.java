package com.pocketedDays.controller;

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

/**
 * The type Delete sheet servlet.
 */
@WebServlet (
        urlPatterns = {"/deleteSheet"}
)
public class DeleteSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable sheetId and sheetType
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");
        String sheetType = (String) session.getAttribute("sheetType");

        //Get sheet form database
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Sheet sheet = (Sheet) sheetDao.getById(sheetId);

        //Delete sheet from the database
        sheetDao.deleteEntity(sheet);

        //Forward to sheet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sheet?sheetType=" + sheetType);
        dispatcher.forward(request, response);
    }
}
