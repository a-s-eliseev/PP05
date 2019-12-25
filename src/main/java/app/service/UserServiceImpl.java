package app.service;

import app.dao.UserDao;
import app.dao.UserDaoFactory;
import app.entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;

    private UserDao userDao = UserDaoFactory.getUserDao();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {

        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public void newUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public List<User> listUser() {
        return userDao.selectAllUsers();
    }

    @Override
    public User selectUser(Long id) {
        return userDao.selectUser(id);
    }

    @Override
    public void editUser(User user) throws SQLException {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        userDao.deleteUser(id);
    }

    @Override
    public String validate(User user) {
        return userDao.validate(user);
    }
}