<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--BachHD-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="PMS">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />
        <link rel="canonical" href="pages-sign-up.html" />

        <title>Reset Password | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <script src="js/settings.js"></script>
        <style>
            body {
                opacity: 0;
            }
        </style>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <main class="d-flex w-100 h-100">
            <div class="container d-flex flex-column">
                <div class="row vh-100">
                    <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
                        <div class="d-table-cell align-middle">
                            <div class="text-center mt-4">
                                <div>
                                    <img src="${pageContext.request.contextPath}/img/logo/PMSLogo-big.jpg" width="100px" style="border-radius: 50%;" alt="logo" />
                                </div>
                                <h1 class="h2">Reset Password</h1>
                                <p class="lead">
                                    Already have account? <a style="color: black;" href="">Back to Sign in</a>
                                </p>
                            </div>
                            <div class="card">
                                <div class="card-body row">
                                    <div class="col-md-12">
                                        <form id="otp-form" action="<%=request.getContextPath()%>/resetpassword" method="post">
                                            <div class="mb-3" style="margin-top: 30px;">
                                                <label class="form-label">Enter OTP:</label>
                                                <input class="form-control form-control-lg" type="text" name="otp" placeholder="Enter the OTP" required />
                                            </div>
                                            <div class="mb-3" style="margin-top: 30px;">
                                                <label class="form-label">New Password:</label>
                                                <input class="form-control form-control-lg" type="password" name="newPassword" placeholder="Enter the new passowrd" required />
                                            </div>
                                            <div class="mb-3" style="margin-top: 30px;">
                                                <label class="form-label">Confirm Password:</label>
                                                <input class="form-control form-control-lg" type="password" name="confirmPassword" placeholder="Enter the confirm password" required />
                                            </div>
                                            <p style="color: red;">
                                                ${ERROR}
                                            </p>
                                            <div class="text-center mt-3">
                                                <button type="submit" class="btn btn-lg btn-success" style="border-radius: 50px;">Update Password</button>
                                            </div>
                                            <c:if test="${not empty errorMessage}">
                                                <div class="text-danger mt-2">${errorMessage}</div>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="js/app.js"></script>
    </body>

</html>
