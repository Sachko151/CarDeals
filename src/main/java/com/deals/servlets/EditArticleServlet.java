package com.deals.servlets;

import com.deals.models.Article;
import com.deals.models.User;
import com.deals.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "EditArticleServlet", urlPatterns = "/editArticle")
public class EditArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        try {
            String title = req.getParameter("title");
            String imageLink = req.getParameter("imageLink");
            BigDecimal price = BigDecimal.valueOf(Integer.parseInt(req.getParameter("price")));
            int manufacturerYear = Integer.parseInt(req.getParameter("year"));
            int power = Integer.parseInt(req.getParameter("power"));
            String eurostandard = req.getParameter("eurostandard");
            int mileage = Integer.parseInt(req.getParameter("mileage"));
            String color = req.getParameter("color");
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            String region = req.getParameter("region");
            long userId = (long) req.getSession().getAttribute("id");
            String category = req.getParameter("category");
            String transmission = req.getParameter("transmission");
            String engine = req.getParameter("engine");
            Article article = (Article) req.getSession().getAttribute("article");
            long articleId = article.getId();
            User user = article.getUser();
            long articleUserId = user.getId();
            if (userId == articleUserId) {
                req.setAttribute("article", article);
                req.getRequestDispatcher("/updateArticle.jsp");
                ArticleService.getInstance().update(title, imageLink, price, manufacturerYear, power, eurostandard, mileage,
                        color, brand, model, region, userId, category, transmission, engine, articleId);
            } else {
                req.getRequestDispatcher("/unauthorized.jsp");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.setAttribute("empty-fields-error", e.getMessage());
            req.getRequestDispatcher("/updateArticle.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long userId = (long) req.getSession().getAttribute("id");
            long articleId = Integer.parseInt(req.getParameter("articleId"));
            Article article = ArticleService.getInstance().get(articleId);
            User user = article.getUser();
            long articleUserId = user.getId();
            if (userId == articleUserId) {
                req.setAttribute("article", article);
                req.getRequestDispatcher("/updateArticle.jsp");
            } else {
                req.getRequestDispatcher("/unauthorized.jsp");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.getRequestDispatcher("/not-found.jsp");
        }

    }
}

