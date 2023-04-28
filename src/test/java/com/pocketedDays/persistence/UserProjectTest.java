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

/**
 * The type User project test.
 */
public class UserProjectTest {
    /**
     * The UserProject dao.
     */
    GenericDao userProjectDao;

    /**
     * The User dao.
     */
    GenericDao userDao;
    /**
     * The Project dao.
     */
    GenericDao projectDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userProjectDao = new GenericDao(UserProject.class);
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

    /**
     * Gets all user projects success.
     */
    @Test
    void getAllUserProjectsSuccess() {
        List<UserProject> userProjectList = (List<UserProject>) userProjectDao.getAll();
        assertEquals(1, userProjectList.size());
        UserProject userProject = userProjectList.get(0);
        User user = (User) userDao.getById(1);
        assertEquals(user, userProject.getUser());
    }

    /**
     * Gets user by user project id success.
     */
    @Test
    void getUserByUserProjectIdSuccess() {
        UserProject userProject = (UserProject) userProjectDao.getById(1);
        User user = (User) userDao.getById(1);
        assertEquals(user, userProject.getUser());
    }

    /**
     * Insert user project success.
     */
    @Test
    void insertUserProjectSuccess() {
        User user = (User) userDao.getById(1);
        Project project = (Project) projectDao.getById(1);

        UserProject userProject = new UserProject(user, project, "creator", LocalDate.parse("2018-12-27"));
        int userProjectId = userProjectDao.insertEntity(userProject);
        assertNotEquals(0, userProjectId);
        UserProject userProjectToTest = (UserProject) userProjectDao.getById(userProjectId);
        assertEquals(userProjectToTest, userProject);
    }

    /**
     * Delete user success.
     */
    @Test
    void deleteUserSuccess() {
        UserProject userProjectToDelete = (UserProject) userProjectDao.getById(1);
        User user = userProjectToDelete.getUser();
        int userId = user.getUserId();
        Project project = userProjectToDelete.getProject();
        int projectId = project.getProjectId();
        userProjectDao.deleteEntity(userProjectToDelete);
        assertNull(userProjectDao.getById(1));
        assertNotNull(userDao.getById(userId));
        assertNotNull(projectDao.getById(projectId));
    }
}
