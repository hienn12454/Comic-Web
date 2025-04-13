<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">My Website</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="main?action=user">User Management</a>
            </li>
           
            <li class="nav-item">
                <a class="nav-link" href="main?action=story">Story Management</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?action=logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>