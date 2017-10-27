<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<nav>
        <div class="nav-wrapper teal lighten-2">
            <div class="col s3 offset-s2">
                <c:if test="${sessionScope.user.username != null}">
                    <span class="user-text left">Welcome, <c:out value="${sessionScope.user.username}" /></span>
                </c:if>
            </div>
            <div class="col s2 center-align">
                <a href="/login" class="brand-logo center"><span class="hoverable">Shamlister
                <span class="point-logo">!</span></span></a>

            </div>
            <div class="col s5">
                <ul id="nav-mobile" class="right hide-on-sm-and-down">

                        <ul id="dropdown1" class="dropdown-content">
                            <li><a>Ads</a></li>
                            <li class="divider"></li>
                            <li><a href="/ads/create">Create</a></li>
                            <li><a href="/ads/search">Search</a></li>
                            <li><a href="/ads/view">View All</a></li>
                        </ul>
                                <ul class="right hide-on-med-and-down">
                                    <li><a href="/login">Login</a></li>
                                    <li><a href="/logout">Logout</a></li>
                                    <li><a href="/register">Register!</a></li>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.username == null}">
                                            <li><a href="/ads/view">Ads</a></li>
                                        </c:when>

                                        <c:otherwise>
                                            <!-- Dropdown Trigger -->
                                            <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Ads<i class="material-icons right">arrow_drop_down</i></a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>


                </ul>
            </div>
        </div>

        <%--<div class="nav-content teal lighten-3">--%>
        <%--<span class="nav-title">Welcome, <c:out value="${sessionScope.user.username}" /></span>--%>
        <%--<a class="btn-floating left btn-large halfway-fab waves-effect waves-light teal">--%>
            <%--<i class="material-icons">add</i>--%>
        <%--</a>--%>
    <%--</div>--%>
    <%--</c:if>--%>



</nav>
</header>