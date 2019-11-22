package com.deals.servlets;

import com.deals.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "AddArticleServlet", urlPatterns = "/addArticle")
public class AddArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String title = req.getParameter("title");
            String imageLink = req.getParameter("image");
            BigDecimal price = BigDecimal.valueOf(Integer.parseInt(req.getParameter("price")));
            int manufacturerYear = Integer.parseInt(req.getParameter("date"));
            int power = Integer.parseInt(req.getParameter("power"));
            String eurostandard = req.getParameter("euro");
            int mileage = Integer.parseInt(req.getParameter("mileage"));
            String color = req.getParameter("color");
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            String region = req.getParameter("region");
            long userId;
            if (req.getSession().getAttribute("id") == null) {
                userId = 0;
            } else {
                userId = (long) req.getSession().getAttribute("id");
            }
            String category = req.getParameter("category");
            String transmission = req.getParameter("transmission");
            String engine = req.getParameter("engine");

            ArticleService.getInstance().create(title, imageLink, price, manufacturerYear, power, eurostandard, mileage, color, brand,
                    model, region, userId, category, transmission, engine);
            resp.sendRedirect("/index.jsp");
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.setAttribute("empty-fields-error", e.getMessage());
            req.getRequestDispatcher("/create_article.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/addArticle.jsp");
    }
}
