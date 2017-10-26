<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Please Log In</h1>
        <%--<form action="/login" method="POST">--%>
            <div class="row">
                <form action="/login" method="post" class="col s12">
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="icon_prefix" type="text" class="validate">
                            <label for="icon_prefix">First Name</label>
                        </div>
                        <div class="input-field col s6">
                            <i class="material-icons prefix">lock</i>
                            <input id="icon_lock" type="password" class="validate">
                            <label for="icon_lock">password</label>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Log In">
                </form>
            </div>
        <%--</form>--%>
    </div>
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
