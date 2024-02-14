package ru.ykul.dao;

import ru.ykul.config.DBConnection;
import ru.ykul.model.UserQrCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UserQrCodesDao {

    public void create(UserQrCode userQrCode) {

        UUID uuid = UUID.randomUUID();

        String createUuidQuery = "INSERT INTO user_qr_codes (user_id, uuid) " +
                "VALUES (?, ?);";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(createUuidQuery)) {
            ps.setInt(1, userQrCode.getUserId());
            ps.setObject(2, uuid);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserQrCode> getById(int userId) {

        String getUuidQuery = "SELECT q.id, q.user_id, q.uuid " +
                "FROM user_qr_codes AS q JOIN " +
                "users ON users.id = q.user_id " +
                "WHERE users.id = ?;";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(getUuidQuery)) {
            ps.setInt(1, userId);
            try(ResultSet resultSet = ps.executeQuery()) {
                return resultSetMapping(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateByUserId(int userId) {

        UUID uuid = UUID.randomUUID();

        String updateUuidQuery = "UPDATE user_qr_codes SET uuid = ? " +
                "WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateUuidQuery)) {
            ps.setObject(1, uuid);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UserQrCode userQrCode) {

        String deleteUuidQuery = "DELETE FROM user_qr_codes WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteUuidQuery)) {
            ps.setInt(1, userQrCode.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<UserQrCode> resultSetMapping(ResultSet resultSet) throws SQLException {

        if(resultSet.next()) {

            int id = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            UUID uuid = (UUID) resultSet.getObject("uuid");

            UserQrCode userQrCode = new UserQrCode(id, userId, uuid);

            return Optional.of(userQrCode);
        }
        return Optional.empty();
    }
}

