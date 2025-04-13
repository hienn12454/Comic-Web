<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Story List</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-....." crossorigin="anonymous" />
    </head>
    <body>
        <jsp:include page="adminHeader.jsp" />
        <div class="container mt-5">
            <h1>Story List</h1>
            <a href="${pageContext.request.contextPath}/stories?action=add" class="btn btn-info btn-sm" style="margin-bottom: 10px; background-color: #17b849">Add Story</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Story ID</th>
                        <th>Title</th>
                        <th>Image</th>
                        <th>Author ID</th>
                        <th>Genre ID</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="story" items="${stories}">
                        <tr>
                            <td>${story.storyId}</td>
                            <td>${story.title}</td>
                            <td><img src="${story.image}" class="card-img-top" alt="..."></td>
                            <td>${story.authorID}</td>
                            <td>${story.genreID}</td>
                            <td>${story.status}</td>
                            <td>${story.createdAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/stories?action=view&storyId=${story.storyId}" class="btn btn-info btn-sm">View</a>
                                <a href="${pageContext.request.contextPath}/stories?action=edit&storyId=${story.storyId}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="${pageContext.request.contextPath}/stories?action=delete&storyId=${story.storyId}" class="btn btn-danger btn-sm">Delete</a>
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