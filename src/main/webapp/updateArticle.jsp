<%@ page import="com.deals.models.Article" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" type="image/png" href="styles\favicon.png"/>
    <link rel="stylesheet" type="text/css" href="styles\EditArticleStyle.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <title>Edit Article</title>
</head>
<body>
<%
    Article article = (Article) request.getSession().getAttribute("article");
%>
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
<div id="main">
    <h1>Edit Article</h1>
    <form action="editArticle" method="post">
        <label><p id="titleP">Title:
            <input type="text" name="title" required="true" value="<%=article.getTitle()%>" id="title">
        </p></label>
        <label><p id="pictureP">Image:
            <input type="text" name="imageLink" id="image" required="true" value="<%=article.getImageLink()%>">
        </p></label>
        <label><p id="EPriceP">Price:
            <input type="number" name="price" required="true" value="<%=article.getPrice()%>" id="price">
        </p></label>
        <label><p id="dateP">Date of Manufacture:
            <input type="number" name="year" required="true" value="<%=article.getManufacturerYear()%>" id="date">
        </p></label>
        <label><p id="powerP">Power:
            <input type="number" name="power" required="true" value="<%=article.getPower()%>" id="power">
        </p></label>
        <label><p id="euroP">Eurostandard:
            <input type="text" name="eurostandard" required="true" value="<%=article.getEurostandard()%>" id="euro">
        </p></label>
        <label><p id="mileageP">Mileage:
            <input type="number" name="mileage" required="true" value="<%=article.getMileage()%>" id="mileage">
        </p></label>
        <label><p id="colorP">Color:
            <input type="text" name="color" required="true" value="<%=article.getColor()%>" id="color">
        </p></label>
        <label><p id="brandP">Brand:
            <input type="text" name="brand" required="true" value="<%=article.getBrand()%>" id="brand">
        </p></label>
        <label><p id="modelP">Model:
            <input type="text" name="model" required="true" value="<%=article.getModel()%>" id="model">
        </p></label>
        <label><p id="regionP">Region:
            <input type="text" name="region" required="true" value="<%=article.getRegion()%>" id="region">
        </p></label>
        <label><p id="categoryP">Category:
            <input type="text" name="category" required="true" value="<%=article.getCategory()%>" id="category">
        </p></label>
        <label>
            <p id="transmissionP">Transmission:
                <select name="transmission" id="transmission" required="true">
                    <option value="">--------</option>
                    <option value="MANUAL" <% if (article.getTransmission().name().equals("MANUAL")) { %>
                            selected <% } %>>MANUAL
                    </option>
                    <option value="AUTOMATIC" <% if (article.getTransmission().name().equals("AUTOMATIC")) { %>
                            selected <% } %>>AUTOMATIC
                    </option>
                    <option value="SEMIAUTOMATIC" <% if (article.getTransmission().name().equals("SEMIAUTOMATIC")) { %>
                            selected <% } %>>SEMIAUTOMATIC
                    </option>
                </select>
            </p>
        </label>
        <label>
            <p id="enigneP">Engine type:
                <select name="engine" id="engine" required="true">
                    <option value="">--------</option>
                    <option value="GASOLINE" <% if (article.getTransmission().name().equals("GASOLINE")) { %>
                            selected <% } %>>GASOLINE
                    </option>
                    <option value="DIESEL" <% if (article.getTransmission().name().equals("DIESEL")) { %>
                            selected <% } %>>DIESEL
                    </option>
                    <option value="ELECTRIC" <% if (article.getTransmission().name().equals("ELECTRIC")) { %>
                            selected <% } %>>ELECTRIC
                    </option>
                    <option value="HYBRID" <% if (article.getTransmission().name().equals("HYBRID")) { %>
                            selected <% } %>>HYBRID
                    </option>
                </select>
            </p>
        </label>
        <input type="hidden" name="articleId" value="<%=article.getId()%>" id="articleId">
        <input type="submit" name="submit" value="Update" id="submit" onclick="SubmitButtonClicked()">
    </form>
</div>
<script type="text/javascript" src="script/editArticle.js"></script>
</body>
</html>
