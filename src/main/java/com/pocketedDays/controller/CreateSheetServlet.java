package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
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

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Forward to createSheet.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createSheet.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable projectId, sheetCreatorId, sheetType
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        int sheetCreatorId = (int) session.getAttribute("userId");
        String sheetType = (String) session.getAttribute("sheetType");

        Part filePart = request.getPart("filePath");
        String fileName = filePart.getSubmittedFileName();
        for (Part part : request.getParts()) {
            try {
                part.write("C:\\upload\\" + fileName);
            } catch (IOException exception) {
                logger.error("Cannot load the file", exception);
            } catch (Exception exception) {
                logger.error("Cannot load the file", exception);
            }
        }

        //Set data
        String sheetDescription = request.getParameter("sheetDescription");
        LocalDate createdDate = LocalDate.now();
        String organization = request.getParameter("organization");
        String filePath = fileName;
        String note = request.getParameter("note");

        //Create new sheet
        GenericDao projectDao = new GenericDao(Project.class);
        Project project = (Project) projectDao.getById(projectId);
        Sheet sheet = new Sheet(project, sheetDescription, sheetCreatorId, createdDate, organization, filePath, note, sheetType);

        //Insert sheet into the database
        GenericDao sheetDao = new GenericDao(Sheet.class);
        sheetDao.insertEntity(sheet);

        //Forward to sheet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sheet");
        dispatcher.forward(request, response);
    }
}