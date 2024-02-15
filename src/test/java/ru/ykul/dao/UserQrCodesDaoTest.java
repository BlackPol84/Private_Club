package ru.ykul.dao;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ykul.config.DBConnection;
import ru.ykul.model.User;
import ru.ykul.model.UserQrCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.ykul.config.DBConnection.getConnection;

public class UserQrCodesDaoTest {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    @BeforeEach
    public void setUp() {
        DBConnection.setConnectionParam(URL, USERNAME, PASSWORD);

        try(Connection connection = getConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (UserQrCodesDaoTest.class.getResourceAsStream("/script_exists_table.sql")));
            RunScript.execute(connection, reader);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        try(Connection connection = getConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (UserQrCodesDaoTest.class.getResourceAsStream("/script_drop_table.sql")));
            RunScript.execute(connection, reader);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateByUserId_ifUserExists_uuidUpdate() {

        UUID uuid = UUID.fromString("d67d7d36-dc3f-4425-a9dc-172e13ce84e4");
        UsersDao usersDao = new UsersDao();
        Optional<User> userOptionalBefore = usersDao.getUserByUuid(uuid);
        User user = userOptionalBefore.get();
        UserQrCodesDao userQrCodesDao = new UserQrCodesDao();
        userQrCodesDao.updateByUserId(user.getId());
        Optional<User> userOptionalAfter = usersDao.getUserByUuid(uuid);

        assertTrue(userOptionalAfter.isEmpty());
    }

    @Test
    public void updateByUserId_ifUserNotExists_uuidNotUpdate() {

        int minValue = 1;
        int maxValue = 1000;
        int randomValue = minValue + (int) (Math.random() * (maxValue - minValue + 1));

        UserQrCodesDao userQrCodesDao = new UserQrCodesDao();
        userQrCodesDao.updateByUserId(randomValue);
        Optional<UserQrCode> userQrCodeOptional = userQrCodesDao.getById(randomValue);

        assertTrue(userQrCodeOptional.isEmpty());

        UUID uuid = UUID.fromString("d67d7d36-dc3f-4425-a9dc-172e13ce84e4");
        UsersDao usersDao = new UsersDao();
        Optional<User> userOptional = usersDao.getUserByUuid(uuid);

        assertTrue(userOptional.isPresent());
    }
}
