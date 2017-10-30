<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col s6 m6 l6">
    <div class="card">
        <div class="card-image">
            <img src="https://source.unsplash.com/collection/190727/350x350?sig=${ad.id}">
            <span class="card-title"><c:out value = "${ad.title}"/></span>
        </div>
        <div class="card-content">

            <p class="truncate">${ad.description}</p>
        </div>
        <div class="card-action">
            <a href="/ads/view?id=<c:out value="${ad.id}" />">View More</a>
        </div>
    </div>
</div>
