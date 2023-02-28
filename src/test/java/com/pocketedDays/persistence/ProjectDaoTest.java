package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {

    ProjectDao dao;
    @BeforeEach
    void setUp() {
        dao = new ProjectDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @AfterEach
    void tearDown() {
        //Database database = Database.getInstance();
        //database.runSQL("install.sql");
    }

    @Test
    void getAllProjects() {
        List<Project> projects = dao.getAllProjects();
        assertEquals(1, projects.size());
    }


    @Test
    void getProjectById() {
        Project project = dao.getProjectById(1);
        assertEquals("Manlay Website", project.getProjectName());
    }

    @Test
    void saveOrUpdateProject() {
        String newProjectName = "Han";
        Project projectToUpdate = dao.getProjectById(1);
        projectToUpdate.setProjectName(newProjectName);
        dao.saveOrUpdateProject(projectToUpdate);
        Project retrievedProject = dao.getProjectById(1);
        assertEquals(newProjectName, retrievedProject.getProjectName());
    }

    @Test
    void insertProject() {
        Project project = new Project(1, "testing", "testing", LocalDate.parse("2018-12-27"), "testing");
        int projectId = dao.insertProject(project);
        assertNotEquals(0, projectId);
        Project projectToTest = dao.getProjectById(projectId);
        assertEquals("testing", projectToTest.getProjectName());
    }

    @Test
    void deleteProject() {
        dao.deleteProject(dao.getProjectById(1));
        assertNull(dao.getProjectById(1));
    }
}