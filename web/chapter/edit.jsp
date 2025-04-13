<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Chapter</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Edit Chapter</h1>
        <form action="${pageContext.request.contextPath}/chapters?action=edit" method="post">
            <input type="hidden" name="chapterId" value="${chapter.chapterId}">
            <div class="form-group">
                <label for="storyId">Story ID:</label>
                <input type="number" class="form-control" id="storyId" name="storyId" value="${chapter.storyId}" readonly>
            </div>
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${chapter.title}" required>
            </div>
            
            <div class="form-group">
                <label for="chapterNumber">Chapter Number:</label>
                <input type="text" class="form-control" id="chapterNumber" name="chapterNumber" value="${chapter.chapterNumber}" readonly>
            </div>
            <button type="submit" class="btn btn-primary">Update Chapter</button>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>