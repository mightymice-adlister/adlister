<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*"%>
<html>
<head>

    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>

</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <main>
        <div class="container">
        <h1 class="center-align">Please Log In</h1>
        <%--<form action="/login" method="POST">--%>
        <div class="row">
            <form action="/login" method="POST" class="col s12 center-align">
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="icon_prefix" type="text" class="validate" name="username">
                        <label for="icon_prefix">First Name</label>
                        <p><c:out value="${usernameIsEmpty}" /></p>
                    </div>
                    <div class="input-field col s6">
                        <i class="material-icons prefix">lock</i>
                        <input id="icon_lock" type="password" class="validate" name="password">
                        <label for="icon_lock">password</label>
                        <p><c:out value="${passwordIsEmpty}" /></p>
                    </div>
                </div>
                <button type="submit" class="waves-effect waves-light btn-large">Login!</button>
            </form>
        </div>
        <%--</form>--%>
        </div>
    </main>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
