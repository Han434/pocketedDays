package com.pocketedDays.controller;

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
        urlPatterns = {"/sheet"}
)
public class SheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SheetDao sheetDao = new SheetDao();

        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");

        String sheetType = request.getParameter("sheetType");
        session.setAttribute("sheetType", sheetType);

        //request.setAttribute("sheets", sheetDao.getAllSheets());
        request.setAttribute("sheets", sheetDao.getSheetsByProjectId(projectId));
        request.setAttribute("projectId", projectId);
        request.setAttribute("sheetType", "Revenue");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/sheetView.jsp");
        dispatcher.forward(request, response);
    }
}
