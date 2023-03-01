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
    }

    @Test
    void getAllProjectsSuccess() {
        List<Project> projects = dao.getAllProjects();
        assertEquals(1, projects.size());
    }

    @Test
    void getProjectByUserIdSuccess() {
        List<Project> projects = dao.getProjectByUserId(1);
        assertEquals(1, projects.size());
        Project project = projects.get(0);
        assertEquals("Manlay Website", project.getProjectName());
    }


    @Test
    void getProjectByProjectIdSuccess() {
        Project project = dao.getProjectByProjectId(1);
        assertEquals("Manlay Website", project.getProjectName());
    }

    @Test
    void saveOrUpdateProjectSuccess() {
        String newProjectName = "Han";
        Project projectToUpdate = dao.getProjectByProjectId(1);
        projectToUpdate.setProjectName(newProjectName);
        dao.saveOrUpdateProject(projectToUpdate);
        Project retrievedProject = dao.getProjectByProjectId(1);
        assertEquals(newProjectName, retrievedProject.getProjectName());
    }

    @Test
    void insertProjectSuccess() {
        Project project = new Project(1, "testing", "testing", LocalDate.parse("2018-12-27"), "testing");
        int projectId = dao.insertProject(project);
        assertNotEquals(0, projectId);
        Project projectToTest = dao.getProjectByProjectId(projectId);
        assertEquals("testing", projectToTest.getProjectName());
    }

    @Test
    void deleteProjectSuccess() {
        dao.deleteProject(dao.getProjectByProjectId(1));
        assertNull(dao.getProjectByProjectId(1));
    }
}