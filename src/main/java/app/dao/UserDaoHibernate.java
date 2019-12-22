package app.dao;

import app.entities.User;
import app.util.DBHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernate implements UserDao {

    private Session session;
    private Transaction transaction;
    private List<User> users = null;
    private User user = null;

    @Override
    public void insertUser(User user) {
        try {
            session = DBHelper.getConfiguration().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User selectUser(Long id) {
        try {
            session = DBHelper.getConfiguration().openSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        try {
            session = DBHelper.getConfiguration().openSession();
            transaction = session.beginTransaction();
            users = session.createQuery("SELECT u FROM User u").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        try {
            session = DBHelper.getConfiguration().openSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            session = DBHelper.getConfiguration().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
