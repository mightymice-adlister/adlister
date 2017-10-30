<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <c:choose>
        <c:when test="${sessionScope.user.username == null}">
            <%--If user is not logged in--%>
        <nav>
            <div class="nav-wrapper teal lighten-2">
                <div class="col s3 offset-s2">
                </div>
                <div class="col s2 center-align">
                    <a href="/ads" class="brand-logo center"><span class="hoverable">Shamlister
                    <span class="point-logo">!</span></span></a>
                </div>
                <div class="col s5">
                    <ul class="right hide-on-sm-and-down">
                        <ul class="right hide-on-med-and-down">
                            <li><a href="/login">Login</a></li>
                            <li><a href="/register">Register!</a></li>
                            <li><a href="/ads">Ads</a></li>
                        </ul>
                    </ul>
                </div>
            </div>
        </nav>
        </c:when>
        <c:otherwise>
           <%--If the user is logged in--%>
    <nav class="nav-extended">
        <div class="nav-wrapper teal lighten-2">
            <div class="col s3 offset-s2">
                    <span class="user-text left">Welcome, <c:out value="${sessionScope.user.username}" /></span>
            </div>
            <div class="col s2 center-align">
                <a href="/login" class="brand-logo center"><span class="hoverable">Shamlister
                <span class="point-logo">!</span></span></a>

            </div>
            <div class="col s5">
                <ul class="right hide-on-sm-and-down">

                    <ul id="dropdown1" class="dropdown-content">
                        <li><a>Ads</a></li>
                        <li class="divider"></li>
                        <li><a href="/ads/create">Create</a></li>
                        <li><a href="/ads/search">Search</a></li>
                        <li><a href="/ads/view">View All</a></li>
                    </ul>
                    <ul class="right hide-on-med-and-down">
                        <li><a class="custom-chip-link" href="/profile"><div class="chip custom-chip"><img src="https://source.unsplash.com/collection/159213/300x300?sig="><c:out value="${user.username}"/></div></a></li>
                        <li><a href="/profile">Profile</a></li>
                        <li><a href="/logout">Logout</a></li>
                        <!-- Dropdown Trigger -->
                        <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Ads<i class="material-icons right">arrow_drop_down</i></a></li>
                    </ul>
                </ul>
            </div>
        </div>
    </nav>
        </c:otherwise>
    </c:choose>



















</nav>
</header>