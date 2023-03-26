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
import java.time.LocalDate;

/**
 * The type Edit row servlet.
 */
@WebServlet (
        urlPatterns = {"/editRow"}
)
public class EditRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");

        //Get rowId from the request
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Get session variable sheetId
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");

        //If not equal to "Edit Row"
        if (!submit.equals("Edit")) {
            GenericDao rowDao = new GenericDao(Row.class);
            Row row = (Row) rowDao.getById(rowId);
            request.setAttribute("row", row);
            request.setAttribute("sheetId", sheetId);

            //Forward to editRow.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editRow.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");

        //Get rowId from the request
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Get session variable sheetId and rowCreatorId
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");
        int rowCreatorId = (int) session.getAttribute("userId");

        //If equal to "Edit Row"
        if (submit.equals("Edit")) {
            //Get row by rowId
            GenericDao rowDao = new GenericDao(Row.class);
            Row row = (Row) rowDao.getById(rowId);

            //Update data
            row.setRowCreatorId(rowCreatorId);
            row.setUpdatedDate(LocalDate.now());
            row.setRowDescription(request.getParameter("rowDescription"));
            row.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            row.setCostPerItem(Integer.parseInt(request.getParameter("costPerItem")));
            row.setRowType(request.getParameter("rowType"));
            row.setTag(request.getParameter("tag"));

            //Update row to the database
            rowDao.saveOrUpdateEntity(row);

            //Forwards to viewRow
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
            dispatcher.forward(request, response);
        }
    }
}
