<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Актуализиране на профил</title>
    <link rel="stylesheet" type="text/css" href="styles\update_user.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <title>Edit User</title>
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
<div id="main">
    <%
        if (request.getSession().getAttribute("id") == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
    %>
    <h1>Enter the information you want to update</h1>
    <div id="form">
        <form method="post" action="updateUser">
            <label><p id="usernameP">Username <input type="text"
                                                     name="username" <% if (request.getAttribute("username") != null) { %>
                                                     value="<%=request.getAttribute("username")%>" <% } %> />
            </p></label>
            <label><p id="passwordP">Password <input type="password"
                                                     name="password" <% if (request.getAttribute("password") != null) { %>
                                                     value="<%=request.getAttribute("password")%>" <% } %> />
            </p></label>
            <label><p id="confPasswordP">Confirm password<input type="password"
                                                                name="confPassword" <% if (request.getAttribute("confPassword") != null) { %>
                                                                value="<%=request.getAttribute("confPassword")%>" <% } %> />
            </p></label>
            <label><p id="phoneNumberP">Phone number <input type="text"
                                                            name="phoneNumber" <% if (request.getAttribute("phoneNumber") != null) { %>
                                                            value="<%=request.getAttribute("phoneNumber")%>" <% } %> />
            </p></label>
            <label><p id="addressP">Address <input type="text"
                                                   name="address" <% if (request.getAttribute("address") != null) { %>
                                                   value="<%=request.getAttribute("address")%>" <% } %> />
            </p></label>

            <% if (request.getAttribute("errorText") != null) { %>
            <%= request.getAttribute("errorText") %>
            </p>
            <% } %>
            <input type="submit" value="Save" class="submit"/>
        </form>
    </div>
</div>
</body>
</html>
