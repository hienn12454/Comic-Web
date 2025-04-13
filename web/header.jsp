<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">My Website</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
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
           
            <li class="nav-item">
                <a class="nav-link" href="main?action=register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?action=user">Admin Page</a>
            </li>
        </ul>
    </div>
</nav>