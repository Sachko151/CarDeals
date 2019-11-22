<%@ page import="com.deals.models.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.deals.models.User" %>
<%@ page import="com.deals.services.UserService" %>
<%@ page import="com.deals.models.enums.Authority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
    <link rel="stylesheet" type="text/css" href="styles/search-result-style.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
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
    List<Article> searchedArticles = (List<Article>) request.getAttribute("articles");
    if (searchedArticles.size() != 0) {
        for (Article article : searchedArticles) { %>
<div class="wrapper">

    <img class="search-img" src="<%=article.getImageLink()%>"/>

    <div class="search-info">
        <a href="details?articleId=<%=article.getId()%>" class="car-title"><%=article.getTitle()%>
        </a>

        <p>Mileage: <%=article.getMileage()%>
        </p>

        <p>Price: <%=article.getPrice()%>
        </p>

        <p>Region: <%=article.getRegion()%>
        </p>
    </div>
</div>
<%
        }

    } else {
        response.sendRedirect("not-found.jsp");
    }
%>
</body>
</html>
