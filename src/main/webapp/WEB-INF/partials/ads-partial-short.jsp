
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row">
    <div class="col s3">
        <c:if test="${sessionScope.user.id == ad.userId}">
            <form method="post" action="/profile/ads">
                <button type="submit"
                        class="waves-effect waves-light red lighten-1 btn"
                        name="deleteId"
                        value="${ad.id}">Delete</button>
            </form>
            <form method="post" action="/ads/edit">
                <button type="submit"
                        name="editId"
                        value="${ad.id}"
                        class="waves-effect waves-light btn">Edit</button>
            </form>
        </c:if>
    </div>
    <div class="col s6">
        <div class="card small">
            <div class="card-image">
                <img src="https://source.unsplash.com/collection/190727/350x350?sig=${ad.id}">
                <span class="card-title"><c:out value = "${ad.title}"/></span>
            </div>
            <div class="card-content">

                <p class="truncate"><c:out value="${ad.description}"/></p>

            </div>
            <div class="card-action">
                <a href="/ads/view?id=<c:out value="${ad.id}" />">View More</a>
            </div>
        </div>
    </div>
    <div class="col s3"></div>

</div>
