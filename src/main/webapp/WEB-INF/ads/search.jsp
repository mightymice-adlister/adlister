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
<div class="container">
    <div class="row">
        <div class="col s2"></div>
        <div class="col s8">
            <form action="search" method="get">
                <input type="text" name="search-query" value="<c:out value="${sessionScope.previousSearch}"/>">
                <input type="submit" placeholder="search">
            </form>
        </div>
        <div class="col s2"></div>

    </div>
    <div class="row">
        <table class="centered highlight">
            <thead>
            <tr>
                <th>Ad Title</th>
                <th>Description</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="ad" items="${ads}">
                <c:choose>
                    <c:when test="${ad.id == 0}">
                        <h2><c:out value = "${ad.title}"/></h2>
                    </c:when>
                    <c:otherwise>
                        <%--// Make a table to display the ad links--%>
                        <tr>
                            <td>
                                <a href="/ads/view?id=<c:out value="${ad.id}"/>">
                                    <c:out value = "${ad.title}"/>
                                </a>
                            </td>
                            <td><c:out value = "${ad.description}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>


</div>


<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>