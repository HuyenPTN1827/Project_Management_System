<%-- 
    Document   : register
    Created on : Sep 12, 2024, 12:23:11 AM
    Author     : kelma
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />
        <title>Sign Up | AdminKit Demo</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <script src="js/settings.js"></script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <main class="d-flex w-100 h-100">
            <div class="container d-flex flex-column">
                <div class="row vh-100">
                    <div class="col-sm-12 col-md-10 col-lg-10 mx-auto d-table h-100">
                        <div class="d-table-cell align-middle">
                            <div class="text-center mt-4">
                                <h1 class="h2">Create an account</h1>

                                <p class="lead">
                                    Already have an account? <a style="text-decoration: none; color: black;" href="<%=request.getContextPath()%>/login-form">Log in</a>
                                </p>

                            </div>
                            <div class="card">
                                <div class="card-body row">
                                    <div class="col-md-7">
                                        <form action="<%=request.getContextPath()%>/register" method="post">
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Full Name</label>
                                                <input class="form-control form-control-lg" type="text" name="fullname" placeholder="Enter full name" required />
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Username</label>
                                                <input class="form-control form-control-lg" type="text" name="username" placeholder="Enter username" required />
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input class="form-control form-control-lg" type="text" name="email" placeholder="Enter email" required />
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Phone Number</label>
                                                <input class="form-control form-control-lg" type="text" name="mobile" placeholder="Enter phone number" />
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Password</label>
                                                <input class="form-control form-control-lg" type="password" name="password" placeholder="Enter password" required />
                                                <small style="color: gray">Use 8 or more characters with a mix of letters, numbers, and symbols</small>
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Confirm Password</label>
                                                <input class="form-control form-control-lg" type="password" name="confirmPassword" placeholder="Confirm password" required />
                                            </div>

                                            <p style="color: red;">
                                                ${NOTIFICATION}
                                            </p>
                                            <p style="color: blue;">
                                                ${SUCCESS}
                                            </p>

                                            <div class="text-center mt-3">
                                                <button type="submit" class="btn btn-lg btn-primary" style="border-radius: 50px;">Create Account</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-5" style="display: flex; align-items: center">
                                        <img width="100%" src="https://tatthanh.com.vn/pic/Images/Module/News/images/image(1722).png" alt="alt"/>
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
    <script>
        document.querySelector("form").addEventListener("submit", function (e) {
            const password = document.querySelector("input[name='password']").value;
            const confirmPassword = document.querySelector("input[name='confirmPassword']").value;

            if (password !== confirmPassword) {
                e.preventDefault();
                alert("Passwords do not match. Please try again.");
            }
        });
    </script>

</html>
