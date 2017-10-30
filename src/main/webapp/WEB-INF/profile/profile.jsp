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
        <div class="row">
            <div class="col s12">
                <h1>Welcome, <c:out value="${user.username}"/>!</h1>
            </div>
        </div>
        <div class="row">
            <div class="col s12 m7 l7">
                <img src="https://source.unsplash.com/collection/159213/300x300?sig=">
            </div>
                <div class="col s12 m5 l5">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title center-align">Contact info</span>
                            <p>First Name: <c:out value="${user.first_name}"/> </p>
                            <br>
                            <p>Last Name: <c:out value="${user.last_name}"/> </p>
                            <br>
                            <p>Email: <c:out value = "${user.email}"/></p>
                            <br>
                            <p>Daytime Phone Number: <c:out value="${user.day_number}"/></p>
                            <br>
                            <p>Evening Phone Number: <c:out value="${user.evening_number}"/></p>
                            <br>
                            <p>About me: <c:out value="${user.bio}"/></p>
                            <br>
                        </div>
                        <div class="card-action">
                            <a class="button" href="/profile/edit">Edit profile</a>
                            <a class="left-align" href="/profile/ads">View ads</a>

                        </div>
                    </div>
                </div>
        </div>

        <p><c:out value = "${passChange}"/></p>

    </div>

    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
