<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<main>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <div class="row">
            <div class="col s12">
                <h1 class="center-align">"Ads" List</h1>
            </div>
        </div>
        <div class="row">
        <c:forEach var="ad" items="${ads}">
            <c:set var="ad" value="${ad}" scope="request"/>
            <jsp:include page="/WEB-INF/partials/ads-partial-short.jsp" />
        </c:forEach>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
