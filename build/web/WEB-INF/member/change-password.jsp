<%-- 
    Document   : change-password
    Created on : Oct 1, 2024, 8:00:40 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đổi Mật Khẩu</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Thay đổi đường dẫn nếu cần -->
</head>
<body>
    <div class="container">
        <h2>Đổi Mật Khẩu</h2>
        <form action="changepasswordcontroller" method="post"> <!-- Đường dẫn đến Servlet xử lý đổi mật khẩu -->
            <div class="form-group">
                <label for="oldPassword">Mật khẩu hiện tại:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            <div class="form-group">
                <label for="newPassword">Mật khẩu mới:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Đổi Mật Khẩu">
            </div>
            <c:if test="${not empty param.error}">
                <div class="error-message">
                    <p>${param.error}</p>
                </div>
            </c:if>
            <c:if test="${not empty param.success}">
                <div class="success-message">
                    <p>${param.success}</p>
                </div>
            </c:if>
        </form>
    </div>
</body>
</html>

