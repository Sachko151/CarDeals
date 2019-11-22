<%@ page import="com.deals.models.Article" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="styles\favicon.png"/>
    <link rel="stylesheet" type="text/css" href="styles\DeleteArticleStyle.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <title>Delete an Article</title>
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
    Article article = (Article) request.getSession().getAttribute("article");
%>
<%
    if (request.getSession().getAttribute("id") == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
%>
<div id="main">
    <h1>Delete an Article</h1>
    <form action="deleteArticle" method="post">
        <label><p id="titleP">Title:
            <input type="text" name="title" required="true" value="<%=article.getTitle()%>" id="title"
                   readonly>
        </p></label>
        <label><p id="pictureP">Image:
            <input type="text" name="imageLink" id="image" required="true" value="<%=article.getImageLink()%>"
                   readonly>
        </p></label>
        <label><p id="EPriceP">Price:
            <input type="number" name="price" required="true" value="<%=article.getPrice()%>" id="price"
                   readonly>
        </p></label>
        <label><p id="dateP">Date of manifacture:
            <input type="number" name="year" required="true" value="<%=article.getManufacturerYear()%>"
                   id="date" readonly>
        </p></label>
        <label><p id="powerP">Power:
            <input type="number" name="power" required="true" value="<%=article.getPower()%>" id="power"
                   readonly>
        </p></label>
        <label><p id="euroP">Eurostandard:
            <input type="text" name="eurostandard" required="true" value="<%=article.getEurostandard()%>" id="euro"
                   readonly>
        </p></label>
        <label><p id="mileageP">Mileage:
            <input type="number" name="mileage" required="true" value="<%=article.getMileage()%>"
                   id="mileage" readonly>
        </p></label>
        <label><p id="colorP">Color:
            <input type="text" name="color" required="true" value="<%=article.getColor()%>" id="color"
                   readonly>
        </p></label>
        <label><p id="brandP">Brand:
            <input type="text" name="brand" required="true" value="<%=article.getBrand()%>" id="brand"
                   readonly>
        </p></label>
        <label><p id="modelP">Model:
            <input type="text" name="model" required="true" value="<%=article.getModel()%>" id="model"
                   readonly>
        </p></label>
        <label><p id="regionP">Region:
            <input type="text" name="region" required="true" value="<%=article.getRegion()%>" id="region"
                   readonly>
        </p></label>
        <label><p id="categoryP">Category:
            <input type="text" name="category" required="true" value="<%=article.getCategory()%>"
                   id="category" readonly>
        </p></label>
        <label><p id="transmissionP">Transmission:
            <input type="text" name="transmission" required="true" value="<%=article.getTransmission()%>"
                   id="transmission" readonly>
        </p></label>
        <label><p id="enigneP">Engine type:
            <input type="text" name="engine" required="true" value="<%=article.getEngine()%>" id="engine"
                   readonly>
        </p></label>
        <input type="hidden" name="articleId"  value="<%=article.getId()%>">
        <input type="submit" name="submit" value="Delete" id="submit">
    </form>
</div>
</body>
</html>