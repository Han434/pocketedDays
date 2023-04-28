package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.entity.User;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Sheet dao test.
 */
class SheetDaoTest {

    /**
     * The Sheet dao.
     */
    GenericDao sheetDao;
    /**
     * The User dao.
     */
    GenericDao userDao;
    /**
     * The Project dao.
     */
    GenericDao projectDao;
    /**
     * The Row dao.
     */
    GenericDao rowDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        sheetDao = new GenericDao(Sheet.class);
        projectDao = new GenericDao(Project.class);
        rowDao = new GenericDao(Row.class);
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
     * Gets all sheets success.
     */
    @Test
    void getAllSheetsSuccess() {
        List<Sheet> sheets = sheetDao.getAll();
        assertEquals(1, sheets.size());
        Sheet sheet = sheets.get(0);
        assertEquals("Installing computer for ABC department", sheet.getSheetDescription());
    }

    /**
     * Gets sheet by sheet id success.
     */
    @Test
    void getSheetBySheetIdSuccess() {
        Sheet sheet = (Sheet) sheetDao.getById(1);
        assertEquals("Installing computer for ABC department", sheet.getSheetDescription());
    }

    /**
     * Insert sheet success.
     */
    @Test
    void insertSheetSuccess() {
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        project.addSheet(sheet);

        int sheetId = sheetDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);

        Sheet sheetToTest = (Sheet) sheetDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(project, sheet.getProject());
    }


    /**
     * Insert sheet with rows success.
     */
    @Test
    void insertSheetWithRowsSuccess() {
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-22"), "abc", 1, 200, "Expense", "tag");
        sheet.addRow(row);

        int sheetId = sheetDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = (Sheet) sheetDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(1, sheetToTest.getRows().size());
        //assertEquals(row, sheetToTest.getRows().get(0));
    }

    /**
     * Insert sheet with users success.
     */
    @Test
    void insertSheetWithUsersSuccess() {
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        user.addSheet(sheet);
        int sheetId = sheetDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = (Sheet) sheetDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(user, sheetToTest.getUser());
    }


    /**
     * Delete sheet success.
     */
    @Test
    void deleteSheetSuccess() {
        Sheet sheetToDelete = (Sheet) sheetDao.getById(1);
        Project project = sheetToDelete.getProject();
        int projectId = project.getProjectId();
        Set<Row> listOfRow = sheetToDelete.getRows();
        List<Integer> listOfRowId = new ArrayList<>();
        List<Row> row = new ArrayList<>();
        for (Row rowToDelete : listOfRow) {
            listOfRowId.add(rowToDelete.getRowId());
        }
        sheetDao.deleteEntity(sheetToDelete);
        assertNull(sheetDao.getById(1));
        for (int rowId : listOfRowId) {
            row.add((Row) rowDao.getById(rowId));
        }
        assertEquals(null, row.get(0));
        assertNotNull(projectDao.getById(projectId));
    }
}