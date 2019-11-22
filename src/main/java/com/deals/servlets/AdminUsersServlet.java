package com.deals.servlets;

import com.deals.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUsersServlet", urlPatterns = "/admin-users")
public class AdminUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        long adminId = (long) req.getSession().getAttribute("id");
        try {
            req.setAttribute("users", AdminService.getInstance().listUsers(adminId));
            req.getRequestDispatcher("/admin-users.jsp").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/unauthorized.jsp").forward(req, resp);
        }


    }
}
