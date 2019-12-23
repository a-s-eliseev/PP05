package app.dao;

import app.entities.User;
import app.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    Connection connection = DBHelper.getConnection();

    @Override
    public void insertUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstName, lastName, mail) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(Long id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select id, firstName, lastName, mail from users where id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String mail = rs.getString("mail");
                user = new User(id, firstName, lastName, mail);
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
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String mail = rs.getString("mail");
                users.add(new User(id, firstName, lastName, mail));
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
        try (PreparedStatement statement = connection.prepareStatement("update users set firstName = ?,lastName= ?, mail =? where id = ?;")) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getMail());
            statement.setLong(4, user.getId());

            statement.executeUpdate();
        }
    }
}
