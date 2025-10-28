<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer class="footer mt-5 py-4" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; margin-top: auto !important;">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h5><i class="fas fa-book"></i> Comic Web</h5>
                <p>Your ultimate destination for comics and manga. Explore amazing stories, discover new adventures, and get lost in incredible worlds.</p>
            </div>
            <div class="col-md-3">
                <h5>Quick Links</h5>
                <ul style="list-style: none; padding: 0;">
                    <li><a href="home" style="color: white;"><i class="fas fa-home"></i> Home</a></li>
                    <c:if test="${sessionScope.user == null}">
                        <li><a href="main?action=login" style="color: white;"><i class="fas fa-sign-in-alt"></i> Login</a></li>
                        <li><a href="main?action=register" style="color: white;"><i class="fas fa-user-plus"></i> Register</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="col-md-3">
                <h5>Connect With Us</h5>
                <div style="font-size: 1.5rem;">
                    <a href="#" style="color: white; margin-right: 15px;"><i class="fab fa-facebook"></i></a>
                    <a href="#" style="color: white; margin-right: 15px;"><i class="fab fa-twitter"></i></a>
                    <a href="#" style="color: white; margin-right: 15px;"><i class="fab fa-instagram"></i></a>
                    <a href="#" style="color: white;"><i class="fab fa-github"></i></a>
                </div>
            </div>
        </div>
        <hr style="background: rgba(255,255,255,0.3);">
        <div class="text-center">
            <p class="mb-0">&copy; 2024 Comic Web. All rights reserved. Made with <i class="fas fa-heart" style="color: #fd79a8;"></i></p>
        </div>
    </div>
</footer>

