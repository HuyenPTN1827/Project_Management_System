<!--BachHD-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />
        <link rel="canonical" href="pages-profile.html" />

        <title>Profile | AdminKit Demo</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <script src="js/settings.js"></script>
        <style>body {
                opacity: 0;
            }</style>
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-120946860-10"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-120946860-10', {'anonymize_ip': true});
        </script>
    </head>


    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>
                    <main class="content">
                        <div class="container-fluid p-0">

                            <a href="<%=request.getContextPath()%>/member-profile">Profile ></a>

                        <div class="row mt-3">
                            <div class="col-md-8 col-xl-9">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="card-title">Change Password</h5>
                                    </div>
                                    <div class="card-body">
                                        <form action="changepassword" method="post"> <!-- Change action URL as necessary -->
                                            <div class="mb-3">
                                                <label class="form-label" for="oldPassword">Current password:</label>
                                                <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" for="newPassword">New password:</label>
                                                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" for="confirmPassword">Confirm new password:</label>
                                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                                            </div>
                                            <p style="color: blue;">
                                                ${NOTIFICATION}
                                            </p>
                                            <p style="color: red;">
                                                ${ERROR}
                                            </p>
                                            <div class="mb-3">
                                                <input type="submit" value="Change Password" class="btn btn-lg btn-success">
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
                                </div>
                            </div>
                        </div>

                    </div>
                </main>

                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col-6 text-start">
                                <p class="mb-0">
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>AdminKit</strong></a> &copy;
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Support</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Help Center</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Privacy</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Terms</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <script src="js/app.js"></script>
    </body>
</html>
