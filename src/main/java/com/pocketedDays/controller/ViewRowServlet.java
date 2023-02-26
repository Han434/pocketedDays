package com.pocketedDays.controller;

import com.pocketedDays.persistence.RowDao;
import com.pocketedDays.persistence.SheetDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (
        urlPatterns = {"/viewRow"}
)
public class ViewRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RowDao rowDao = new RowDao();
        request.setAttribute("rows", rowDao.getAllRows());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/rowView.jsp");
        dispatcher.forward(request, response);
    }
}
