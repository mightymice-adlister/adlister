<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <form method="post" action="/profile/edit">
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" value="<c:out value="${user.email}" />" >
        </div>
        <div class="form-group">
            <label for="first_name">First Name</label>
            <input id="first_name" name="first_name" class="form-control" value="<c:out value="${user.first_name}" />">
        </div>
        <div class="form-group">
            <label for="last_name">Last Name</label>
            <input id="last_name" name="last_name" class="form-control" value="<c:out value="${user.last_name}" />">
        </div>
        <div class="form-group">
            <label for="day_number">Daytime Phone Number</label>
            <input id="day_number" name="day_number" class="form-control" value="<c:out value="${user.day_number}" />">
        </div>
        <div class="form-group">
            <label for="evening_number">Evening Phone Number</label>
            <input id="evening_number" name="evening_number" class="form-control" value="<c:out value="${user.evening_number}" />">
        </div>
        <div class="form-group">
            <label for="bio">About me</label>
            <textarea id="bio" name="bio"  class="form-control"><c:out value="${user.bio}" /></textarea>
        </div>
        <br>
        <div class="bottomNav">
            <input type="submit" class="btn btn-primary" value="Edit">
            <a href="/profile" class="btn btn-primary">Cancel</a>
            <a class="waves-effect waves-light btn" href="/profile/password_change">Change password</a>
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
