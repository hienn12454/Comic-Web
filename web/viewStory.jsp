<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Story</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <a href="${pageContext.request.contextPath}/main?action=detail&storyId=${story.storyId}" class="btn btn-info btn-sm" style="margin-bottom: 10px; background-color: #17b849">Back</a>
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <td style="text-align: center"><img src="${story.image}" class="card-img-top" style="height: 200px; width: auto" alt="..."></td>
                    </tr>
                    <tr>
                        <td style="text-align: center"><h3>${story.title}</h3></td>
                    </tr>
                </tbody>
            </table>
            <h1>Chapter List</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Chapter ID</th>
                        <th>Story ID</th>
                        <th>Chapter Number</th>
                        <th>Title</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="chapter" items="${chapters}">
                        <tr>
                            <td>${chapter.chapterId}</td>
                            <td>${chapter.storyId}</td>
                            <td>${chapter.chapterNumber}</td>
                            <td>${chapter.title}</td>
                            <td>${chapter.createdAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/readChapterController?chapterId=${chapter.chapterId}" class="btn btn-info btn-sm">View</a>
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