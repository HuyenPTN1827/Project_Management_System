<!--BachHD-->
<%@ page import="model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />
        <link rel="canonical" href="pages-sign-in.html" />
        <title>Login | PMS</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>body {
                opacity: 0;
            }</style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <main class="d-flex w-100 h-100">
            <div class="container d-flex flex-column">
                <div class="row vh-100">
                    <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
                        <div class="d-table-cell align-middle">
                            <div class="text-center mt-4">
                                <h1 class="h2">Welcome back</h1>
                                <p class="lead">Sign in to your account to continue</p>
                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <div class="m-sm-4">
                                        <!--                                        <div class="text-center">
                                                                                    <img src="${pageContext.request.contextPath}/img/avatars/avatar.jpg" alt="Charles Hall" class="img-fluid rounded-circle" width="132" height="132" />
                                                                                </div>-->
                                        <form action="<%=request.getContextPath()%>/login" method="post">
                                            <div class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input type="text" class="form-control form-control-lg" id="email" value="${sessionScope.user}" placeholder="Enter your email" name="email" required />
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Password</label>
                                                <input type="password" class="form-control form-control-lg" id="password" placeholder="Enter your password" name="password" required />
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="showPassword" onclick="togglePasswordVisibility()">
                                                    <label class="form-check-label" for="showPassword">Show Password</label>
                                                </div>
                                            </div>
                                            <div style="color: green; font-weight: bold;">
                                                ${SUCCESS}
                                            </div>

                                            <script>
                                                function togglePasswordVisibility() {
                                                    var passwordInput = document.getElementById("password");
                                                    var showPasswordCheckbox = document.getElementById("showPassword");
                                                    if (showPasswordCheckbox.checked) {
                                                        passwordInput.type = "text"; // Hiển thị mật khẩu
                                                    } else {
                                                        passwordInput.type = "password"; // Ẩn mật khẩu
                                                    }
                                                }
                                            </script>


                                            <% 
        String notification = (String) session.getAttribute("NOTIFICATION");
        if (notification != null) {
            out.println("<p style='color:red;'>" + notification + "</p>");
            session.removeAttribute("NOTIFICATION"); // Xóa sau khi hiển thị để tránh lặp lại
        }
                                            %>

                                            <div class="text-center mt-3">
                                                <button type="submit" class="btn btn-lg btn-outline-primary" style="border-radius: 50px; width: 95%">Sign in</button>
                                            </div>
                                            <div style="display: flex; justify-content: space-between; margin: 10px 5px;">
                                                <label class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="remember-me" name="remember-me" checked>
                                                    <span class="form-check-label">Remember me next time</span>
                                                </label>
                                                <small>
                                                    <button type="button" class="btn btn-link" onclick="location.href = '<%=request.getContextPath()%>/forgotpassword'">Forgot password?</button>
                                                </small>
                                            </div>
                                            <div class="text-center mt-3">
                                                <a href="register-form" class="btn btn-lg btn-outline-primary" style="margin-bottom: 10px; border-radius: 50px; width: 95%">Sign up</a>
                                                <br>
                                                <a href="#" class="btn btn-lg btn-outline-primary" style="border-radius: 50px; width: 95%">
                                                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png" width="23px" alt="alt" /> Continues with Google
                                                </a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>

</html>
