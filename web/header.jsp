<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/style.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
    <a class="navbar-brand" href="home">Comic Web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="main">Home</a>
            </li>
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="main?action=login">Login</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                 <li class="nav-item">
                <a class="nav-link" href="main?action=logout">Logout</a>
            </li>
            </c:if>
           
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="main?action=register">Register</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user != null && sessionScope.user.role == 'Admin'}">
                <li class="nav-item">
                    <a class="nav-link" href="main?action=user">Admin Page</a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="home" method="get">
            <input class="form-control mr-sm-2" type="search" placeholder="Search stories" aria-label="Search" name="q" value="${param.q}">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>