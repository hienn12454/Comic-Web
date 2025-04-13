<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Story</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
     <jsp:include page="adminHeader.jsp" />
    <div class="container mt-5">
        <h1>Add Story</h1>
        <form action="${pageContext.request.contextPath}/stories?action=add" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="authorId">Author:</label>
                </br>
                <select style="padding:5px; width: 300px" name="authorId">
                    <c:forEach var="auth" items="${listAuthor}">
                        <option value="${auth.authorId}">${auth.authorName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="genreId">Genre ID:</label>
                </br>
                <select style="padding: 5px; width: 300px" name="genreId">
                    <c:forEach var="genre" items="${listGenre}">
                        <option value="${genre.genreId}">${genre.genreName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <input type="text" class="form-control" id="status" name="status" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="text" class="form-control" id="image" name="image">
            </div>
            <button type="submit" class="btn btn-primary">Add Story</button>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>