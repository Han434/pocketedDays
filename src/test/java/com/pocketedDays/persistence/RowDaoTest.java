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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Row dao test.
 */
class RowDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Row.class);
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
        List<Row> rows = genericDao.getAll();
        assertEquals(1, rows.size());
        Row row = rows.get(0);
        assertEquals("Socket", row.getRowDescription());
    }

    /**
     * Gets project by row id success.
     */
    @Test
    void getProjectByRowIdSuccess() {
        Row row = (Row) genericDao.getById(1);
        assertEquals("Socket", row.getRowDescription());
    }

    /**
     * Insert row success.
     */
    @Test
    void insertRowSuccess() {
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Sheet sheet = (Sheet) sheetDao.getById(1);
        GenericDao userDao = new GenericDao(User.class);
        User user = (User) userDao.getById(1);
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-27"), "Keyword", 5,200, "Product", "Finanace");
        sheet.addRow(row);

        int rowId = genericDao.insertEntity(row);
        assertNotEquals(0, rowId);

        Row rowToTest = (Row) genericDao.getById(rowId);
        assertEquals(rowToTest, row);
        assertEquals(sheet, row.getSheet());
    }

    @Test
    void insertSheetWithUsersSuccess() {
        GenericDao sheetDao = new GenericDao(Sheet.class);
        GenericDao userDao = new GenericDao(User.class);
        User user = (User) userDao.getById(1);
        Sheet sheet = (Sheet) sheetDao.getById(1);
        Row row = new Row(sheet, user, LocalDate.parse("2018-12-27"), "Keyword", 5,200, "Product", "Finanace");
        user.addRow(row);
        int rowId = genericDao.insertEntity(row);
        assertNotEquals(0, rowId);
        Row rowToTest = (Row) genericDao.getById(rowId);
        assertEquals(rowToTest, row);
        assertEquals(user, rowToTest.getUser());
    }

    /**
     * Save or update row success.
     */
    @Test
    void saveOrUpdateRowSuccess() {
        String newRowDescription = "Cable";
        Row rowToUpdate = (Row) genericDao.getById(1);
        rowToUpdate.setRowDescription(newRowDescription);
        genericDao.saveOrUpdateEntity(rowToUpdate);
        Row retrievedProject = (Row) genericDao.getById(1);
        assertEquals(rowToUpdate, retrievedProject);
    }


    /**
     * Delete row success.
     */
    @Test
    void deleteRowSuccess() {
        genericDao.deleteEntity(genericDao.getById(1));
        assertNull(genericDao.getById(1));
    }
}