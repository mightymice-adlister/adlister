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
                <div class="col s6 m7">
                    <div class="card">
                        <div class="card-image">
                            <img src="https://source.unsplash.com/collection/190727/">
                            <span class="card-title"><c:out value = "${ad.title}"/></span>
                        </div>
                        <div class="card-content">
                            <p>${ad.description}</p>
                        </div>
                        <div class="card-action">
                            <a href="/ads/view?id=<c:out value="${ad.id}" />">View More</a>
                        </div>
                    </div>
                </div>
        </c:forEach>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
