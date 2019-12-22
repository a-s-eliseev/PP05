package app.service;

import app.dao.UserDaoFactory;
import app.entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;

    private UserDaoFactory userDaoFactory = new UserDaoFactory();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {

        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public void newUser(User user) {
        userDaoFactory.insertUser(user);
    }

    @Override
    public List<User> listUser() {
        return userDaoFactory.selectAllUsers();
    }

    @Override
    public User selectUser(Long id) {
        return userDaoFactory.selectUser(id);
    }

    @Override
    public void editUser(User user) throws SQLException {
        userDaoFactory.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        userDaoFactory.deleteUser(id);
    }
}