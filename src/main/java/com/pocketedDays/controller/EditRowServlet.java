package com.pocketedDays.controller;

import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.User;
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
        //Get value of submit and rowId
        String submit = request.getParameter("submit");
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Get session variable sheetId
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");
        String userType = (String) session.getAttribute("userType");

        //If not equal to "Edit Row"
        if (!submit.equals("Edit") && (!(userType.equals("visitor")))) {
            //Get row by id
            GenericDao rowDao = new GenericDao(Row.class);
            Row row = (Row) rowDao.getById(rowId);

            //Set attributes of row and sheetId
            request.setAttribute("row", row);
            request.setAttribute("sheetId", sheetId);

            //Forward to editRow.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editRow.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/unauthorized.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get submit and rowId form the request
        String submit = request.getParameter("submit");
        int rowId = Integer.parseInt(request.getParameter("rowId"));

        //Get session variable sheetId and user
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");
        User user = (User) session.getAttribute("user");


        //If equal to "Edit Row"
        if (submit.equals("Edit")) {
            //Get row by rowId
            GenericDao rowDao = new GenericDao(Row.class);
            Row row = (Row) rowDao.getById(rowId);

            //Update data
            row.setUser(user);
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
