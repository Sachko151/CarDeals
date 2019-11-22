package com.deals.daos;

import com.deals.models.Article;
import com.deals.models.enums.Category;
import com.deals.models.enums.EngineType;
import com.deals.models.enums.TransmissionType;
import com.deals.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private static ArticleDao INSTANCE;

    private ArticleDao() {

    }

    public static ArticleDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArticleDao();
        }
        return INSTANCE;
    }

    public void create(Article article) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(article);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Article article) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(article);
            System.out.println("Article is updated");
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(long articleId) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            Article article = session.get(Article.class, articleId);
            if (article != null) {
                session.delete(article);
                System.out.println("Article is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Article get(long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Article article = session.get(Article.class, id);
            return article;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Article> getAll() {
        List<Article> articles;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            articles = session.createNativeQuery("SELECT * FROM articles", Article.class).getResultList();
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Article> find(Category category, String make, String model, Double maxPrice, Integer year,
                              EngineType engine, TransmissionType transmission) {
        List<Article> articles;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String sql = "SELECT * FROM articles";
            if ((category != null) || (make != null) || (model != null) || (maxPrice != null) || (year != null) || (engine != null) || (transmission != null)) {
                sql += " WHERE";
            }
            if (category != null) sql += " category='" + category + "' AND";
            if (make != null) sql += " brand='" + make + "' AND";
            if (model != null) sql += " model='" + model + "' AND";
            if (maxPrice != null) sql += " price<=" + maxPrice + " AND";
            if (year != null) sql += " manufacturerYear>=" + year + " AND";
            if (engine != null) sql += " engine='" + engine + "' AND";
            if (transmission != null) sql += " transmission='" + transmission + "'";

            if (sql.endsWith("' AND")) {
                sql = sql.substring(0, sql.length()-4);
            }
            articles = session.createNativeQuery(sql, Article.class).getResultList();
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

