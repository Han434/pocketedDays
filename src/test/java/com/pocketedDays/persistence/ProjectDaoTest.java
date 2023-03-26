package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.Sheet;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Project dao test.
 */
class ProjectDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Project.class);
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
     * Insert sheet with rows success.
     */
    @Test
    void insertSheetWithRowsSuccess() {
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
     * Delete project success.
     */
    @Test
    void deleteProjectSuccess() {
        genericDao.deleteEntity(genericDao.getById(1));
        assertNull(genericDao.getById(1));
    }
}