<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles/register-style.css">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>

</head>
<body>
<ul>
    <div class="navigation-bar">
        <% if (request.getSession().getAttribute("id") != null) { %>
        <form method="post" action="/logout">
            <input type="submit" value="Log Out" class="logout">
        </form>
        <li><a href="updateUser.jsp">Account settings</a></li>
        <li><a href="create_article.jsp">Add article</a></li>
        <% } %>
        <% if (request.getSession().getAttribute("id") == null) { %>
        <li><a class="active" href="register.jsp">Register</a></li>
        <li><a href="login.jsp">Login</a></li>
        <% } %>
        <li><a href="index.jsp">Home</a></li>
        <img class="car-logo" src="styles/car-logo.png">
        <h2>Car Deals</h2>
    </div>
</ul>
<h1>━━━━━━━━ Register ━━━━━━━━</h1>
<h3>Create your account. It's free and only takes a minute.</h3>

<div class="register-form">
    <form method="post" action="register">

        <label>Username:</label>
        <input type="text" name="reg-username">


        <label>Password:</label>
        <input type="password" name="reg-pass">

        <label>Repeat Password:</label>
        <input type="password" name="reg-repeat-pass">

        <label>Address:</label>
        <input type="text" name="reg-address">

        <label>Phone Number:</label>
        <input type="text" name="reg-phone">

        <br>
        <input class="reg-button" type="submit" name="reg-submit" value="Register">
        <br>

    </form>

    <p class="error-messages">
        <%=request.getAttribute("matching-passes-error") == null ? "" : request.getAttribute("matching-passes-error")%>
        <%=request.getAttribute("empty-fields-error") == null ? "" : request.getAttribute("empty-fields-error")%>
    </p>
</div>
</body>
</html>
