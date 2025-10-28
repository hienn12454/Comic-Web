<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Comic Web</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="auth-container">
        <div class="auth-card">
            <div class="auth-card-header">
                <h2><i class="fas fa-user-plus"></i> Register</h2>
                <p class="mb-0">Create your Comic Web account</p>
            </div>
            <div class="auth-card-body">
                <form action="main" method="post">
                    <div class="form-group">
                        <label for="userName"><i class="fas fa-user"></i> Username</label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter your username" required>
                    </div>
                    <div class="form-group">
                        <label for="email"><i class="fas fa-envelope"></i> Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                    </div>
                    <div class="form-group">
                        <label for="password"><i class="fas fa-lock"></i> Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <input type="hidden" name="action" value="register">
                    <div class="d-flex" style="gap: 10px;">
                        <button type="submit" class="btn btn-primary flex-fill">
                            <i class="fas fa-user-check"></i> Register
                        </button>
                        <a href="main?action=login" class="btn btn-danger">
                            <i class="fas fa-sign-in-alt"></i> Login
                        </a>
                    </div>
                    <c:if test="${not empty mess}">
                        <div class="alert-message alert-error mt-3">
                            <i class="fas fa-exclamation-circle"></i> ${mess}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
