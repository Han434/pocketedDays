package com.pocketedDays.controller;

import com.pocketedDays.entity.Row;
import com.pocketedDays.persistence.RowDao;

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
        int rowId = Integer.parseInt(request.getParameter("rowId"));
        HttpSession session = request.getSession();
        int sheetId = (int) session.getAttribute("sheetId");
        int rowCreatorId = (int) session.getAttribute("userId");
        if (submit.equals("Edit Row")) {
            RowDao rowDao = new RowDao();
            Row row = rowDao.getRowsByRowId(rowId);
            row.setSheetId(sheetId);
            row.setRowCreatorId(rowCreatorId);
            row.setCreatedDate(LocalDate.now());
            row.setRowDescription(request.getParameter("rowDescription"));
            //row.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            row.setCostPerItem(Integer.parseInt(request.getParameter("costPerItem")));
            row.setRowType(request.getParameter("rowType"));
            row.setTag(request.getParameter("tag"));

            rowDao.saveOrUpdateRow(row);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewRow?sheetId=" + sheetId);
            dispatcher.forward(request, response);
        } else {
            RowDao rowDao = new RowDao();
            Row row = rowDao.getRowsByRowId(rowId);
            request.setAttribute("row", row);
            request.setAttribute("sheetId", sheetId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/editRow.jsp");
            dispatcher.forward(request, response);
        }
    }
}
