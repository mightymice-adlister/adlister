<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit your Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />

<div class="container">

    <h1>Edit your Ad</h1>

    <form action="/ads/edit" method="post">
            <label for="title">Title</label>
            <input id="title"
                   name="title"
                   value="<c:out value="${ad.title}"/>"
                   type="text">
            <p><c:out value="${titleIsEmpty}" /></p>

            <div class="input-field col s12 m6">
                <select class="icons" name="catId">

                    <option disabled selected>Category</option>

                    <option <c:if test="${ad.catId == 1}">selected </c:if>
                            value="1"
                            data-icon="https://source.unsplash.com/collection/632477/" class="circle">Beauty</option>

                    <option <c:if test="${ad.catId == 2}">selected </c:if>
                            value="2"
                            data-icon="https://source.unsplash.com/collection/190727/" class="circle">Jobs</option>

                    <option <c:if test="${ad.catId == 3}">selected </c:if>
                            value="3"
                            data-icon="https://source.unsplash.com/collection/353844/" class="circle">Electronics</option>

                </select>
            <p><c:out value="${catIdIsEmpty}" /></p>
        </div>

            <label for="description">Description</label>
            <textarea id="description"
                      name="description"
                      type="text"><c:out value="${ad.description}"/>
            </textarea>
            <p><c:out value="${descriptionIsEmpty}" /></p>
        <input type="hidden" name="id" value="${ad.id}">
            <button type="submit" class="waves-effect waves-light btn">Edit</button>

    </form>
</div>
<jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
