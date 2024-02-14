package ru.ykul.dao;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ykul.config.DBConnection;
import ru.ykul.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ykul.config.DBConnection.getConnection;

public class UsersDaoTest {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    @BeforeEach
    public void setUp() {
        DBConnection.setConnectionParam(URL, USERNAME, PASSWORD);

        try(Connection connection = getConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (UsersDaoTest.class.getResourceAsStream("/script_exists_table.sql")));
            RunScript.execute(connection, reader);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        try(Connection connection = getConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (UsersDaoTest.class.getResourceAsStream("/script_drop_table.sql")));
            RunScript.execute(connection, reader);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getUserByUuid_ifUserFound_returnOptionalUser() {

        UsersDao usersDao = new UsersDao();
        UUID uuid = UUID.fromString("d67d7d36-dc3f-4425-a9dc-172e13ce84e4");
        Optional<User> userOptional = usersDao.getUserByUuid(uuid);

        assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        assertEquals("Taylor", user.getSurname());
        assertEquals("John", user.getName());
        assertEquals("Smith", user.getMiddleName());
    }

    @Test
    public void getUserByUuid_ifUserNotFound_returnEmptyOptional() {

        UsersDao usersDao = new UsersDao();
        UUID uuid = UUID.fromString("d67d7d36-dc3f-4425-a9dc-172e13ce84e5");
        Optional<User> userOptional = usersDao.getUserByUuid(uuid);

        assertTrue(userOptional.isEmpty());
    }

    @Test
    public void getUserByUuid_ifUuidNull_throwIllegalArgumentException() {

        UsersDao usersDao = new UsersDao();

        assertThrows(IllegalArgumentException.class, () ->
                usersDao.getUserByUuid(null));
    }
}
