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

@WebServlet(name = "DeleteArticleServlet", urlPatterns = "/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        try {
            long userId = (long) req.getSession().getAttribute("id");
            long articleId = Integer.parseInt(req.getParameter("articleId"));
            Article article = ArticleService.getInstance().get(articleId);
            User user = article.getUser();
            long articleUserId = user.getId();
            if (userId == articleUserId) {
                ArticleService.getInstance().delete(articleId);
                req.setAttribute("article", article);
                req.getRequestDispatcher("/index.jsp");
                resp.sendRedirect("/index.jsp");
            } else {
                req.getRequestDispatcher("/unauthorized.jsp");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.setAttribute("empty-fields-error", e.getMessage());
            req.getRequestDispatcher("/deleteArticle.jsp").forward(req, resp);
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
                req.getRequestDispatcher("/deleteArticle.jsp");
            } else {
                req.getRequestDispatcher("/unauthorized.jsp");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.getRequestDispatcher("/not-found.jsp");
        }
    }
}
