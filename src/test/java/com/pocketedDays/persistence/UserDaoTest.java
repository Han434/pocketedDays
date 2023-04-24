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


class UserDaoTest {
    GenericDao userDao;
    GenericDao projectDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        projectDao = new GenericDao(Project.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsersSuccess() {
        List<User> users = (List<User>) userDao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("White", user.getUserName());
    }

    @Test
    void getUserByUserIdSuccess() {
        User user = (User) userDao.getById(1);
        assertEquals("White", user.getUserName());
    }

    @Test
    void saveOrUpdateUserSuccess() {
        String newUserName = "Han";
        User userToUpdate = (User) userDao.getById(1);
        userToUpdate.setUserName(newUserName);
        userDao.saveOrUpdateEntity(userToUpdate);
        User retrievedUser = (User) userDao.getById(1);
        assertEquals(userToUpdate, retrievedUser);
    }

    @Test
    void insertUserSuccess() {
        User user = new User("A", "B", "Red", "Female", "abc@gmail.com", LocalDate.parse("2018-12-27"));
        Project project = new Project("testing1", "testing1", LocalDate.parse("2018-12-27"), "testing");

        int userId = userDao.insertEntity(user);
        assertNotEquals(0, userId);
        User userToTest = (User) userDao.getById(userId);
        assertEquals(user, userToTest);
    }
}