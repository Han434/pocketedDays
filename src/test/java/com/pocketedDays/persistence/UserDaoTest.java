package com.pocketedDays.persistence;

import com.pocketedDays.entity.Project;
import com.pocketedDays.entity.User;
import com.pocketedDays.test.utilities.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type User dao test.
 */
class UserDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao genericDao;
    /**
     * The Project dao.
     */
    GenericDao projectDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(User.class);
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
     * Gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = (List<User>) genericDao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("White", user.getUserName());
    }

    /**
     * Gets user by user id success.
     */
    @Test
    void getUserByUserIdSuccess() {
        User user = (User) genericDao.getById(1);
        assertEquals("White", user.getUserName());
    }

    /**
     * Save or update user success.
     */
    @Test
    void saveOrUpdateUserSuccess() {
        String newUserName = "Han";
        User userToUpdate = (User) genericDao.getById(1);
        userToUpdate.setUserName(newUserName);
        genericDao.saveOrUpdateEntity(userToUpdate);
        User retrievedUser = (User) genericDao.getById(1);
        assertEquals(userToUpdate, retrievedUser);
    }

    /**
     * Insert user success.
     */
    @Test
    void insertUserSuccess() {
        User user = new User("A", "B", "Red", "Female", "abc@gmail.com", LocalDate.parse("2018-12-27"));
        Project project = new Project(2, "testing1", "testing1", LocalDate.parse("2018-12-27"), "testing");

        int userId = genericDao.insertEntity(user);
        assertNotEquals(0, userId);
        User userToTest = (User) genericDao.getById(userId);
        assertEquals(user, userToTest);
    }

    /**
     * Delete project success.
     */
    @Test
    void deleteProjectSuccess() {
        User userToDelete = (User) genericDao.getById(1);
        genericDao.deleteEntity(userToDelete);
        assertNull(genericDao.getById(1));
    }
}