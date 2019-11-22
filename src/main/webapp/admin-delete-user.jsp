<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
    <link rel="stylesheet" type="text/css" href="styles/admin-delete-user-style.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>

</head>
<body>
<%
    User loggedUser = null;
    if (request.getSession().getAttribute("id") != null) {
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

<%
    User user = (User) request.getAttribute("user");
%>

<div class="delete-user-form">
    <form method="post" action="delete-user">

        <label>Username:</label>
        <input readonly type="text" value="<%=user.getUsername()%>">

        <label>Phone Number:</label>
        <input readonly type="text" value="<%=user.getPhoneNumber()%>">

        <label>Address:</label>
        <input readonly type="text" value="<%=user.getAddress()%>">


        <input type="hidden" name="userId" value="<%=user.getId()%>">

        <br>
        <input class="delete-button" type="submit" value="Delete User">
        <br>

    </form>
</div>

</body>
</html>
