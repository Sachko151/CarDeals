<%@ page import="java.awt.*" %>
<%@ page import="com.deals.models.Role" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page import="com.deals.services.RoleService" %>
<%@ page import="com.deals.services.AdminService" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.daos.UserDao" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="style/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="style/HomePageStyle.css"/>
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <link rel="Fascinate" type="fa" href="">
    <title>HomePage</title>
</head>
<body>
<%
    User user = null;
    if (request.getSession().getAttribute("id")!= null) {
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
        <li><a href="updateUser.jsp">Account settings</a></li>
        <li><a href="create_article.jsp">Add article</a></li>
        <% } %>
        <% if (request.getSession().getAttribute("id") == null) { %>
        <li><a href="register.jsp">Register</a></li>
        <li><a href="login.jsp">Login</a></li>
        <% } %>
        <li><a href="index.jsp" class="active">Home</a></li>
        <img class="car-logo" src="styles/car-logo.png">
        <h2>Car Deals</h2>
    </div>
</ul>
<img src="style/home_page_car.jpg" class="car-image">

<div class="main-words">
    <h1>Find your perfect car match!</h1>
    <h2 class="tiny-words">Browse our extensive car listing catalogue</h2>
</div>

<div id="main">
    <form action="searchArticles" method="get">
        <label><p id="categoryP">Category:
            <select name="category" id="category" required="true" class="category">
                <option value="">--------</option>
                <%
                    for (Category category : Category.values()) { %>
                <option value=<%= category %>><%= category %></option>
                <%
                    }
                %>
            </select>
        </p>
        </label>
        <label><p id="brandP">Brand:
            <input type="text" name="brand" required="true" placeholder="Ford" id="brand">
        </p></label>
        <label><p id="EPriceP">End price:
            <input type="number" name="maxPrice" required="true" placeholder="1000" id="endPrice">
        </p></label>
        <label><p id="dateP">Manufacture year:
            <input type="number" name="year" required="true" placeholder="1980" id="manufacture">
        </p></label>
        <label><p id="modelP">Model:
            <input type="text" name="model" required="true" placeholder="ecosport" id="model">
        </p></label>
        <label>
            <p id="transmissionP">Transmission:
                <select name="transmission" id="transmission" required="true" class="transmission">
                    <option value="">--------</option>
                    <option value="MANUAL">MANUAL</option>
                    <option value="AUTOMATIC">AUTOMATIC</option>
                    <option value="SEMIAUTOMATIC">SEMIAUTOMATIC</option>
                </select>
            </p>
        </label>
        <label>
            <p id="enigneP">Engine:
                <select name="engine" id="engine" required="true" class="engine">
                    <option value="">--------</option>
                    <option value="GASOLINE">GASOLINE</option>
                    <option value="DIESEL">DIESEL</option>
                    <option value="ELECTRIC">ELECTRIC</option>
                    <option value="HYBRID">HYBRID</option>
                </select>
            </p>
        </label>
        <input type="submit" name="submit" value="Search" id="submit">
    </form>
</div>

</body>
</html>
