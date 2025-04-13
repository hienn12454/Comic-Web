<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Story</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
     <jsp:include page="adminHeader.jsp" />
    <div class="container mt-5">
        <h1>Edit Story</h1>
        <form action="${pageContext.request.contextPath}/stories?action=edit" method="post">
            <input type="hidden" name="storyId" value="${story.storyId}">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${story.title}" required>
            </div>
            <div class="form-group">
                <label for="authorId">Author ID:</label>
                <input type="number" class="form-control" id="authorId" name="authorId" value="${story.authorID}" required>
            </div>
            <div class="form-group">
                <label for="genreId">Genre ID:</label>
                <input type="number" class="form-control" id="genreId" name="genreId" value="${story.genreID}" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" cols="50">${story.description}</textarea>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <input type="text" class="form-control" id="status" name="status" value="${story.status}" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="text" class="form-control" id="image" name="image" value="${story.image}">
            </div>
            <button type="submit" class="btn btn-primary">Update Story</button>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>