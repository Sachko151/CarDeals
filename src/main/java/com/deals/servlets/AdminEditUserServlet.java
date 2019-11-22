package com.deals.servlets;

import com.deals.models.Article;
import com.deals.models.Role;
import com.deals.models.User;
import com.deals.services.AdminService;
import com.deals.services.ArticleService;
import com.deals.services.RoleService;
import com.deals.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "AdminEditUserServlet", urlPatterns = "/updateAdmin")
public class AdminEditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id") == null) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        long adminId = (long) req.getSession().getAttribute("id");
        String isAdmin = req.getParameter("authority");
        boolean boolIsAdmin = CheckIsAdmin(isAdmin);
        String userId = (String) req.getParameter("userId");
        if (!CheckUser(userId)) {
            req.setAttribute("errorMessage", "Invalid User!");
            resp.sendRedirect("not-found.jsp");
            return;
        }
        long userIdLong = Long.parseLong(userId);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confPassword = req.getParameter("confPassword");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        try {
            AdminService.getInstance().updateUser(adminId, userIdLong, password, confPassword, phoneNumber, address, boolIsAdmin);
            req.getSession().setAttribute("user", username);
            resp.sendRedirect("/admin-users.jsp");
        } catch (IllegalArgumentException | IllegalStateException | IllegalAccessException e) {
            e.printStackTrace();
            resp.sendRedirect("unauthorized.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getSession().getAttribute("id") == null) {
                resp.sendRedirect("login.jsp");
                return;
            }
            String userId = (String) req.getParameter("userId");
            if (!CheckUser(userId)) {
                req.setAttribute("errorMessage", "Invalid User!");
                resp.sendRedirect("not-found.jsp");
                return;
            }
            long adminId = (long) req.getSession().getAttribute("id");
            User user = AdminService.getInstance().getUser(adminId, Long.parseLong(userId));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/updateAdmin.jsp").forward(req, resp);
        } catch (IllegalArgumentException | IllegalStateException | IllegalAccessException e) {
            e.printStackTrace();
            resp.sendRedirect("unauthorized.jsp");

        }

    }

    private boolean CheckUser(String userId) {
        try {
            Long.parseLong(userId);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean CheckIsAdmin(String isAdmin) {
        return isAdmin != null;
    }


}