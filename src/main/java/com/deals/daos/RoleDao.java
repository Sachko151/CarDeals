package com.deals.daos;

import com.deals.models.Article;
import com.deals.models.Role;
import com.deals.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoleDao {

    private static RoleDao INSTANCE;


    public static RoleDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleDao();
        }
        return INSTANCE;
    }


    public void create(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Role get(String authority) {
        List<Role> roles;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM roles AS u WHERE u.authority = :authority";
            roles = session.createNativeQuery(sql, Role.class).setParameter("authority", authority).getResultList();
            if (roles.isEmpty()) {
                return null;
            } else {
                return roles.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
