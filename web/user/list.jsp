<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="adminHeader.jsp" />
    <div class="container mt-5">
        <h1>User List</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Active</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>${user.isActive ? true : false}</td>
                        <td>
                            <a href="users?action=edit&userId=${user.userId}" class="btn btn-warning btn-sm">Edit</a>
                            <c:if test="${user.isActive == false}">
                                <a href="users?action=delete&userId=${user.userId}&active=1" class="btn btn-success btn-sm">Active</a>
                            </c:if>
                             <c:if test="${user.isActive == true}">
                                <a href="users?action=delete&userId=${user.userId}&active=0" class="btn btn-success btn-sm">Disable</a>
                            </c:if>
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>