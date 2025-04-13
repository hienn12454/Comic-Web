<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Chapter Image</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Add Chapter Image</h1>
        <form action="${pageContext.request.contextPath}/chapterimages?action=add" method="post">
            <div class="form-group">
                <label for="imageUrl">Image Url:</label>
                <input type="text" class="form-control" id="imageUrl" name="imageUrl" required>
            </div>
            <input type="hidden" name="chapterId" value="${chapter.chapterId}">
            <input type="hidden" name="orderNumber" value="${orderNumber}">
            <button type="submit" class="btn btn-primary">Add Chapter Image</button>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>