package com.deals.daos;

import com.deals.models.User;
import com.deals.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    public void create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User get(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public User searchForUser(String username, String password) {
        List<User> users;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM users AS u WHERE u.username = :username AND u.password = :password";
            users = session.createNativeQuery(sql, User.class).setParameter("username", username).setParameter("password", password).getResultList();
            if (users == null || users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> listUsers() {
        List<User> users;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM users";
            users = session.createNativeQuery(sql, User.class).getResultList();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        public User getUserByUsername(String username) {
        List<User> users;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM users AS u WHERE u.username = :username";
            users = session.createNativeQuery(sql, User.class)
                    .setParameter("username", username)
                    .getResultList();
            if (users == null || users.isEmpty()) {
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}