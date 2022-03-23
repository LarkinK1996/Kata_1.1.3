package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Users (id SERIAL PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(20),lastname VARCHAR(20),age INT)";
    String CLEAN_TABLE = "TRUNCATE TABLE Users";
    String DELETE_TABLE = "DROP TABLE IF EXISTS Users";
    String ADD_USERS = "INSERT INTO Users(name,lastname,age) VALUES (?,?,?)";
    String REMOVE_USER_FROM_ID = "DELETE FROM Users WHERE id = ?";
    String GET_ALL_USERS = "SELECT * FROM Users";

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TABLE)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USERS)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_USER_FROM_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                users.add(new User(name, lastname, age));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(CLEAN_TABLE)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
