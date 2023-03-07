package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
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
     * The Generic dao.
     */
    GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Sheet.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
    }


    /**
     * Gets all sheets.
     */
    @Test
    void getAllSheetsSuccess() {
        List<Sheet> sheets = genericDao.getAll();
        assertEquals(1, sheets.size());
        Sheet sheet = sheets.get(0);
        assertEquals("Installing computer for ABC department", sheet.getSheetDescription());
    }

    /**
     * Gets sheet by id.
     */
    @Test
    void getSheetBySheetIdSuccess() {
        Sheet sheet = (Sheet) genericDao.getById(1);
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
        project.addSheet(sheet);

        int sheetId = genericDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);

        Sheet sheetToTest = (Sheet) genericDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(project, sheet.getProject());
    }


    /**
     * Insert sheet with rows success.
     */
    @Test
    void insertSheetWithRowsSuccess() {
        ProjectDao projectDao = new ProjectDao();
        Project project = projectDao.getProjectByProjectId(1);
        Sheet sheet = new Sheet(project, "Finance department petition", 1, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        Row row = new Row(sheet, 1, LocalDate.parse("2018-12-22"), "abc", 1, 200, "Expense", "tag");
        sheet.addRow(row);

        int sheetId = genericDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = (Sheet) genericDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(1, sheetToTest.getRows().size());
        assertEquals(row, sheetToTest.getRows().get(0));
    }


    /**
     * Delete sheet.
     */
    @Test
    void deleteSheetSuccess() {
        genericDao.deleteEntity(genericDao.getById(1));
        assertNull(genericDao.getById(1));
    }
}