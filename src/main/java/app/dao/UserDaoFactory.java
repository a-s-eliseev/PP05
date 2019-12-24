package app.dao;

import app.util.PropertyReader;

public abstract class UserDaoFactory {

    private static String property = PropertyReader.getProperty("property", "dao.properties").toLowerCase();

    public static UserDao getUserDao() {

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
