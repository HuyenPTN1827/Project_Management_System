<%-- 
    Document   : user-list
    Created on : Sep 15, 2024, 6:19:09 PM
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

        <!--<link rel="canonical" href="tables-datatables-multi.html" />-->

        <title>User Management | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>
            body {
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
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">User Management</h1>
                            </div>

                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                <form action="user-management" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                    <select name="deptId" class="form-select" style="width: 120px;">
                                                        <option value="">All Departments</option>
                                                    <c:forEach items="${dept}" var="d">
                                                        <option 
                                                            <c:if test="${deptId eq d.id}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="${d.id}">${d.name}
                                                        </option>
                                                    </c:forEach>
                                                </select>

                                                <select name="roleId" class="form-select" style="width: 120px;">
                                                    <option value="">All Roles</option>
                                                    <c:forEach items="${role}" var="r">
                                                        <option 
                                                            <c:if test="${roleId eq r.id}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="${r.id}">${r.name}
                                                        </option>
                                                    </c:forEach>
                                                </select>

                                                <select name="status" class="form-select"  style="width: 130px;">
                                                    <option value="">All Statuses</option>
                                                    <option 
                                                        <c:if test="${status eq 1}">
                                                            selected="selected"
                                                        </c:if>
                                                        value="1">Active
                                                    </option>
                                                    <option 
                                                        <c:if test="${status eq 0}">
                                                            selected="selected"
                                                        </c:if>
                                                        value="0">Inactive
                                                    </option>
                                                    <option 
                                                        <c:if test="${status eq 3}">
                                                            selected="selected"
                                                        </c:if>
                                                        value="3">Unverified
                                                    </option>
                                                </select>

                                                <input type="search" name="keyword" class="form-control" style="width: 270px;"
                                                       placeholder="Enter Full Name or Email or Phone" id="keyword" value="${keyword}">

                                                <button type="submit" class="btn btn-primary">Search</button>

                                            </form>

                                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-user">Create new</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <table id="datatables-multi" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th>ID</th>
                                                    <th>Full Name</th>
                                                    <th>Email</th>
                                                    <th>Mobile</th>
                                                    <th>Department</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.listUser}" var="user">
                                                    <tr style="text-align: center">
                                                        <td>${user.id}</td>
                                                        <td>${user.full_name}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.mobile}</td>
                                                        <c:forEach items="${user.depts}" var="d">
                                                            <td>${d.code}</td>
                                                        </c:forEach>
                                                        <c:forEach items="${user.settings}" var="r">
                                                            <td>${r.name}</td>
                                                        </c:forEach>
                                                        <td>
                                                            <c:if test="${user.status eq '0'}">
                                                                <span class="badge bg-danger">Inactive</span>
                                                            </c:if>
                                                            <c:if test="${user.status eq '1'}">
                                                                <span class="badge bg-success">Active</span>
                                                            </c:if>
                                                            <c:if test="${user.status eq '3'}">
                                                                <span class="badge bg-secondary">Unverified</span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${user.status eq '0'}">
                                                                <a href="<%=request.getContextPath()%>/edit-user?id=${user.id}"
                                                                   class="btn btn-link text-primary">Details</a>
                                                                   
                                                                <a href="<%=request.getContextPath()%>/change-status-user?id=${user.id}&status=${user.status}"
                                                                   class="btn btn-link text-success"
                                                                   onclick="return confirm('Are you sure you want to activate this user?');">Activate</a>
                                                            </c:if>
                                                            
                                                            <c:if test="${user.status eq '1'}">
                                                                <a href="<%=request.getContextPath()%>/edit-user?id=${user.id}"
                                                                   class="btn btn-link text-primary">Details</a>
                                                                
                                                                <a href="<%=request.getContextPath()%>/change-status-user?id=${user.id}&status=${user.status}"
                                                                   class="btn btn-link text-danger"
                                                                   onclick="return confirm('Are you sure you want to deactivate this user?');">Deactivate</a>
                                                            </c:if>
                                                            
                                                            <c:if test="${user.status eq '3'}">
                                                                <a href="<%=request.getContextPath()%>/edit-user?id=${user.id}"
                                                                   class="btn btn-link text-primary">Details</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
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

        <script src="${pageContext.request.contextPath}/js/datatables.js"></script>

        <script>
                                                                       document.addEventListener("DOMContentLoaded", function () {
                                                                           var datatablesMulti = $("#datatables-multi").DataTable({
                                                                               responsive: true,
                                                                               paging: true,
                                                                               searching: false,
                                                                               info: true,
                                                                               order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                               columnDefs: [
                                                                                   {orderable: false, targets: 7} // Disable sorting on the 'Action' column
                                                                               ],
                                                                               language: {
                                                                                   paginate: {
                                                                                       previous: "&laquo;",
                                                                                       next: "&raquo;"
                                                                                   },
                                                                                   info: "_TOTAL_ user(s) found",
                                                                                   infoEmpty: "No user found"
                                                                               },
                                                                               dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                               initComplete: function () {
                                                                                   // Add necessary classes for alignment
                                                                                   $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                   $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes
                                                                               }
                                                                           });
                                                                       });
        </script>

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