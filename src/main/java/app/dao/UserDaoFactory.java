package app.dao;

import app.entities.User;

import java.sql.SQLException;
import java.util.List;

public abstract class UserDaoFactory implements UserDao {

    public static final int hibernate = 1;
    public static final int jdbc = 2;

    public abstract void insertUser(User user);

    public abstract User selectUser(Long id);

    public abstract List<User> selectAllUsers();

    public abstract void deleteUser(Long id) throws SQLException;

    public abstract void updateUser(User user) throws SQLException;

    public static UserDaoFactory getUserDaoFactory (int whichFactory) {

        switch (whichFactory) {
            case hibernate:
                return new UserDaoHibernate();
            case jdbc     :
                return new UserDaoJdbc();
            default           :
                return null;
        }
    }
}
