<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${user.username}!</h1>
        <h3>Current email is ${user.email}</h3>
        <p>${passChange}</p>
        <a class="button" href="/profile/edit">Edit profile</a>
        <a class="button" href="/profile/password_change">Change password</a>
    </div>

</body>
</html>
