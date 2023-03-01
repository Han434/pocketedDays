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
 * The type Row dao test.
 */
class RowDaoTest {
    /**
     * The Dao.
     */
    RowDao dao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new RowDao();
        Database database = Database.getInstance();
        database.runSQL("rowClean.sql");
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
        List<Row> rows = dao.getAllRows();
        assertEquals(1, rows.size());
    }

    /**
     * Gets rows by sheet id success.
     */
    @Test
    void getRowsBySheetIdSuccess() {
        List<Row> rows = dao.getRowsBySheetId(1);
        Row row = rows.get(0);
        assertEquals("Socket", row.getRowDescription());
    }

    /**
     * Gets project by row id success.
     */
    @Test
    void getProjectByRowIdSuccess() {
        Row row = dao.getRowsByRowId(1);
        assertEquals("Socket", row.getRowDescription());
    }

    @Test
    void saveOrUpdateRowSuccess() {
        String newRowDescription = "Cable";
        Row rowToUpdate = dao.getRowsByRowId(1);
        rowToUpdate.setRowDescription(newRowDescription);
        dao.saveOrUpdateRow(rowToUpdate);
        Row retrievedProject = dao.getRowsByRowId(1);
        assertEquals(newRowDescription, retrievedProject.getRowDescription());
    }

    /**
     * Insert row success.
     */
    @Test
    void insertRowSuccess() {
        Row row = new Row(1, 1, LocalDate.parse("2018-12-27"), "Keyword", 5,200, "Product", "Finanace");
        int rowId = dao.insertRow(row);
        assertNotEquals(0, rowId);
        Row rowToTest = dao.getRowsByRowId(rowId);
        assertEquals("Keyword", rowToTest.getRowDescription());
    }

    /**
     * Delete row success.
     */
    @Test
    void deleteRowSuccess() {
        dao.deleteRow(dao.getRowsByRowId(1));
        assertNull(dao.getRowsByRowId(1));
    }
}