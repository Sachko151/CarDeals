<%@ page import="com.deals.models.enums.Category" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="style\favicon.png"/>
    <link rel="stylesheet" type="text/css" href="styles/AddArticleStyle.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <title>Add an Article</title>
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
        <li><a class="active" href="create_article.jsp">Add article</a></li>
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
    <h1>Add an Article</h1>
    <form action="addArticle" method="post">
        <label><p id="titleP">Title:
            <input type="text" name="title" required="true" placeholder="New car" id="title">
        </p></label>
        <label><p id="pictureP">Image:
            <input type="text" name="image" id="image" required="true">
        </p></label>
        <label><p id="EPriceP">Price:
            <input type="number" name="price" required="true" placeholder="1000" id="price">
        </p></label>
        <label><p id="dateP">Аate of manufacture:
            <input type="number" name="date" required="true" placeholder="1980" id="date">
        </p></label>
        <label><p id="powerP">Power:
            <input type="number" name="power" required="true" placeholder="100" id="power">
        </p></label>
        <label><p id="euroP">Eurostandard:
            <input type="text" name="euro" required="true" placeholder="EURO5" id="euro">
        </p></label>
        <label><p id="mileageP">Mileage:
            <input type="number" name="mileage" required="true" placeholder="50000" id="mileage">
        </p></label>
        <label><p id="colorP">Color:
            <input type="text" name="color" required="true" placeholder="white" id="color">
        </p></label>
        <label><p id="brandP">Brand:
            <input type="text" name="brand" required="true" placeholder="Opel" id="brand">
        </p></label>
        <label><p id="modelP">Model:
            <input type="text" name="model" required="true" placeholder="Astra" id="model">
        </p></label>
        <label><p id="regionP">Region:
            <input type="text" name="region" required="true" placeholder="Mezdra" id="region">
        </p></label>
        <label>
            <p id="categoryP">Category:
                <select name="category" id="category" required="true" class="category">
                    <option value="">--------</option>
                    <%
                        for (Category category : Category.values()) { %>
                    <option value=<%= category %>><%= category %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </p>
        </label>
        <label><p id="transmissionP">Transmission:
            <select name="transmission" id="transmission" required="true">
                <option value="">--------</option>
                <option value="MANUAL">MANUAL</option>
                <option value="AUTOMATIC">AUTOMATIC</option>
                <option value="SEMIAUTOMATIC">SEMIAUTOMATIC</option>
            </select>
        </p></label>
        <label><p id="enigneP">Engine type:
            <select name="engine" id="engine" required="true">
                <option value="">--------</option>
                <option value="GASOLINE">GASOLINE</option>
                <option value="DIESEL">DIESEL</option>
                <option value="ELECTRIC">ELECTRIC</option>
                <option value="HYBRID">HYBRID</option>
            </select>
        </p></label>
        <input type="submit" name="submit" value="Add" id="submit" onclick="SubmitButtonClicked()">
    </form>
</div>
<script type="text/javascript" src="script/addArticle.js"></script>
</body>
</html>
