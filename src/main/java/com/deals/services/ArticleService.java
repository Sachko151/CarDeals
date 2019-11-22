package com.deals.services;

import com.deals.daos.ArticleDao;
import com.deals.daos.UserDao;
import com.deals.models.Article;
import com.deals.models.enums.Category;
import com.deals.models.enums.EngineType;
import com.deals.models.enums.TransmissionType;
import com.mysql.jdbc.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class ArticleService {

    private static ArticleService INSTANCE;

    public static ArticleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArticleService();
        }
        return INSTANCE;
    }

    public void create(String title, String imageLink, BigDecimal price, int manufacturerYear, int power,
                       String eurostandard, int mileage, String color, String brand, String model, String region,
                       long userId, String category, String transmission, String engine) {

        checkArticleFields(title, imageLink, price, manufacturerYear, power, eurostandard, mileage, color, brand, model,
                region, userId, category, transmission, engine);

        Article article = new Article();

        article.setTitle(title);
        article.setImageLink(imageLink);
        article.setPrice(price);
        article.setManufacturerYear(manufacturerYear);
        article.setPower(power);
        article.setEurostandard(eurostandard);
        article.setMileage(mileage);
        article.setColor(color);
        article.setBrand(brand);
        article.setModel(model);
        article.setRegion(region);
        //FIXME get user by id from user dao
        UserDao userDao = new UserDao();
        article.setUser(userDao.get(userId));
        article.setCategory(Category.valueOf(category));
        article.setTransmission(TransmissionType.valueOf(transmission));
        article.setEngine(EngineType.valueOf(engine));

        ArticleDao.getInstance().create(article);

    }

    public void checkArticleFields(String title, String imageLink, BigDecimal price, int manufacturerYear, int power,
                                   String eurostandard, int mileage, String color, String brand, String model,
                                   String region, long userId, String category, String transmission, String engine) {

        if (StringUtils.isNullOrEmpty(title)) throw new IllegalStateException("You haven't entered title");
        if (StringUtils.isNullOrEmpty(imageLink)) throw new IllegalStateException("You haven't entered imageLink");
        if (price == null) throw new IllegalStateException("You haven't entered price");
        if (manufacturerYear < 1900)
            throw new IllegalStateException("You haven't entered manufacturerYear");
        if (power == 0) throw new IllegalStateException("You haven't entered power");
        if (StringUtils.isNullOrEmpty(eurostandard))
            throw new IllegalStateException("You haven't entered eurostandard");
        if (mileage == 0) throw new IllegalStateException("You haven't entered mileage");
        if (StringUtils.isNullOrEmpty(color)) throw new IllegalStateException("You haven't entered color");
        if (StringUtils.isNullOrEmpty(brand)) throw new IllegalStateException("You haven't entered brand");
        if (StringUtils.isNullOrEmpty(model)) throw new IllegalStateException("You haven't entered model");
        if (StringUtils.isNullOrEmpty(region)) throw new IllegalStateException("You haven't entered region");
        if (userId == 0) throw new IllegalStateException("You haven't entered userId");
        if (StringUtils.isNullOrEmpty(category)) throw new IllegalStateException("You haven't entered category");
        if (StringUtils.isNullOrEmpty(transmission))
            throw new IllegalStateException("You haven't entered transmission");
        if (StringUtils.isNullOrEmpty(engine)) throw new IllegalStateException("You haven't entered engineType");
    }

    public void update(String title, String imageLink, BigDecimal price, int manufacturerYear, int power,
                       String eurostandard, int mileage, String color, String brand, String model, String region,
                       long userId, String category, String transmission, String engine, long articleId) {

        checkArticleFields(title, imageLink, price, manufacturerYear, power, eurostandard, mileage, color, brand, model,
                region, userId, category, transmission, engine);

        Article article = ArticleDao.getInstance().get(articleId);

        article.setTitle(title);
        article.setImageLink(imageLink);article.setPrice(price);
        article.setManufacturerYear(manufacturerYear);
        article.setPower(power);
        article.setEurostandard(eurostandard);
        article.setMileage(mileage);
        article.setColor(color);
        article.setBrand(brand);
        article.setModel(model);
        article.setRegion(region);
        article.setCategory(Category.valueOf(category));
        article.setTransmission(TransmissionType.valueOf(transmission));
        article.setEngine(EngineType.valueOf(engine));
        UserDao userDao = new UserDao();
        article.setUser(userDao.get(userId));

        ArticleDao.getInstance().update(article);
    }

    public void delete(long articleId) {
        Article article = ArticleDao.getInstance().get(articleId);
        if (article == null) throw new IllegalStateException("There are no articles");

        ArticleDao.getInstance().delete(article.getId());
    }

    public Article get(long articleId) {
        return ArticleDao.getInstance().get(articleId);
    }

    List<Article> getAll() {
        return ArticleDao.getInstance().getAll();
    }

    public List<Article> search(Category category, String make, String model, Double maxPrice, Integer year,
                                EngineType engine, TransmissionType transmission) {


        return ArticleDao.getInstance().find(category, make, model, maxPrice, year, engine, transmission);
    }


}
