<%@ page import="com.deals.models.Article" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link rel="stylesheet" type="text/css" href="styles/details-style.css"/>
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
</head>
<body>
<% Article article = (Article) request.getAttribute("article"); %>
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

<h1><%=article.getTitle()%>
</h1>

<div class="container">

    <img src="<%=article.getImageLink()%>" id="car-img"/>

    <div class="labels">
        <label>Price: </label> <br>
        <label>Manufactured: </label> <br>
        <label>Engine Type:</label> <br>
        <label>Power: </label> <br>
        <label>Eurostandard: </label> <br>
        <label>Transmission: </label> <br>
        <label>Mileage: </label> <br>
        <label>Color: </label> <br>
        <label>Brand: </label> <br>
        <label>Model: </label> <br>
        <label>Region: </label> <br>
        <label>Category: </label>
    </div>

    <div class="car-info">
        <label><%=article.getPrice()%>
        </label> <br>
        <label><%=article.getManufacturerYear()%>
        </label> <br>
        <label><%=article.getEngine()%>
        </label> <br>
        <label><%=article.getPower()%>
        </label> <br>
        <label><%=article.getEurostandard()%>
        </label> <br>
        <label><%=article.getTransmission()%>
        </label> <br>
        <label><%=article.getMileage()%>
        </label> <br>
        <label><%=article.getColor()%>
        </label> <br>
        <label><%=article.getBrand()%>
        </label> <br>
        <label><%=article.getModel()%>
        </label> <br>
        <label><%=article.getRegion()%>
        </label> <br>
        <label><%=article.getCategory()%>
        </label>
    </div>
</div>
<br>
<% if (request.getSession().getAttribute("id") != null) {
    if (request.getSession().getAttribute("id").equals(article.getUser().getId())) {
        request.getSession().setAttribute("article", article);
%>

<div style="position: absolute; width: 56%; left: 25%; margin-top: 27%;">

    <form action="deleteArticle" method="post">
        <input type="submit"
               style="float: right; border-radius: 8px; width: 50%; font-size: 24px; border-color: #d10000; background-color: #d10000; height: 50px; color: whitesmoke; margin-top: 60px"
               value="Delete">
        <input type="hidden" value="<%=article.getId()%>">
    </form>

    <form action="editArticle" method="post">
        <input style="float: left; border-radius: 8px; width: 50%; font-size: 24px; border-color: deepskyblue; background-color: deepskyblue; height: 50px; color: whitesmoke; margin-top: 60px"
               type="submit" value="Edit">
        <input type="hidden" value="<%=article.getId()%>">
    </form>

</div>

<% }
}%>


</body>
</html>
