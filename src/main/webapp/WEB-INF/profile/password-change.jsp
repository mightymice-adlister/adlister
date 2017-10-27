<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Change Password" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <p>${incompleteForm}</p>
    <p>${invalidInput}</p>
        <form action="/profile/password_change" method="post">
            <div class="form-group">
                <label for="password">Current Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <div class="form-group">
                <label for="new_password">New Password</label>
                <input id="new_password" name="new_password" class="form-control" type="password">
            </div>
            <div class="form-group">
                <label for="confirm_new_password">Confirm Password</label>
                <input id="confirm_new_password" name="confirm_new_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block">
        </form>
</div>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
