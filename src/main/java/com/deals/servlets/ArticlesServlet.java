package com.deals.servlets;

import com.deals.models.Article;
import com.deals.models.enums.Category;
import com.deals.models.enums.EngineType;
import com.deals.models.enums.TransmissionType;
import com.deals.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticlesServlet", urlPatterns = "/searchArticles")
public class ArticlesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = Category.valueOf(req.getParameter("category"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Double maxPrice = Double.parseDouble(req.getParameter("maxPrice"));
        Integer year = Integer.parseInt(req.getParameter("year"));
        EngineType engine = EngineType.valueOf(req.getParameter("engine"));
        TransmissionType transmission = TransmissionType.valueOf(req.getParameter("transmission"));

        List<Article> articles;
        articles =  ArticleService.getInstance().search(category, brand, model, maxPrice, year, engine, transmission);
        req.setAttribute("articles", articles);
        req.getRequestDispatcher("/search-result.jsp").forward(req, resp);
    }
}
