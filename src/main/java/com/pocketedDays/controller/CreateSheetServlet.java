package com.pocketedDays.controller;

import com.pocketedDays.entity.Sheet;
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
        urlPatterns = {"/createSheet"}
)
public class CreateSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        String sheetType = (String) session.getAttribute("sheetType");
        if (submit.equals("Add New Sheet")) {
            String sheetDescription = request.getParameter("sheetDescription");
            LocalDate createdDate = LocalDate.now();
            String organization = request.getParameter("organization");
            String filePath = request.getParameter("filePath");
            String note = request.getParameter("note");
            Sheet sheet = new Sheet(projectId, sheetDescription, createdDate, organization, filePath, note, sheetType);

            SheetDao sheetDao = new SheetDao();
            sheetDao.insertSheet(sheet);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/sheet");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createSheet.jsp");
            dispatcher.forward(request, response);
        }
    }
}
