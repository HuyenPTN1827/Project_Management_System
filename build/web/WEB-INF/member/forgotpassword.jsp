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

            <!-- Form nhập email -->
            <form id="email-form" action="<%=request.getContextPath()%>/forgotpassword" method="post">
                <div class="form-group">
                    <label for="email">Nhập email của bạn:</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
                </div>
                ${errorMessage}<br>
                <button type="submit" class="btn btn-primary">Khôi phục mật khẩu</button>
            </form>

          

        </div>
    </body>
</html>
