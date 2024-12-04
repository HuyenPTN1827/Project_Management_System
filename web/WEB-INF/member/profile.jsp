<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
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
        <style>
            body {
                opacity: 0;

            }
        </style>

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
            <!-- Include sidebar -->
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                    <!-- Include header -->
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">
                            <h1 class="h3 mb-3">Profile</h1>

                            <div class="row">
                                <!-- Tăng độ rộng cho phần Profile Details -->
                                <div class="col-md-5 col-xl-4">
                                    <div class="card mb-3">
                                        <div class="card-header">
                                            <h5 class="card-title mb-0">Profile Details</h5>
                                        </div>
                                        <div class="card-body text-center">
                                            <img src="img/avatars/avatar-4.jpg" 
                                                 alt="${userProfile.full_name}" 
                                            class="img-fluid rounded-circle mb-2" width="128" height="128" />
                                        <h5 class="card-title mb-0">${userProfile.full_name}</h5>
                                        <div class="text-muted mb-2">${userProfile.role_name}</div>
                                        <div class="text-muted mb-2">Viet Nam</div>
                                    </div>
                                    <hr class="my-0" />
                                    <div class="card-body">
                                        <h5 class="h6 card-title">About</h5>
                                        <ul class="list-unstyled mb-0">
                                            <li class="mb-1">
                                                <span class="feather-sm me-1"></span> 
                                                Email: <a href="mailto:${userProfile.email}">${userProfile.email}</a>
                                            </li>
                                            <li class="mb-1">
                                                <span class="feather-sm me-1"></span> 
                                                Phone: <a href="tel:${userProfile.mobile}">${userProfile.mobile}</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-7 col-xl-8">
                                <div class="card">
                                    <div class="card-header position-relative">
                                        <h5 class="card-title">Edit Profile</h5>
                                        <h6 class="card-subtitle text-muted">Update your profile here.</h6>
                                        <a href="changepasswordcontroller" class="btn btn-link" style="position: absolute; top: 10px; right: 10px;">
                                            <i class="align-middle me-1" data-feather="lock"></i> Change Password
                                        </a>
                                    </div>

                                    <div class="card-body">
                                        <form action="${pageContext.request.contextPath}/member-profilecontroller" method="post" class="row">
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="inputName">Full Name</label>
                                                <input type="text" class="form-control" id="inputName" name="fullname" value="${userProfile.full_name}" required>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="inputEmail4">Email</label>
                                                <input type="email" class="form-control" id="inputEmail4" name="email" value="${userProfile.email}" required>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="phone">Phone</label>
                                                <input type="text" class="form-control" id="phone" name="mobile" value="${userProfile.mobile}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="inputCity">Roles</label>
                                                <input type="text" class="form-control" id="roles" name="roles" value="${userProfile.role_name}" readonly>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="inputCity">User Name</label>
                                                <input type="text" class="form-control" id="roles" name="username" value="${userProfile.username}" readonly>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label" for="inputCity">Department</label>
                                                <input type="text" class="form-control" id="roles" name="department" value="${userProfile.department}" readonly>
                                            </div>
                                            <div style="color: green;">
                                                ${message}
                                            </div>
                                            <div style="color: red;">
                                                ${err}
                                            </div>
                                            <button style="width: 150px;" type="submit" class="btn btn-lg btn-primary">Save Changes</button>
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
                                    <li class="list-inline-item"><a class="text-muted" href="#">Support</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Help Center</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Privacy</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Terms</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <script src="js/app.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Pie chart
                new Chart(document.getElementById("chartjs-dashboard-pie"), {
                    type: "pie",
                    data: {
                        labels: ["Working time", "Not work"],
                        datasets: [{
                                data: [4306, 1251],
                                backgroundColor: [window.theme.primary, "#E8EAED"],
                                borderWidth: 5,
                                borderColor: window.theme.white
                            }]
                    },
                    options: {
                        responsive: !window.MSInputMethodContext,
                        maintainAspectRatio: false,
                        legend: {display: false},
                        cutoutPercentage: 70
                    }
                });
            });
        </script>
    </body>

</html>
