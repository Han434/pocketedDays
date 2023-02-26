package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
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

@WebServlet (
        urlPatterns = {"/deleteSheet"}
)
public class DeleteSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sheetId = (int) request.getAttribute("sheetId");

        SheetDao sheetDao = new SheetDao();
        Sheet sheet = sheetDao.getSheetById(sheetId);
        sheetDao.deleteSheet(sheet);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/revenue");
        dispatcher.forward(request, response);
    }
}
