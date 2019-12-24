package app.dao;

import app.entities.User;
import app.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public void insertUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (login, password, email) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(Long id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select id, login, password, email from users where id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                user = new User(id, login, password, email, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users")) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                users.add(new User(id, login, password, email, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from users where id = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("update users set login = ?,password= ?, email =? where id = ?;")) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public boolean validate(User user) {
        boolean status = false;

        try (PreparedStatement statement = connection.prepareStatement("select * from users where login = ? and password = ?")) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            ResultSet rs = statement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}
