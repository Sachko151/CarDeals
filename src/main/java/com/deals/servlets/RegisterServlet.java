package com.deals.servlets;

import com.deals.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("reg-username");
            String pass = request.getParameter("reg-pass");
            String confPass = request.getParameter("reg-repeat-pass");
            String address = request.getParameter("reg-address");
            String phoneNumber = request.getParameter("reg-phone");
            UserService.getInstance().register(username, pass, confPass, address, phoneNumber);
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            request.setAttribute("empty-fields-error", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
