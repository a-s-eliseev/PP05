package app.dao;

import app.entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoFactory implements UserDao {

    UserDaoHibernate userDaoHibernate = new UserDaoHibernate();
    UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
    User user = null;
    private int dao = 2;


    public UserDaoFactory() {}

    public void insertUser(User user) {
        if (dao == 1) {
            userDaoHibernate.insertUser(user);
        } else {
            userDaoJdbc.insertUser(user);
        }
    }

    public User selectUser(Long id) {
        if (dao == 1) {
            user = userDaoHibernate.selectUser(id);
        } else {
            user = userDaoJdbc.selectUser(id);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        if (dao == 1) {
            return userDaoHibernate.selectAllUsers();
        } else {
            return userDaoJdbc.selectAllUsers();
        }
    }

    public void deleteUser(Long id) throws SQLException {
        if (dao == 1) {
            userDaoHibernate.deleteUser(id);
        } else {
            userDaoJdbc.deleteUser(id);
        }
    }

    public void updateUser(User user) throws SQLException {
        if (dao == 1) {
            userDaoHibernate.updateUser(user);
        } else {
            userDaoJdbc.updateUser(user);
        }
    }
}
