package com.pocketedDays.controller;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.entity.User;
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
        fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50
)   // 50MB
public class CreateSheetServlet extends HttpServlet {
    private static final String SAVE_DIR = "uploadFiles";
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");
        if (!(userType.equals("visitor"))) {
            //Forward to createSheet.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/createSheet.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/unauthorized.jsp");
            dispatcher.forward(request, response);
        }
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get session variable projectId, sheetCreatorId, sheetType
        HttpSession session = request.getSession();
        int projectId = (int) session.getAttribute("projectId");
        User user = (User) session.getAttribute("user");
        String sheetType = (String) session.getAttribute("sheetType");


        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            try {
                part.write(savePath + File.separator + fileName);
            } catch (Exception exception) {
                logger.error("Error writing file: " + exception.getMessage(), exception);
            }
        }

        //Set data
        String sheetDescription = request.getParameter("sheetDescription");
        LocalDate createdDate = LocalDate.now();
        String organization = request.getParameter("organization");
        String filePath = request.getParameter("filePath");
        String note = request.getParameter("note");

        //Create new sheet
        GenericDao projectDao = new GenericDao(Project.class);
        Project project = (Project) projectDao.getById(projectId);
        Sheet sheet = new Sheet(project, sheetDescription, user, createdDate, organization, filePath, note, sheetType);

        //Insert sheet into the database
        GenericDao sheetDao = new GenericDao(Sheet.class);
        sheetDao.insertEntity(sheet);

        //Forward to sheet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sheet");
        dispatcher.forward(request, response);
    }
}