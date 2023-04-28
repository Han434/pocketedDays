package com.pocketedDays.persistence;

import com.pocketedDays.entity.Row;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.entity.User;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Row dao test.
 */
class RowDaoTest {
    /**
     * The Row dao.
     */
    GenericDao rowDao;
    /**
     * The Sheet dao.
     */
    GenericDao sheetDao;
    /**
     * The User dao.
     */
    GenericDao userDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        rowDao = new GenericDao(Row.class);
        sheetDao = new GenericDao(Sheet.class);
        userDao = new GenericDao(User.class);
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
     * Gets all rows success.
     */
    @Test
    void getAllRowsSuccess() {
        List<Row> rows = rowDao.getAll();
        assertEquals(1, rows.size());
        Row row = rows.get(0);
        assertEquals("Socket", row.getRowDescription());
    }

    /**
     * Gets project by row id success.
     */
    @Test
    void getProjectByRowIdSuccess() {
        Row row = (Row) rowDao.getById(1);
        assertEquals("Socket", row.getRowDescription());
    }

    /**
     * Insert row success.
     */
    @Test
    void insertRowSuccess() {
        Sheet sheet = (Sheet) sheetDao.getById(1);
        User user = (User) userDao.getById(1);
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-27"), "Keyword", 5,200, "Product", "Finanace");
        sheet.addRow(row);

        int rowId = rowDao.insertEntity(row);
        assertNotEquals(0, rowId);

        Row rowToTest = (Row) rowDao.getById(rowId);
        assertEquals(rowToTest, row);
        assertEquals(sheet, row.getSheet());
    }

    /**
     * Insert sheet with users success.
     */
    @Test
    void insertSheetWithUsersSuccess() {
        User user = (User) userDao.getById(1);
        Sheet sheet = (Sheet) sheetDao.getById(1);
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-27"), "Keyword", 5,200, "Product", "Finanace");
        user.addRow(row);
        int rowId = rowDao.insertEntity(row);
        assertNotEquals(0, rowId);
        Row rowToTest = (Row) rowDao.getById(rowId);
        assertEquals(rowToTest, row);
        assertEquals(user, rowToTest.getUser());
    }

    /**
     * Save or update row success.
     */
    @Test
    void saveOrUpdateRowSuccess() {
        String newRowDescription = "Cable";
        Row rowToUpdate = (Row) rowDao.getById(1);
        rowToUpdate.setRowDescription(newRowDescription);
        rowDao.saveOrUpdateEntity(rowToUpdate);
        Row retrievedProject = (Row) rowDao.getById(1);
        assertEquals(rowToUpdate, retrievedProject);
    }


    /**
     * Delete row success.
     */
    @Test
    void deleteRowSuccess() {
        Row rowToDelete = (Row) rowDao.getById(1);
        Sheet sheet = rowToDelete.getSheet();
        int sheetId = sheet.getSheetId();
        rowDao.deleteEntity(rowToDelete);
        assertNull(rowDao.getById(1));
        assertNotNull(sheetDao.getById(sheetId));
    }
}