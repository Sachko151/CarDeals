package com.deals.servlets;

import com.deals.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUser", urlPatterns = "/updateUser")
public class UserAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        long id  = (long) req.getSession().getAttribute("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confPassword = req.getParameter("confPassword");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");


        try {
            UserService.getInstance().update(id, username, password, confPassword, phoneNumber, address);
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("/index.jsp");

        } catch (IllegalArgumentException | IllegalStateException e) {
            String message = e.getMessage();
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("confPassword", confPassword);
            req.setAttribute("phoneNumber", phoneNumber);
            req.setAttribute("address", address);
            req.setAttribute("errorText", message);
            req.getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            return;
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/updateUser.jsp");
    }
}
