<%@ page import="com.deals.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.deals.models.Article" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%-- Created by IntelliJ IDEA. User: PC Date: 11.11.2019 г. Time: 23:45 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/admin-users.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <title>Admin Users</title>
</head>
<body>
<%
    User loggedUser = null;
    if (request.getSession().getAttribute("id")!= null) {
        loggedUser = UserService.getInstance().get((Long) request.getSession().getAttribute("id"));
    }
%>
<ul>
    <div class="navigation-bar">
        <% if (loggedUser != null) {
            if (loggedUser.getRoles().stream().anyMatch(r -> r.getAuthority().equals(Authority.ADMIN.role))) { %>
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
<h1>Admin Users</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Actions</th>
    </tr>
    <% List<User> users = (List<User>) request.getAttribute("users");

        if(users != null && !users.isEmpty()) {
        for (User user : users) { %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <td>
            <form action="/updateAdmin" method="get">
                <input type="hidden" name="userId" value="<%=user.getId()%>">
                <input type="submit" value="Edit User" class="edit-user-btn">
            </form>
            <form action="delete-user" method="get">
                <input type="hidden" name="userId" value="<%=user.getId()%>">
                <input type="submit" value="Delete User" class="delete-user-btn">
            </form>
        </td>
    </tr>
    <% } }%>
</table>
</body>
</html>