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
                            <c:if test="${user.first_name != null && user.first_name !=''}">
                                <p><strong>First Name</strong>: <c:out value="${user.first_name}"/> </p>
                                <br>
                            </c:if>
                            <c:if test="${user.last_name != null && user.last_name !=''}">
                                <p><strong>Last Name:</strong> <c:out value="${user.last_name}"/> </p>
                                <br>
                            </c:if>
                                <p><strong>Email:</strong> <c:out value = "${user.email}"/></p>
                                <br>
                            <c:if test="${user.day_number != null && user.day_number != ''}">
                                <p><strong>Daytime Phone Number:</strong> <c:out value="${user.day_number}"/></p>
                                <br>
                            </c:if>
                            <c:if test="${user.evening_number != null && user.evening_number !=''}">
                                <p><strong>Evening Phone Number:</strong> <c:out value="${user.evening_number}"/></p>
                                <br>
                            </c:if>
                            <c:if test="${user.bio != null && user.bio !=''}">
                                <p><strong>About me:</strong> <c:out value="${user.bio}"/></p>
                                <br>
                            </c:if>
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
