package com.pocketedDays.controller;

import com.pocketedDays.entity.Sheet;
import com.pocketedDays.persistence.SheetDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Create sheet servlet.
 */
@WebServlet (
        urlPatterns = {"/createSheet"}
)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class CreateSheetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit.equals("Add New")) {
            this.doPost(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createSheet.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Part filePart = request.getPart("filePath");
        String fileName = filePart.getSubmittedFileName();

        int projectId = (int) session.getAttribute("projectId");
        int sheetCreatorId = (int) session.getAttribute("userId");
        String sheetType = (String) session.getAttribute("sheetType");

        String sheetDescription = request.getParameter("sheetDescription");
        LocalDate createdDate = LocalDate.now();
        String organization = request.getParameter("organization");
        String filePath = fileName;//request.getParameter("fileName");
        String note = request.getParameter("note");

        Sheet sheet = new Sheet(projectId, sheetDescription, sheetCreatorId, createdDate, organization, filePath, note, sheetType);

        SheetDao sheetDao = new SheetDao();
        sheetDao.insertSheet(sheet);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/sheet");
        dispatcher.forward(request, response);
    }
}