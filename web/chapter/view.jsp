<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Chapter ${chapter.chapterNumber}</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <a href="${pageContext.request.contextPath}/stories?action=view&storyId=${chapter.storyId}" class="btn btn-info btn-sm" style="margin-bottom: 10px; background-color: #17b849">Back</a>
            <h1>Chapter: ${chapter.chapterNumber}</h1>
            <a href="${pageContext.request.contextPath}/chapterimages?action=add&chapterId=${chapter.chapterId}" class="btn btn-info btn-sm" style="margin-bottom: 10px; background-color: #17b849">Add Chapter Image</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="text-align: center">${chapter.title}</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ci" items="${listCI}">
                        <tr>
                            <td style="text-align: center"><img src="${ci.imageUrl}" class="card-img-top" alt="..."></td>
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