<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text">
                <p><c:out value="${titleIsEmpty}" /></p>
            </div>
            <div class="form-group">
                <div class="input-field col s12 m6">
                    <select class="icons" name="catId">
                        <option disabled selected>Category</option>
                        <option value="1" data-icon="https://source.unsplash.com/collection/632477/" class="circle">Beauty</option>
                        <option value="2" data-icon="https://source.unsplash.com/collection/190727/" class="circle">Jobs</option>
                        <option value="3" data-icon="https://source.unsplash.com/collection/353844/" class="circle">Electronics</option>
                    </select>
                </div>
                <p><c:out value="${catIdIsEmpty}" /></p>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"></textarea>
                <p><c:out value="${descriptionIsEmpty}" /></p>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>

    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
