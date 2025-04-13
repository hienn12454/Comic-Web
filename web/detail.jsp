<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Story Detail</title>
    <style>
        .story-image {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }
        .card {
            margin-top: 20px;
        }
    </style>
</head>
<body>
     <jsp:include page="header.jsp" />
    <div class="container">
        <h1 class="my-4">Story Detail</h1>
        <div class="card">
            <img src="${story.image}" class="card-img-top story-image" alt="${story.title}" style="width: 400px;height: 400px">
            <a href="${pageContext.request.contextPath}/readStoryController?storyId=${story.storyId}"><button style="margin-left: 170px; margin-top: 20px; background-color: #28c4f3; border-radius: 5px" type="submit">Read</button></a>
            <div class="card-body">
                <h5 class="card-title">${story.title}</h5>
                <p class="card-text">${story.description}</p>
                <p class="card-text"><strong>Author:</strong> ${story.authorName}</p>
                <p class="card-text"><strong>Genre:</strong> ${story.genreName}</p>
                <p class="card-text"><strong>Status:</strong> ${story.status}</p>
                <p class="card-text"><strong>Created At:</strong> ${story.createdAt}</p>
                <!-- Add more details as needed -->
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>