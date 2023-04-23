package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.User;
import com.pocketedDays.entity.UserProject;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserProjectTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;

    GenericDao userDao;
    GenericDao projectDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(UserProject.class);
        userDao = new GenericDao(User.class);
        projectDao = new GenericDao(Project.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUserProjectsSuccess() {
        List<UserProject> userProjectList = (List<UserProject>) genericDao.getAll();
        assertEquals(1, userProjectList.size());
        UserProject userProject = userProjectList.get(0);
        User user = (User) userDao.getById(1);
        assertEquals(user, userProject.getUser());
    }

    @Test
    void getUserByUserProjectIdSuccess() {
        UserProject userProject = (UserProject) genericDao.getById(1);
        User user = (User) userDao.getById(1);
        assertEquals(user, userProject.getUser());
    }

    @Test
    void insertUserProjectSuccess() {
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);

        UserProject userProject = new UserProject(user, project, "creator", LocalDate.parse("2018-12-27"));
        int userProjectId = genericDao.insertEntity(userProject);
        assertNotEquals(0, userProjectId);
        UserProject userProjectToTest = (UserProject) genericDao.getById(userProjectId);
        assertEquals(userProjectToTest, userProject);
    }

    @Test
    void deleteUserSuccess() {
        UserProject userProjectToDelete = (UserProject) genericDao.getById(1);
        genericDao.deleteEntity(userProjectToDelete);
        assertNull(genericDao.getById(1));
    }
}
