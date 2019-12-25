package app.dao;

import app.entities.User;
import app.util.DBHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernate implements UserDao {

    @Override
    public void insertUser(User user) {
        Transaction transaction = null;

        try (Session session = DBHelper.getConfiguration().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public User selectUser(Long id) {
        Transaction transaction = null;
        User user = null;

        try (Session session = DBHelper.getConfiguration().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        Transaction transaction = null;
        List<User> users = null;

        try (Session session = DBHelper.getConfiguration().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("SELECT u FROM User u").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return users;
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = null;
        User user;

        try (Session session = DBHelper.getConfiguration().openSession()) {
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
        }
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = null;

        try (Session session = DBHelper.getConfiguration().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public String validate(User user) {
        Transaction transaction = null;
        User userValidate = null;
        String login = user.getLogin();
        String password = user.getPassword();


        try (Session session = DBHelper.getConfiguration().openSession()) {
            transaction = session.beginTransaction();
            userValidate = (User) session.createQuery("SELECT u FROM User u WHERE u.login = :login")
                    .setParameter("login", login)
                    .uniqueResult();

            if (userValidate != null && userValidate.getPassword().equals(password)) {
                return userValidate.getRole();
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return null;
    }
}
