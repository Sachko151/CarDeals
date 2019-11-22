package com.deals.servlets;

import com.deals.models.Article;
import com.deals.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleDetailsServlet", urlPatterns = "/details")
public class ArticleDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("articleId") != null) {
            response.sendRedirect("/index.jsp");
            return;
        }
        long articleId = Long.parseLong(request.getParameter("articleId"));
        try {
            Article article = ArticleService.getInstance().get(articleId);
            request.setAttribute("article", article);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            request.getRequestDispatcher("/not-found.jsp").forward(request, response);
        }
    }
}
