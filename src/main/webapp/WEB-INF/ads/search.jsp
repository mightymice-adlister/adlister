<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Search For Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<form action="search" method="get">
    <input type="text" name="search-query" value="<c:out value="${sessionScope.previousSearch}"/>">
    <input type="submit" placeholder="search">
</form>

<c:forEach var="ad" items="${ads}">
    <c class="col-md-6">
        <c:choose>
        <c:when test="${ad.id == 0}"><h2><c:out value = "${ad.title}"/></h2></c:when>
            <c:otherwise><a href="/ads/view?id=<c:out value="${ad.id}"/>"><h2><c:out value = "${ad.title}"/></h2></a></c:otherwise>
            </c:choose>
        <p><c:out value = "${ad.description}"/></p>
    </div>
</c:forEach>

</body>
</html>