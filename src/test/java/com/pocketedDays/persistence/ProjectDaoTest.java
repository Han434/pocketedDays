package com.pocketedDays.persistence;

import com.pocketedDays.entity.*;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Project dao test.
 */
class ProjectDaoTest {
    /**
     * The Project dao.
     */
    GenericDao projectDao;
    /**
     * The User dao.
     */
    GenericDao userDao;
    /**
     * The Sheet dao.
     */
    GenericDao sheetDao;
    /**
     * The Row dao.
     */
    GenericDao rowDao;
    /**
     * The User project dao.
     */
    GenericDao userProjectDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        projectDao = new GenericDao(Project.class);
        userDao = new GenericDao(User.class);
        sheetDao = new GenericDao(Sheet.class);
        rowDao = new GenericDao(Row.class);
        userProjectDao = new GenericDao(UserProject.class);
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
        List<Project> projects = (List<Project>) projectDao.getAll();
        assertEquals(1, projects.size());
        Project project = projects.get(0);
        assertEquals("Manlay Website", project.getProjectName());
    }

    /**
     * Gets project by project id success.
     */
    @Test
    void getProjectByProjectIdSuccess() {
        Project project = (Project) projectDao.getById(1);
        assertEquals("Manlay Website", project.getProjectName());
    }

    /**
     * Save or update project success.
     */
    @Test
    void saveOrUpdateProjectSuccess() {
        String newProjectName = "Han";
        Project projectToUpdate = (Project) projectDao.getById(1);
        projectToUpdate.setProjectName(newProjectName);
        projectDao.saveOrUpdateEntity(projectToUpdate);
        Project retrievedProject = (Project) projectDao.getById(1);
        assertEquals(projectToUpdate, retrievedProject);
    }

    /**
     * Insert project success.
     */
    @Test
    void insertProjectSuccess() {
        Project project = new Project("testing", "testing", LocalDate.parse("2018-12-27"), "testing");
        int projectId = projectDao.insertEntity(project);
        assertNotEquals(0, projectId);
        Project projectToTest = (Project) projectDao.getById(projectId);
        assertEquals(project, projectToTest);
    }

    /**
     * Insert project with sheets success.
     */
    @Test
    void insertProjectWithSheetsSuccess() {
        User user = (User) userDao.getById(1);
        Project project = new Project("Name", "123", LocalDate.parse("2018-12-27"), "Description");
        Sheet sheet = new Sheet(project, "Finance department petition", user, LocalDate.parse("2018-12-27"), "TechLand","finance.png", "Not here", "Expense");
        project.addSheet(sheet);

        int projectId = projectDao.insertEntity(project);
        assertNotEquals(0, projectId);
        Project projectToTest = (Project) projectDao.getById(projectId);
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
        Project project = new Project("Namea", "123", LocalDate.parse("2018-12-27"), "Description");

        int userId = userDao.insertEntity(user);
        int projectId = projectDao.insertEntity(project);

        User insertedUser = (User) userDao.getById(userId);
        Project insertedProject = (Project) projectDao.getById(projectId);

        insertedProject.addUser(insertedUser);
        projectDao.saveOrUpdateEntity(insertedProject);

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
        Project projectToDelete = (Project) projectDao.getById(1);
        List<UserProject> userProjectList = userProjectDao.findByPropertyEqual("project", projectToDelete);
        for (UserProject userProject : userProjectList) {
            userProjectDao.deleteEntity(userProject);
        }
        Set<Sheet> listOfSheet = projectToDelete.getSheets();
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
        projectDao.deleteEntity(projectToDelete);
        assertNull(projectDao.getById(1));
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

    @Test
    void calculateTotalRevenueSuccess() {
        Project project = (Project) projectDao.getById(1);
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", "Revenue");
        propertyMap.put("project", project);

        List<Sheet> sheets = (List<Sheet>) sheetDao.findByPropertyEqual(propertyMap);
        int expectedTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculateTotal();
            expectedTotal += sheetTotal;
        }
        int retrivedTotal = project.calculateTotalRevenue();
        assertEquals(expectedTotal, retrivedTotal);
    }

    @Test
    void calculateTotalExpenseSuccess() {
        Project project = (Project) projectDao.getById(1);
        GenericDao sheetDao = new GenericDao(Sheet.class);
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("sheetType", "Expense");
        propertyMap.put("project", project);

        List<Sheet> sheets = (List<Sheet>) sheetDao.findByPropertyEqual(propertyMap);
        int expectedTotal = 0;
        for (Sheet sheet : sheets) {
            int sheetTotal = sheet.calculateTotal();
            expectedTotal += sheetTotal;
        }
        int retrivedTotal = project.calculateTotalExpense();
        assertEquals(expectedTotal, retrivedTotal);
    }
}