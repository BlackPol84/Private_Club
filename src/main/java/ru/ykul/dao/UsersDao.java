package ru.ykul.dao;

import ru.ykul.database.PostgreSQLConnection;
import ru.ykul.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UsersDao {

    public Optional<User> getUserByUuid(UUID uuid) {

        String uuidString = uuid.toString();

        String query = "SELECT users.id, users.surname, users.name, users.middle_name " +
                "FROM users JOIN " +
                "user_qr_codes ON user_qr_codes.user_id = users.id " +
                "WHERE user_qr_codes.uuid::text like ?;";

        try (Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, uuidString);
            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSetMapping(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<User> resultSetMapping(ResultSet resultSet) throws SQLException {

        if(resultSet.next()) {

            int id = resultSet.getInt("id");
            String surname = resultSet.getString("surname");
            String name = resultSet.getString("name");
            String middleName = resultSet.getString("middle_name");

            User user = new User(id, surname, name, middleName);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public User create(User user) {

        String createUserQuery = "INSERT INTO users (surname, name, middle_name) " +
                "VALUES (?, ?, ?);";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement createUserQueryStatement = connection.prepareStatement(createUserQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
            createUserQueryStatement.setString(1, user.getSurname());
            createUserQueryStatement.setString(2, user.getName());
            createUserQueryStatement.setString(3, user.getMiddleName());

            createUserQueryStatement.executeUpdate();

            try(ResultSet generatedKeys = createUserQueryStatement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void update(User user) {

        String updateUserQuery = "UPDATE users SET surname = ?, name = ?, middle_name = ?" +
                "WHERE id = ?";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement updateUserQueryStatement = connection.prepareStatement(updateUserQuery)) {
            updateUserQueryStatement.setString(1, user.getSurname());
            updateUserQueryStatement.setString(2, user.getName());
            updateUserQueryStatement.setString(3, user.getMiddleName());
            updateUserQueryStatement.setInt(4, user.getId());

            updateUserQueryStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {

        String deleteUserQuery = "DELETE FROM users WHERE id = ?";

        try(Connection connection = PostgreSQLConnection.getConnection();
            PreparedStatement deleteUserQueryStatement = connection.prepareStatement(deleteUserQuery)) {
            deleteUserQueryStatement.setInt(1, user.getId());

            deleteUserQueryStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
