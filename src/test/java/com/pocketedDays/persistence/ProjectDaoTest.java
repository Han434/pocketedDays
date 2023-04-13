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
 * The type Project dao test.
 */
class ProjectDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;
    GenericDao userDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Project.class);
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
     * Gets all projects success.
     */
    @Test
    void getAllProjectsSuccess() {
        List<Project> projects = (List<Project>) genericDao.getAll();
        assertEquals(1, projects.size());
        Project project = projects.get(0);
        assertEquals("Manlay Website", project.getProjectName());
    }

    /**
     * Gets project by user id success.
     */
    @Test
    void getProjectByUserIdSuccess() {
        List<Project> projects = (List<Project>) genericDao.getByProperty("projectCreatorId", 1);
        assertEquals(1, projects.size());
        Project project = projects.get(0);
        assertEquals("Manlay Website", project.getProjectName());
    }


    /**
     * Gets project by project id success.
     */
    @Test
    void getProjectByProjectIdSuccess() {
        Project project = (Project) genericDao.getById(1);
        assertEquals("Manlay Website", project.getProjectName());
    }

    /**
     * Save or update project success.
     */
    @Test
    void saveOrUpdateProjectSuccess() {
        String newProjectName = "Han";
        Project projectToUpdate = (Project) genericDao.getById(1);
        projectToUpdate.setProjectName(newProjectName);
        genericDao.saveOrUpdateEntity(projectToUpdate);
        Project retrievedProject = (Project) genericDao.getById(1);
        assertEquals(projectToUpdate, retrievedProject);
    }

    /**
     * Insert project success.
     */
    @Test
    void insertProjectSuccess() {
        Project project = new Project(1, "testing", "testing", LocalDate.parse("2018-12-27"), "testing");
        int projectId = genericDao.insertEntity(project);
        assertNotEquals(0, projectId);
        Project projectToTest = (Project) genericDao.getById(projectId);
        assertEquals(project, projectToTest);
    }

    /**
     * Insert project with sheets.
     */
    @Test
    void insertProjectWithSheetsSuccess() {
        Project project = new Project(1, "Name", "123", LocalDate.parse("2018-12-27"), "Description");
        Sheet sheet = new Sheet(project, "Finance department petition", 1, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        project.addSheet(sheet);

        int projectId = genericDao.insertEntity(project);
        assertNotEquals(0, projectId);
        Project projectToTest = (Project) genericDao.getById(projectId);
        assertEquals(projectToTest, project);
        assertEquals(1, projectToTest.getSheets().size());
        //assertEquals(sheet, projectToTest.getSheets().get(0));
    }

    /**
     * Insert project with users success.
     */
    @Test
    void insertProjectWithUsersSuccess() {
        User user = new User("A", "B", "Red12", "Female", "abc@gmail.com", LocalDate.parse("2018-12-27"));
        Project project = new Project(1, "Namea", "123", LocalDate.parse("2018-12-27"), "Description");

        int userId = userDao.insertEntity(user);
        int projectId = genericDao.insertEntity(project);

        User insertedUser = (User) userDao.getById(userId);
        Project insertedProject = (Project) genericDao.getById(projectId);

        insertedProject.addUser(insertedUser);
        genericDao.saveOrUpdateEntity(insertedProject);

        assertNotEquals(0, userId);
        assertEquals(insertedUser, user);
        assertEquals(insertedProject, project);
        assertEquals(1, insertedProject.getUsers().size());
    }

    /**
     * Delete project success.
     */
    @Test
    void deleteProjectSuccess() {
        Project projectToDelete = (Project) genericDao.getById(1);
        Set<Sheet> listOfSheet = projectToDelete.getSheets();
        GenericDao sheetDao = new GenericDao(Sheet.class);
        GenericDao rowDao = new GenericDao(Row.class);
        List<Integer> listOfSheetId = new ArrayList<>();
        List<Sheet> sheet = new ArrayList<>();
        List<List> collectionOfListOfRowId = new ArrayList();
        for (Sheet sheetToDelete : listOfSheet) {
            listOfSheetId.add(sheetToDelete.getSheetId());

            List<Integer> listOfRowId = new ArrayList<>();
            Set<Row> listOfRow = sheetToDelete.getRows();
            for (Row rowToDelete : listOfRow) {
                listOfRowId.add(rowToDelete.getRowId());
            }
            collectionOfListOfRowId.add(listOfRowId);
        }
        genericDao.deleteEntity(projectToDelete);
        assertNull(genericDao.getById(1));
        for (int sheetId : listOfSheetId) {
            sheet.add((Sheet) sheetDao.getById(sheetId));
        }
        for (List<Integer> listOfRowId : collectionOfListOfRowId) {
            List<Row> row = new ArrayList<>();
            for (int rowId : listOfRowId) {
                row.add((Row) rowDao.getById(rowId));
            }
            assertEquals(null, row.get(0));
        }
        assertEquals(null, sheet.get(0));
    }
}