<%-- 
    Document   : project-type-user-add
    Created on : Oct 22, 2024, 9:13:36 PM
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
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <link rel="canonical" href="pages-profile.html" />

        <title>Project Type User Details | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
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
        </script></head>


    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "project-type-management"); %>
            <jsp:include page="../component/sidebar-admin.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">
                            <a href="<%=request.getContextPath()%>/project-type-management">Project Types Management > </a>
                        <a href="<%=request.getContextPath()%>/project-type-config?id=${typeId}">Project Type Configs > </a>

                        <h1 class="h1 mt-2 mb-3"> Add User</h1>
                        <div class="row">

                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="mt-2 mb-2">
                                            <form action="add-project-type-user" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                <input type="hidden" name="typeId" value="${typeId}">
                                                <div class="col-md-4">
                                                    <input type="search" name="keyword" class="form-control"
                                                           placeholder="Enter Full Name or Email" id="keyword" value="${keyword}">
                                                </div>
                                                <div class="col-md-2">
                                                    <button type="submit" class="btn btn-primary">Search</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>          

                                <div class="card">
                                    <div class="card-body">
                                        <c:if test="${not empty errorMessages}">
                                            <div class="alert alert-danger pt-3 pe-3 ps-3">
                                                <ul>
                                                    <c:forEach items="${errorMessages}" var="error" >
                                                        <li>${error}</li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>

                                        <form action="insert-project-type-user" method="post" class="row">
                                            <input type="hidden" name="typeId" value="${typeId}">
                                            <input type="hidden" name="id" value="${userType.id}">

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Full Name<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="fullname" placeholder="Full name" 
                                                       value="${userType.full_name}" readonly>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Phone</label>
                                                <input type="text" class="form-control" name="mobile" placeholder="Enter the Phone number"
                                                       value="${userType.mobile}" readonly>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Email<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="email" placeholder="Enter the Email address" 
                                                       value="${userType.email}" readonly>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Project Role<span style="color: red;">*</span></label>
                                                <select name="pjRole" class="form-select" required>
                                                    <option value="" disable hidden>Choose Project Role</option>
                                                    <c:forEach items="${ptSetting}" var="r">
                                                        <option value=${r.id}>${r.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div>
                                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                            </div>
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

        <script src="${pageContext.request.contextPath}/js/app.js"></script>

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
        </script>
    </body>
</html>
