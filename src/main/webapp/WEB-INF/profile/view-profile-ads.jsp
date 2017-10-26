<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here are all the ads from <c:out value="${user.username}"/></h1>
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <a href="/ads/view?id=<c:out value="${ad.id}"/>"><h2><c:out value = "${ad.title}"/></h2></a>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</div>

</body>
</html>