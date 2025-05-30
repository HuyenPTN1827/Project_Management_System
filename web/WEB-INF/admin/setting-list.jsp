<%-- 
    Document   : setting-list
    Created on : Oct 14, 2024, 4:20:19 PM
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
        <meta name="author" content="PMS">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <!--<link rel="canonical" href="tables-datatables-multi.html" />-->

        <title>Setting Management | PMS</title>

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
            <% request.setAttribute("currentPage", "setting-management"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">Setting Management</h1>
                            </div>

                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                <form action="setting-management" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                    <div class="col-md-3">
                                                        <select name="type" class="form-select">
                                                            <option value="">All Types</option>
                                                            <option 
                                                            <c:if test="${type eq 'parent'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="parent">Parent Setting
                                                        </option> 
                                                        <c:forEach items="${listType}" var="t">
                                                            <option 
                                                                <c:if test="${type eq t.name}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${t.name}">${t.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="col-md-3">
                                                    <select name="status" class="form-select">
                                                        <option value="">All Status</option>
                                                        <option 
                                                            <c:if test="${status eq 'true'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="true">Active
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq 'false'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="false">Inactive
                                                        </option>
                                                    </select>
                                                </div>

                                                <div class="col-md-5">
                                                    <input type="search" name="keyword" class="form-control" 
                                                           placeholder="Enter Setting Name or Value" id="keyword" value="${keyword}">
                                                </div>

                                                <button type="submit" class="btn btn-primary">Search</button>

                                            </form>

                                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-setting">Create new</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <table id="datatables-multi" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Type</th>
                                                    <th>Value</th>
                                                    <th>Priority</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.listSetting}" var="setting">
                                                    <tr style="text-align: center">
                                                        <td>${setting.id}</td>
                                                        <td>${setting.name}</td>
                                                        <td>${setting.type}</td>
                                                        <td>${setting.value}</td>
                                                        <td>${setting.priority}</td>
                                                        <td>
                                                            <c:if test="${setting.status eq 'true'}">
                                                                <span class="badge bg-success">Active</span>
                                                            </c:if>
                                                            <c:if test="${setting.status eq 'false'}">
                                                                <span class="badge bg-danger">Inactive</span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <a href="<%=request.getContextPath()%>/edit-setting?id=${setting.id}" 
                                                               class="btn btn-info"><i class="align-middle" data-feather="edit"></i></a>

                                                            <c:if test="${setting.status eq 'false'}">
                                                                <a href="<%=request.getContextPath()%>/change-status-setting?id=${setting.id}&status=${setting.status}"
                                                                   class="btn btn-success"
                                                                   onclick="return confirm('Are you sure you want to activate this setting?');">
                                                                    <i class="fas fa-check"></i>
                                                                </a>
                                                            </c:if>

                                                            <c:if test="${setting.status eq 'true'}">
                                                                <a href="<%=request.getContextPath()%>/change-status-setting?id=${setting.id}&status=${setting.status}"
                                                                   class="btn btn-danger"
                                                                   onclick="return confirm('Are you sure you want to deactivate this setting?');">
                                                                    <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                </a>
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
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>PMS</strong></a> &copy;
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
                                                                                   {orderable: false, targets: 6} // Disable sorting on the 'Action' column
                                                                               ],
                                                                               language: {
                                                                                   paginate: {
                                                                                       previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                                       next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                                   },
                                                                                   info: "_TOTAL_ setting(s) found",
                                                                                   infoEmpty: "No setting found"
                                                                               },
                                                                               dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                               initComplete: function () {
                                                                                   // Add necessary classes for alignment
                                                                                   $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                   $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                                   // Replace Feather icons after DataTable initializes
                                                                                   feather.replace();
                                                                               }
                                                                           });

                                                                           // Replace Feather icons in case of dynamic changes
                                                                           datatablesMulti.on('draw', function () {
                                                                               feather.replace();
                                                                           });
                                                                       });
        </script>

        <script>
            document.addEventListener("DOMContentLoaded", function (event) {
                setTimeout(function () {
                    if (localStorage.getItem('popState') !== 'shown') {
                        window.notyf.open({
                            type: "success",
                            message: "Get access to all 500+ components and 45+ pages with PMS PRO. <u><a class=\"text-white\" href=\"https://adminkit.io/pricing\" target=\"_blank\">More info</a></u> 🚀",
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