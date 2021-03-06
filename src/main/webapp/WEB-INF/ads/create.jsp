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
<%%>
    <div class="container">

        <h1>Create a new Ad</h1>

        <form action="/ads/create" method="post">
                <label for="title">Title</label>
                <input id="title"
                       name="title"
                       value="<c:out value="${titleEntered}"/>" type="text">
                <p><c:out value="${titleIsEmpty}" /></p>

                <div class="input-field col s12">
                    <select name="catIds" multiple>
                        <option value="" disabled selected>Choose your option</option>
                        <c:forEach var="category" items="${categories}">
                            <option name="catIds" value="<c:out value="${category.id}"/>"
                                    <%--<c:if test="${catIdsEntered != null}">--%>
                                    <c:forEach var="catIdEntered" items="${catIdsEntered}">

                                        <c:if test="${category.id == catIdEntered}">
                                            selected
                                        </c:if>
                                    </c:forEach>
                                    <%--</c:if>--%>
                            >${category.name}</option>
                        </c:forEach>
                    </select>
                    <label>Select Your Category/Categories</label>
                </div>


                <p><c:out value="${catIdIsEmpty}" /></p>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"><c:out value="${descriptionEntered}"/></textarea>
                <p><c:out value="${descriptionIsEmpty}" /></p>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>

    </div>

    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/materializejs.jsp" />
</body>
</html>
