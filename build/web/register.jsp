

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

        <link rel="canonical" href="pages-sign-up.html" />

        <title>Sign Up | AdminKit Demo</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <script src="js/settings.js"></script>
        <style>body {
                opacity: 0;
            }
        </style>
        <!-- END SETTINGS -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-120946860-10"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-120946860-10', {'anonymize_ip': true});
        </script>
        <style>
            form-label{
               
            }
            
        </style>
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
                                    Already have account? <a style="text-decoration: none; color: black;" href="">Login</a>
                                </p>
                            </div>
                            <div class="card">
                                <div class="card-body row">
                                    <div class="col-md-7">
                                        <form>
                                            <div style=" margin-top: 10px" class="mb-3">
                                                <label class="form-label">Name</label>
                                                <input class="form-control form-control-lg" type="text" name="name" placeholder="Enter your name" />
                                            </div>
                                            <div  style=" margin-top: 10px" class="mb-3">
                                                <label class="form-label">Email</label>
                                                <input class="form-control form-control-lg" type="text" name="Email" placeholder="Enter your Email" />
                                            </div>
                                            <div  style=" margin-top: 10px" class="mb-3">
                                                <label class="form-label">Password</label>
                                                <input class="form-control form-control-lg" type="password" name="password" placeholder="Enter password" />
                                                <small style="color: gray">Use 8 or more characters with a mix of letters, number & symbol</small>
                                            </div>
                                            <div  style=" margin-top: 10px" class="mb-3">
                                                <label class="form-label">Confirm your Password</label>
                                                <input class="form-control form-control-lg" type="password" name="cpassword" placeholder="Enter password" />
                                            </div>
                                            <div  style=" margin-top: 10px" class="text-center mt-3">
                                                <a href="index.html" class="btn btn-lg btn-outline-primary" style="border-radius: 50px;">Create Account</a>
                                                <!-- <button type="submit" class="btn btn-lg btn-primary">Sign up</button> -->
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

        <script>
            document.addEventListener("DOMContentLoaded", function (event) {
                setTimeout(function () {
                    if (localStorage.getItem('popState') !== 'shown') {
                        window.notyf.open({
                            type: "success",
                            message: "Get access to all 500+ components and 45+ pages with AdminKit PRO. <u><a class=\"text-white\" href=\"https://adminkit.io/pricing\" target=\"_blank\">More info</a></u> 🚀",
                            duration: 10000,
                            ripple: true,
                            dismissible: false,
                            position: {
                                x: "left",
                                y: "bottom"
                            }
                        });

                        localStorage.setItem('popState', 'shown');
                    }
                }, 15000);
            });
        </script></body>

</html>