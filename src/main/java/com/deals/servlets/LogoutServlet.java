package com.deals.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("id") == null) {
            response.sendRedirect("/index.jsp");
        } else {
            request.getSession().removeAttribute("id");
            request.getSession().removeAttribute("username");
            request.getSession().invalidate();
            response.sendRedirect("/index.jsp");
        }
    }
}
