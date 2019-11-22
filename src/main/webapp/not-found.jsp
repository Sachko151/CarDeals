<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="styles\favicon.png"/>
    <link rel="stylesheet" type="text/css" href="styles\not-found.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <link rel="Fascinate" type="fa" href="">
    <title>Not found</title>
</head>
<body>
<%
    User user = null;
    if (request.getSession().getAttribute("id") != null) {
        user = UserService.getInstance().get((Long) request.getSession().getAttribute("id"));
    }
%>
<ul>
    <div class="navigation-bar">
        <% if (user != null) {
            if (user.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Authority.ADMIN.role))) { %>
        <li>
            <form method="get" action="/admin-users">
                <input type="submit" value="List Users" class="users-list">
            </form>
        </li>
        <% }
        } %>
        <% if (request.getSession().getAttribute("id") != null) { %>
        <li>
            <form method="post" action="/logout">
                <input type="submit" value="Log Out" class="logout">
            </form>
        </li>
        <li><a class="active" href="updateUser.jsp">Account settings</a></li>
        <li><a href="create_article.jsp">Add article</a></li>
        <% } %>
        <% if (request.getSession().getAttribute("id") == null) { %>
        <li><a href="register.jsp">Register</a></li>
        <li><a href="login.jsp">Login</a></li>
        <% } %>
        <li><a href="index.jsp">Home</a></li>
        <img class="car-logo" src="styles/car-logo.png">
        <h2>Car Deals</h2>
    </div>
</ul>
<div id="main" style="margin-top: 50px">
    <h1 style="color: #df180e">No records found</h1>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <h2 style="color: #df180e; font-size: 2.5em">
        <%= request.getAttribute("errorMessage") %>
    </h2>
    <% } %>
</div>
</body>
</html>
