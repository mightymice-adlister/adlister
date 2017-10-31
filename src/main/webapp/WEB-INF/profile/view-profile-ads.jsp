<%@ page import="com.codeup.adlister.models.Ad" %>
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
<c:choose>
    <c:when test="${ad.id !=0}">
        <c:set var="ad" value="${ad}" scope="request"/>
        <jsp:include page="/WEB-INF/partials/ads-partial-short.jsp" />
    </c:when>

    <c:otherwise>
            <h3><c:out value="${user.username}"/> has no active ads.</h3>
    </c:otherwise>
</c:choose>
    </c:forEach>
</div>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
