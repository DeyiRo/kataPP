package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id bigint not null auto_increment primary key, " +
                "name VARCHAR(100) null, " +
                "lastName VARCHAR(100) null, " +
                "age INT null)";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String query = "INSERT INTO users (name, lastName, age) values(?, ?, ?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

            System.out.println(String.format("User с именем – %s добавлен в базу данных", name));

        } catch (SQLException e) {
            e.getMessage();
        }

    }

    public void removeUserById(long id) throws SQLException {

        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();

            System.out.println(String.format("User c id %n удален из базы данных", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> usersList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));

                usersList.add(user);
            }

        } catch (SQLException e) {
            e.getMessage();
        }

        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        String query = "DELETE FROM users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
