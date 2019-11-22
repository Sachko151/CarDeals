<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="style/login-css.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/navigation_bar.css"/>
    <link rel="stylesheet" type="text/css" href="style/login.css">

    <title>Login</title>
</head>
<body>
<ul>
    <div class="navigation-bar">
        <% if (request.getSession().getAttribute("id") != null) { %>
        <form method="post" action="/logout">
            <input type="submit" value="Log Out" class="logout">
        </form>
        <li><a  href="updateUser.jsp">Account settings</a></li>
        <li><a href="create_article.jsp">Add article</a></li>
        <% } %>
        <% if (request.getSession().getAttribute("id") == null) { %>
        <li><a href="register.jsp">Register</a></li>
        <li><a class="active" href="login.jsp">Login</a></li>
        <% } %>
        <li><a href="index.jsp">Home</a></li>
        <img class="car-logo" src="styles/car-logo.png">
        <h2>Car Deals</h2>
    </div>
</ul>
<h1>━━━━━━━━ Login ━━━━━━━━</h1>
<p id="inputWithEmail">Login with Email and password:</p>

<div class="login-form">
    <form method="post" action="/login">
        <input type="text" name="username" id="username" placeholder="E-mail or Username" required="true" onblur="check(this.value)"/>
        <p id="errorname"></p>
        <input type="password" name="password" id="pass" placeholder="Password" required="true" onblur="check(this.value)"/>
        <p id="errorpass"></p>
        <input id="inputButton" type="submit" value="Login">
    </form>
</div>

<script type="text/javascript" src="script/login-javaScript.js"></script>

</body>
</html>