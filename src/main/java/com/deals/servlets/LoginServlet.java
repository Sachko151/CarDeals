package com.deals.servlets;

import com.deals.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            long id = UserService.getInstance().login(username, password);
            req.getSession().setAttribute("id", id);
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("/index.jsp");
        } catch (IllegalArgumentException | IllegalStateException e) {
            String message = e.getMessage();
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }
}
