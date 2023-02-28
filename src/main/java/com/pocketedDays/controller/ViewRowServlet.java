package com.pocketedDays.controller;

import com.pocketedDays.persistence.ProjectDao;
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
        urlPatterns = {"/viewRow"}
)
public class ViewRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RowDao rowDao = new RowDao();
        ProjectDao projectDao = new ProjectDao();
        SheetDao sheetDao = new SheetDao();

        int sheetId = Integer.parseInt(request.getParameter("sheetId"));
        HttpSession session = request.getSession();
        session.setAttribute("sheetId", sheetId);
        int projectId = (int) session.getAttribute("projectId");

        request.setAttribute("sheet", sheetDao.getSheetById(sheetId));
        request.setAttribute("rows", rowDao.getRowsBySheetId(sheetId));
        request.setAttribute("project", projectDao.getProjectById(projectId));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow.jsp");
        dispatcher.forward(request, response);
    }
}
