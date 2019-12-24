package app.dao;

import app.entities.User;
import app.util.PropertyReader;

import java.sql.SQLException;
import java.util.List;

public abstract class UserDaoFactory {

    private static String property = PropertyReader.getProperty("property", "dao.properties").toLowerCase();

    public abstract void insertUser(User user);
    public abstract User selectUser(Long id);
    public abstract List<User> selectAllUsers();
    public abstract void deleteUser(Long id) throws SQLException;
    public abstract void updateUser(User user) throws SQLException;


    public static UserDaoFactory getUserDaoFactory() {

        switch (property) {
            case "hibernate":
                return new UserDaoHibernate();
            case "jdbc"     :
                return new UserDaoJdbc();
            default         :
                return null;
        }
    }
}
