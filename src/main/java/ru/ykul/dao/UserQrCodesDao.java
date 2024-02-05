package ru.ykul.dao;

import ru.ykul.database.PostgreSQLConnection;
import ru.ykul.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserQrCodesDao {

    public void create(User user) {

        String createUuidQuery = "INSERT INTO user_qr_codes (user_id, uuid) " +
                "VALUES (?, uuid_generate_v4());";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement createUuidQueryStatement = connection.prepareStatement(createUuidQuery)) {
            createUuidQueryStatement.setInt(1, user.getId());

            createUuidQueryStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {

        String updateUuidQuery = "UPDATE user_qr_codes SET uuid = uuid_generate_v4() " +
                "WHERE user_id = ?";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement updateUuidQueryStatement = connection.prepareStatement(updateUuidQuery)) {
            updateUuidQueryStatement.setInt(1, user.getId());

            updateUuidQueryStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {

        String deleteUuidQuery = "DELETE FROM user_qr_codes WHERE user_id = ?";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement deleteUuidQueryStatement = connection.prepareStatement(deleteUuidQuery)) {
            deleteUuidQueryStatement.setInt(1, user.getId());

            deleteUuidQueryStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
