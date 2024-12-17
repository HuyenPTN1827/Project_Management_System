<%-- 
    Document   : verify
    Created on : Dec 7, 2024, 11:27:30 PM
    Author     : Admin
--%>

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
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />
        <link rel="canonical" href="pages-sign-up.html" />

        <title>Verify Account | AdminKit Demo</title>

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
                                <h1 class="h2">Verify Account</h1>
                                <p class="lead">
                                    Already have account? <a style="color: black;" href="<%=request.getContextPath()%>/login-form">Back to Sign in</a>
                                </p>
                            </div>
                            <div class="card">
                                <div class="card-body row">
                                    <div class="col-md-12">
                                        <form id="otp-form" action="<%=request.getContextPath()%>/verify" method="post">
                                            <div class="mb-3" style="margin-top: 30px;">
                                                <label class="form-label">Enter OTP:</label>
                                                <input class="form-control form-control-lg" type="text" name="otp" placeholder="Enter the OTP" value="${enteredotp}" required />
                                            </div>
                                            <p style="color: red;">
                                                ${ERROR}
                                            </p>
                                            <div class="text-center mt-3 d-flex justify-content-between">
                                                <button type="submit" class="btn btn-lg btn-success" style="border-radius: 50px;">Verify</button>
                                                <button type="button" class="btn btn-lg btn-secondary" style="border-radius: 50px; margin-top: 10px;" onclick="openPopup()">Resend OTP</button>
                                            </div>

                                            <c:if test="${not empty NOTIFICATION}">
                                                <div class="text-danger mt-2">${NOTIFICATION}</div>
                                            </c:if>

                                            <c:if test="${not empty SUCCESS}">
                                                <div class="alert alert-success p-3">
                                                    ${SUCCESS}
                                                </div>
                                            </c:if>
                                        </form>



                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- Popup for entering email -->
            <div id="resendOtpPopup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000; background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);">
                <h3 style="margin-bottom: 20px;">Resend OTP</h3>
                <form id="resend-otp-form" action="<%=request.getContextPath()%>/resend-otp" method="post">
                    <label for="email" style="display: block; margin-bottom: 10px;">Enter your email:</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Enter your email" required style="width: 100%; margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px;">
                    <div style="text-align: right;">
                        <button type="button" onclick="closePopup()" style="margin-right: 10px; padding: 10px 20px; border: none; background-color: #ccc; border-radius: 5px; cursor: pointer;">Close</button>
                        <button type="submit" style="padding: 10px 20px; border: none; background-color: #28a745; color: white; border-radius: 5px; cursor: pointer;">Send OTP</button>
                    </div>
                </form>
            </div>
            <!-- Background overlay -->
            <div id="popupOverlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 999;"></div>

        </main>
        <script>
            function openPopup() {
                document.getElementById("resendOtpPopup").style.display = "block";
                document.getElementById("popupOverlay").style.display = "block";
            }

            function closePopup() {
                document.getElementById("resendOtpPopup").style.display = "none";
                document.getElementById("popupOverlay").style.display = "none";
            }
        </script>

        <script src="js/app.js"></script>
    </body>

</html>

