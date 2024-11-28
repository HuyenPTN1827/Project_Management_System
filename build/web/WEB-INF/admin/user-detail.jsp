<%-- 
    Document   : user-detail
    Created on : Sep 15, 2024, 6:19:09 PM
    Author     : HuyenPTNHE160769
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

        <title>User Details | PMS</title>

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
            <% request.setAttribute("currentPage", "user-management"); %>
            <jsp:include page="../component/sidebar-admin.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">
                            <a href="<%=request.getContextPath()%>/user-management">User Management > </a>

                        <c:if test="${userDetail == null}">

                            <h1 class="h1 mt-2 mb-3"> Create New User</h1>
                            <div class="row">

                                <div class="col-md-12 col-xl-12">
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

                                            <form action="insert-user" method="post" class="row">
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Full Name <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="fullname" placeholder="Enter the Full name" 
                                                           value="${fullname}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Username <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="username" placeholder="Enter the Username" 
                                                           value="${username}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Email <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="email" placeholder="Enter the Email address" 
                                                           value="${email}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Password <span style="color: red;">*</span></label>
                                                    <input type="password" class="form-control" name="password" placeholder="Enter the Password" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Phone</label>
                                                    <input type="text" class="form-control" name="mobile" placeholder="Enter the Phone number"
                                                           value="${mobile}">
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Role <span style="color: red;">*</span></label>
                                                    <select name="role" class="form-select">
                                                        <c:forEach items="${role}" var="r">
                                                            <option 
                                                                <c:if test="${roleId eq r.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${r.id}>${r.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Department</label>
                                                    <select name="dept" class="form-select">
                                                        <option value="">Choose Department</option>
                                                        <c:forEach items="${dept}" var="d">
                                                            <option 
                                                                <c:if test="${deptId eq d.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${d.id}>${d.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Notes</label>
                                                    <textarea class="form-control" name="notes" placeholder="Enter the Notes"
                                                              value="${notes}" rows="3"></textarea>
                                                </div>

                                                <div>
                                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if> 

                        <c:if test="${userDetail != null}">
                            <h1 class="h1 mt-2 mb-3"> User Details</h1>
                            <div class="row">

                                <div class="col-md-12 col-xl-12">
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

                                            <form action="update-user" method="post" class="row">
                                                <input type="hidden" name="id" value="${userDetail.id}"/>
                                                <input type="hidden" name="password" value="${userDetail.password}"/>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Full Name <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="fullname" placeholder="Enter the Full name" 
                                                           value="${userDetail.full_name}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Username <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="username" placeholder="Enter the Username" 
                                                           value="${userDetail.username}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Email <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="email" placeholder="Enter the Email address" 
                                                           value="${userDetail.email}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Phone</label>
                                                    <input type="text" class="form-control" name="mobile" placeholder="Enter the Phone number"
                                                           value="${userDetail.mobile}">
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Role <span style="color: red;">*</span></label>
                                                    <select name="role" class="form-select">
                                                        <c:forEach items="${role}" var="r">
                                                            <option 
                                                                <c:if test="${userDetail.setting.id eq r.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${r.id}>${r.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Department</label>
                                                    <select name="dept" class="form-select">
                                                        <option value="">Choose Department</option>
                                                        <c:forEach items="${dept}" var="d">
                                                            <option 
                                                                <c:if test="${userDetail.dept.id eq d.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${d.id}>${d.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Status</label>

                                                    <c:if test="${userDetail.status eq '3'}">
                                                        <input type="hidden" name="status" value="3">
                                                        <input type="text" class="form-control" value="Unverified" readonly>
                                                    </c:if>

                                                    <c:if test="${userDetail.status ne '3'}">
                                                        <div class="check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   <c:if test="${userDetail.status eq '1'}">
                                                                       checked
                                                                   </c:if>
                                                                   value="1"> Active
                                                            <input class="form-check-input ms-3" type="radio" name="status"
                                                                   <c:if test="${userDetail.status eq '0'}">
                                                                       checked
                                                                   </c:if>
                                                                   value="0"> Inactive
                                                        </div>
                                                    </c:if>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Notes</label>
                                                    <textarea class="form-control" name="notes" 
                                                              placeholder="Enter the Notes" rows="3">${userDetail.notes}</textarea>
                                                </div>

                                                <div>
                                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if> 
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