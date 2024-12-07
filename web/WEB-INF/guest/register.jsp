<%-- 
    Document   : register
    Created on : Sep 12, 2024, 12:23:11 AM
    Author     : kelma
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                                                <label class="form-label">Full Name <span style="color: red;">*</span></label>
                                                <input class="form-control form-control-lg" type="text" name="fullname" placeholder="Enter the Full Name" value="${fullname}" />
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Username <span style="color: red;">*</span></label>
                                                <input class="form-control form-control-lg" type="text" name="username" placeholder="Enter the Username" value="${username}"/>
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Email <span style="color: red;">*</span></label>
                                                <input class="form-control form-control-lg" type="text" name="email" placeholder="Enter the Email address" value="${email}"/>

                                            </div>
                                            <div style="margin-top: 10px" class="mb-3" hidden>
                                                <label class="form-label">Phone Number</label>
                                                <input class="form-control form-control-lg" type="text" name="mobile" placeholder="Enter phone number" value="${mobile}"/>
                                                <small style="color: gray">Phone number must start with 03, 05, 07, 08, or 09 and be 10 digits long.</small>
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Password <span style="color: red;">*</span></label>
                                                <input class="form-control form-control-lg" type="password" name="password" placeholder="Enter the Password" value="${password}"/>
                                                <small style="color: gray">Password must be at least 6 characters, including uppercase letters, lowercase letters, numbers and some special characters.</small>
                                            </div>
                                            <div style="margin-top: 10px" class="mb-3">
                                                <label class="form-label">Confirm Password <span style="color: red;">*</span></label>
                                                <input class="form-control form-control-lg" type="password" name="confirmPassword" placeholder="Confirm the Password" value="${confirmpassword}"/>
                                            </div>

<!--                                            <p style="color: red;">
                                                ${NOTIFICATION}
                                            </p>
                                            <p style="color: blue;">
                                                ${SUCCESS}
                                            </p>-->
                                            
                                            <c:if test="${not empty validationErrors}">
                                                <div class="alert alert-danger pt-3 pe-3 ps-3">
                                                    <ul>
                                                        <c:forEach items="${validationErrors}" var="error" >
                                                            <li>${error}</li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </c:if>
                                            
                                            <c:if test="${not empty NOTIFICATION}">
                                                <div class="alert alert-danger p-3">
                                                    ${NOTIFICATION}
                                                </div>
                                            </c:if>
                                            
                                            <c:if test="${not empty SUCCESS}">
                                                <div class="alert alert-success p-3">
                                                    ${SUCCESS}
                                                </div>
                                            </c:if>


                                            <div class="text-center mt-3">
                                                <button type="submit" class="btn btn-lg btn-primary" style="border-radius: 50px;">Register Account</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-5" style="display: flex; align-items: center">
                                        <!--<img width="100%" src="https://tatthanh.com.vn/pic/Images/Module/News/images/image(1722).png" alt="alt"/>-->
                                        <img width="100%" src="${pageContext.request.contextPath}/img/logo/PMSLogo-big.jpg" alt="logo"/>
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
