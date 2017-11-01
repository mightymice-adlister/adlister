<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" value="<c:out value="${usernameEntered}"/>" type="text">
                <p class="invalid"><c:out value="${usernameIsUnique}" /></p>
                <p class="invalid"><c:out value="${usernameIsEmpty}" /></p>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" value="<c:out value="${emailEntered}"/>" type="text">
                    <p class="invalid"><c:out value="${emailIsEmpty}" /></p>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" value="<c:out value="${passwordEntered}"/>" class="form-control" type="password">
                    <p class="invalid"><c:out value="${passwordIsEmpty}" /></p>
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                <p class="invalid"><c:out value="${passwordConfirmationIsEmpty}" /></p>
                <p class="invalid"><c:out value="${passwordsDoNotMatch}" /></p>
            </div>
            <input type="submit" class="btn btn-primary btn-block">
        </form>
    </div>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
