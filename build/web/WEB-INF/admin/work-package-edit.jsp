<%-- 
    Document   : project-type-user-add
    Created on : Oct 22, 2024, 9:13:36 PM
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
                            <a href="<%=request.getContextPath()%>/WorkPackageController">Scope list </a>
                        <span>> Edit scope</span>
                        <h1 class="h1 mt-2 mb-3 col-md-6"> Edit Scope</h1>
                        <div class="row">

                            <div class="col-md-12 col-xl-12">
                                <div class="card">

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

                                        <form action="WorkPackageController" class="row" method="post">
                                            <input type="hidden" name="action" value="add">
                                            <input type="hidden" name="createdBy" value="1">
                                            <div class="mb-3 col-md-6">
                                                <label for="title">Created By</label>
                                                <select id="userId" name="userId" class="form-select" disabled="">
                                                    <c:forEach var="u" items="${ul}">
                                                        <option value="${u.id}"  ${workPackage.createdBy==u.id?"Selected":""}>${u.full_name}</option>
                                                    </c:forEach>  
                                                </select>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="title">Last Updated</label>
                                                <input type="text" id="title" name="title" class="form-control" value="${workPackage.lastUpdated}" readonly>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="title">Title</label>
                                                <input type="text" id="title" name="title" class="form-control" value="${workPackage.title}" required>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="complexity">Project</label>
                                                <select id="projectId" name="projectId" class="form-select" required>
                                                    <c:forEach var="u" items="${pl}">
                                                        <option value="${u.id}" ${workPackage.projectId==u.id?"Selected":""}>${u.name}</option>
                                                    </c:forEach>  
                                                </select>
                                            </div> 
                                            <div class="mb-3 col-md-6">
                                                <label for="complexity">Owner</label>
                                                <select id="userId" name="userId" class="form-select" required>
                                                    <c:forEach var="u" items="${ul}">
                                                        <option value="${u.id}"  ${workPackage.userId==u.id?"Selected":""}>${u.full_name}</option>
                                                    </c:forEach>  
                                                </select>
                                            </div> 

                                            <div class="mb-3 col-md-6">
                                                <label for="complexity">Complexity</label>
                                                <select id="complexity" name="complexity" class="form-select" required>
                                                    <c:forEach var="u" items="${cl}">
                                                        <option value="${u.id}" ${workPackage.complexity==u.id?"Selected":""} >${u.name}</option>
                                                    </c:forEach>  
                                                </select>
                                            </div> 

                                            <div class="mb-3 col-md-6">
                                                <label for="plannedEffort">Planned Effort</label>
                                                <input type="number" id="plannedEffort" name="plannedEffort" value="${workPackage.plannedEffort}"  class="form-control" required>
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="plannedEffort">Actual Effort</label>
                                                <input type="number" id="plannedEffort" name="Actual" value="${workPackage.actualEffort==0?"":workPackage.actualEffort}"  class="form-control"  readonly="">
                                            </div>
                                            <div class="mb-3 col-md-6">
                                                <label for="complexity">Status</label>
                                                <select id="status" name="status" class="form-select" required>
                                                    <c:forEach var="u" items="${sl}">
                                                        <option value="${u.id}" ${workPackage.status==u.id?"Selected":""} >${u.name}</option>
                                                    </c:forEach>  
                                                </select>
                                            </div> 
                                            <div class="mb-3 col-md-12">
                                                <label for="details">Details</label>
                                                <textarea id="details" name="details" class="form-control">${workPackage.details}</textarea>
                                            </div>

                                            <button type="submit" class="btn btn-primary">Submit</button>
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
