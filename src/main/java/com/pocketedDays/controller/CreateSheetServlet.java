package com.pocketedDays.controller;

import com.pocketedDays.entity.Sheet;
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
        urlPatterns = {"/createSheet"}
)
public class CreateSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit.equals("Add New")) {
            Sheet sheet = new Sheet();
            sheet.setProjectId(Integer.parseInt(request.getParameter("projectId")));
            sheet.setSheetDescription(request.getParameter("sheetDescription"));
            sheet.setCreatedDate(LocalDate.now());
            sheet.setOrganization(request.getParameter("organization"));
            sheet.setFilePath(request.getParameter("filePath"));
            sheet.setNote(request.getParameter("note"));

            SheetDao sheetDao = new SheetDao();
            sheetDao.insertSheet(sheet);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/revenue");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createSheet.jsp");
            dispatcher.forward(request, response);
        }
    }
}
