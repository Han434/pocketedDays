package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Sheet dao test.
 */
class SheetDaoTest {

    /**
     * The Dao.
     */
    SheetDao dao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new SheetDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets all sheets.
     */
    @Test
    void getAllSheetsSuccess() {
        List<Sheet> sheets = dao.getAllSheets();
        assertEquals(1, sheets.size());
    }

    /**
     * Gets sheet by id.
     */
    @Test
    void getSheetBySheetIdSuccess() {
        Sheet sheet = dao.getSheetBySheetId(1);
        assertEquals("Installing computer for ABC department", sheet.getSheetDescription());
    }

    /**
     * Insert sheet.
     */
    @Test
    void insertSheetSuccess() {
        ProjectDao projectDao = new ProjectDao();
        Project project = projectDao.getProjectByProjectId(1);
        Sheet sheet = new Sheet(project, "Finance department petition", 1, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        int sheetId = dao.insertSheet(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = dao.getSheetBySheetId(sheetId);
        assertEquals("Finance department petition", sheetToTest.getSheetDescription());
    }


    @Test
    void insertSheetWithRowsSuccess() {
        ProjectDao projectDao = new ProjectDao();
        Project project = projectDao.getProjectByProjectId(1);
        Sheet sheet = new Sheet(project, "Finance department petition", 1, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        Row row = new Row(sheet, 1, LocalDate.parse("2018-12-22"), "abc", 1, 200, "Expense", "tag");

        sheet.addRow(row);

        int sheetId = dao.insertSheet(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = dao.getSheetBySheetId(sheetId);
        assertEquals("Finance department petition", sheetToTest.getSheetDescription());
        assertEquals(1, sheetToTest.getRows().size());
    }


    /**
     * Delete sheet.
     */
    @Test
    void deleteSheetSuccess() {
        dao.deleteSheet(dao.getSheetBySheetId(1));
        assertNull(dao.getSheetBySheetId(1));
    }
}