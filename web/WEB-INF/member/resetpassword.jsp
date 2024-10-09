<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Khôi phục mật khẩu</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container col-md-6 offset-md-3" style="padding-top: 20px;">
            <h1>Khôi phục mật khẩu</h1>

            <!-- Form nhập OTP và mật khẩu mới (ẩn lúc đầu) -->
            <form id="otp-form" action="<%=request.getContextPath()%>/resetpassword" method="post" style="display: none;">
                <div class="form-group">
                    <label for="otp">Nhập mã OTP:</label>
                    <input type="text" class="form-control" id="otp" name="otp" placeholder="Nhập mã OTP" required>
                </div>

                <div class="form-group">
                    <label for="new-password">Mật khẩu mới:</label>
                    <input type="password" class="form-control" id="new-password" name="newPassword" placeholder="Nhập mật khẩu mới" required>
                </div>

                <div class="form-group">
                    <label for="confirm-password">Xác nhận mật khẩu:</label>
                    <input type="password" class="form-control" id="confirm-password" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                </div>
                ${errorMessage}<br>
                <button type="submit" class="btn btn-success">Cập nhật mật khẩu</button>
            </form>

            <%-- Script để chuyển đổi form khi OTP đã được gửi thành công --%>
            
                <script>
                    document.getElementById('otp-form').style.display = 'block';
                    // Thông báo cho người dùng rằng OTP đã được gửi
                    alert("OTP đã được gửi đến email của bạn.");
                </script>
            
        </div>
    </body>
</html>
