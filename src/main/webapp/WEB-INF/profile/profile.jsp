<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <h1>Welcome, <c:out value="${user.username}"/>!</h1>
        <h3>Current email is <c:out value = "${user.email}"/></h3>
        <a class="button" href="/profile/edit">Edit profile</a>
        <a class="button" href="/profile/ads">View ads</a>
    </div>

</body>
</html>
