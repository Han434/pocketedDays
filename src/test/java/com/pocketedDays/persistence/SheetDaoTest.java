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
        GenericDao userDao = new GenericDao(User.class);
        User user = (User) userDao.getById(1);        GenericDao projectDao = new GenericDao(Project.class);
        Project project = (Project) projectDao.getById(1);
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
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
        GenericDao projectDao = new GenericDao(Project.class);
        GenericDao userDao = new GenericDao(User.class);
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-22"), "abc", 1, 200, "Expense", "tag");
        sheet.addRow(row);

        int sheetId = genericDao.insertEntity(sheet);
        assertNotEquals(0, sheetId);
        Sheet sheetToTest = (Sheet) genericDao.getById(sheetId);
        assertEquals(sheetToTest, sheet);
        assertEquals(1, sheetToTest.getRows().size());
        //assertEquals(row, sheetToTest.getRows().get(0));
    }


    /**
     * Delete sheet.
     */
    @Test
    void deleteSheetSuccess() {
        Sheet sheetToDelete = (Sheet) genericDao.getById(1);
        Set<Row> listOfRow = sheetToDelete.getRows();
        GenericDao rowDao = new GenericDao(Row.class);
        List<Integer> listOfRowId = new ArrayList<>();
        List<Row> row = new ArrayList<>();
        for (Row rowToDelete : listOfRow) {
            listOfRowId.add(rowToDelete.getRowId());
        }
        genericDao.deleteEntity(sheetToDelete);
        assertNull(genericDao.getById(1));
        for (int rowId : listOfRowId) {
            row.add((Row) rowDao.getById(rowId));
        }
        assertEquals(null, row.get(0));
    }
}