<%-- 
    Document   : dept-user-detail
    Created on : Nov 1, 2024, 1:53:58 PM
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
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="row">
            <div class="col-md-12 col-xl-12">
                <c:if test="${deptUser == null}">
                    <div class="card">
                        <div class="card-header">
                            <div class="mt-2 mb-2">
                                <form action="add-department-user" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                    <input type="hidden" name="deptId" value="${deptId}">
                                    <div class="col-md-5">
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

                            <form action="insert-department-user" method="post" class="row">
                                <input type="hidden" name="deptId" value="${deptId}">
                                <input type="hidden" name="id" value="${deptUser.id}">

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Full Name <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="fullname" placeholder="Enter the Full name" 
                                           value="${deptUser.full_name}" readonly>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Phone</label>
                                    <input type="text" class="form-control" name="mobile" placeholder="Enter the Phone number"
                                           value="${deptUser.mobile}" readonly>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Email <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="email" placeholder="Enter the Email address" 
                                           value="${deptUser.email}" readonly>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Project Role <span style="color: red;">*</span></label>
                                    <select name="roleId" class="form-select" required>
                                        <option value="" disable hidden>Choose Department Role</option>
                                        <c:forEach items="${setting}" var="r">
                                            <option value=${r.id}>${r.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div>
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${deptUser != null}">
                            <div class="card">
                                <div class="card-header">
                                    <form action="update-department-user" method="post" class="row">
                                        <input type="hidden" name="id" value="${deptUser.id}">
                                        <input type="hidden" name="userId" value="${deptUser.user.id}">
                                        <input type="hidden" name="deptId" value="${deptUser.dept.id}">

                                        <div class="mb-3 col-md-6">
                                            <label class="form-label">Full Name <span style="color: red;">*</span></label>
                                            <input type="text" class="form-control" name="fullname" placeholder="Enter the Full name" 
                                                   value="${deptUser.user.full_name}" readonly>
                                        </div>

                                        <div class="mb-3 col-md-6">
                                            <label class="form-label">Phone</label>
                                            <input type="text" class="form-control" name="mobile" placeholder="Enter the Phone number"
                                                   value="${deptUser.user.mobile}" readonly>
                                        </div>

                                        <div class="mb-3 col-md-6">
                                            <label class="form-label">Email <span style="color: red;">*</span></label>
                                            <input type="text" class="form-control" name="email" placeholder="Enter the Email address" 
                                                   value="${deptUser.user.email}" readonly>
                                        </div>

                                        <div class="mb-3 col-md-6">
                                            <label class="form-label">Department Role <span style="color: red;">*</span></label>
                                            <select name="roleId" class="form-select" required>
                                                <option value="" disable hidden>Choose Project Role</option>
                                                <c:forEach items="${setting}" var="r">
                                                    <option 
                                                        <c:if test="${deptUser.setting.id eq r.id}">
                                                            selected="selected"
                                                        </c:if>
                                                        value=${r.id}>${r.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="mb-3 col-md-6">
                                            <label class="form-label">Status</label>
                                            <div class="check">
                                                <input class="form-check-input" type="radio" name="status"
                                                       <c:if test="${deptUser.status eq 'true'}">
                                                           checked
                                                       </c:if>
                                                       value="true"> Active
                                                <input class="form-check-input ms-3" type="radio" name="status"
                                                       <c:if test="${deptUser.status eq 'false'}">
                                                           checked
                                                       </c:if>
                                                       value="false"> Inactive
                                            </div>
                                        </div>

                                        <div>
                                            <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
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
