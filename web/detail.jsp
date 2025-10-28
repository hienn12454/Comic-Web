<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <title>${story.title} - Comic Web</title>
</head>
<body>
     <jsp:include page="header.jsp" />
    <div class="container">
        <div class="story-detail-container">
            <div class="row">
                <div class="col-md-4">
                    <div class="story-image-container">
                        <img src="${story.image}" class="story-image" alt="${story.title}">
                        <a href="${pageContext.request.contextPath}/readStoryController?storyId=${story.storyId}" style="text-decoration: none;">
                            <button class="read-button w-100 mt-4">
                                <i class="fas fa-book-open"></i> Read Now
                            </button>
                        </a>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="story-info">
                        <h1 class="story-title">${story.title}</h1>
                        <div class="story-meta">
                            <span class="meta-item"><i class="fas fa-user"></i> ${story.authorName}</span>
                            <span class="meta-item"><i class="fas fa-tag"></i> ${story.genreName}</span>
                            <c:choose>
                                <c:when test="${story.status == 'Completed'}">
                                    <span class="meta-item badge-status badge-completed"><i class="fas fa-check-circle"></i> Completed</span>
                                </c:when>
                                <c:when test="${story.status == 'Ongoing'}">
                                    <span class="meta-item badge-status badge-ongoing"><i class="fas fa-clock"></i> Ongoing</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="meta-item badge-status badge-hiatus"><i class="fas fa-pause-circle"></i> ${story.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="story-description">
                            <h5><i class="fas fa-info-circle"></i> Description</h5>
                            <p>${story.description}</p>
                        </div>
                        <div class="mt-4">
                            <p class="text-muted"><i class="far fa-calendar"></i> Released: 
                                <fmt:formatDate value="${story.createdAt}" pattern="dd MMMM yyyy" />
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
