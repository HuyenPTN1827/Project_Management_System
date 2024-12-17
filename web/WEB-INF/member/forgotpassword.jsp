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

    <link rel="shortcut icon" href="img/icons/icon-48x48.png" />

    <title>Forgot Password | PMS Demo</title>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
    <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
    <script src="js/settings.js"></script>
    <style>
        body {
            opacity: 0;
        }
    </style>

    <style>
        form-label {
            margin-top: 30px;
        }
    </style>
</head>

<body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
    <main class="d-flex w-100 h-100">
        <div class="container d-flex flex-column">
            <div class="row vh-100">
                <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
                    <div class="d-table-cell align-middle">
                        <div class="text-center mt-4"></div>
                        <div class="card">
                            <div class="card-body row">
                                <div class="col-md-12">
                                    <!-- Form bắt đầu -->
                                    <form id="email-form" action="<%=request.getContextPath()%>/forgotpassword" method="post">
                                        <div> 
                                            <img src="${pageContext.request.contextPath}/img/logo/PMSLogo-big.jpg" width="100px" style="border-radius: 50%;" alt="logo" />
                                        </div>
                                        <div>
                                            <h1 class="h2">Forgot Password</h1>
                                            <p class="lead">
                                                Already have account? 
                                                <a style="color: black;" href="<%=request.getContextPath()%>/login-form">Back to Sign in</a>
                                            </p>
                                        </div>

                                        <!-- Phần nhập email -->
                                        <div style="margin-top: 30px" class="mb-3">
                                            <label class="form-label">Email address</label>
                                            <input class="form-control form-control-lg" type="email" name="email" placeholder="Enter your Email" required />
                                            <small style="color: gray">Send email to reset your password</small>
                                        </div>

                                        <!-- Hiển thị thông báo lỗi (nếu có) -->
                                        ${errorMessage}<br>

                                        <div>
                                            <button type="submit" class="btn btn-lg btn-primary" style="border-radius: 50px;">Send email</button>
                                        </div>
                                    </form>
                                    <!-- Form kết thúc -->
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
