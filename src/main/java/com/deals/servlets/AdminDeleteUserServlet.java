package com.deals.servlets;

import com.deals.models.User;
import com.deals.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDeleteUserServlet", urlPatterns = "/delete-user")
public class AdminDeleteUserServlet extends HttpServlet {
    private static final String INVALID_USER_ID = "The user id is either null or not an integer";
    private AdminService adminService = AdminService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        long adminId = (long) request.getSession().getAttribute("id");

        String userId = request.getParameter("userId");
        if (!isUserIdValid(userId)) {
            request.setAttribute("errorMessage", INVALID_USER_ID);
            response.sendRedirect("not-found.jsp");
            return;
        }
        long longUserId = Long.parseLong(userId);

        try {
            adminService.deleteUser(adminId, longUserId);
            response.sendRedirect("admin-users.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("unauthorized.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        long adminId = (long) request.getSession().getAttribute("id");

        String userId = request.getParameter("userId");
        if (!isUserIdValid(userId)) {
            request.setAttribute("errorMessage", INVALID_USER_ID);
            response.sendRedirect("not-found.jsp");
            return;
        }
        long longUserId = Long.parseLong(userId);

        try {
            User user = adminService.getUser(adminId, longUserId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/admin-delete-user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("unauthorized.jsp");
        }
    }

    private boolean isUserIdValid(String userId) {
        try {
            Long.parseLong(userId);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
