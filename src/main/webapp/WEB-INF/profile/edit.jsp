
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
            <label for="email">Change Email</label>
            <input id="email" name="email" class="form-control" value="${user.email}">
            <input type="submit" class="btn btn-primary" value="Edit">
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
