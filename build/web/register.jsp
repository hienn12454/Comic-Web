<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="main" method="post">
            <div class="form-group">
                <label for="userName">Username:</label>
                <input type="text" class="form-control" id="userName" placeholder="Enter username" name="userName">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            </div>
            <input type="hidden" name="action" value="register">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a style="text-decoration: none" href="main?action=login" class="btn btn-danger">Login</a>
            <span style="color: red">${mess}</span>
        </form>
    </div>
</body>
</html>