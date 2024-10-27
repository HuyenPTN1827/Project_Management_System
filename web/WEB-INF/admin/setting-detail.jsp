<%-- 
    Document   : setting-detail
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
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <link rel="canonical" href="pages-profile.html" />

        <title>Setting Details | PMS</title>

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
            <% request.setAttribute("currentPage", "setting-management"); %>
            <jsp:include page="../component/sidebar-admin.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">
                            <a href="<%=request.getContextPath()%>/setting-management">Setting Management > </a>

                        <c:if test="${setting == null}">
                            <h1 class="h1 mt-2 mb-3"> Create New Setting</h1>
                            <div class="row">

                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-body">

                                            <form action="insert-setting" method="get" class="row">
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Name<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="name" placeholder="Enter the Setting name" 
                                                           value="${name}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Type<span style="color: red;">*</span></label>
                                                    <select name="type" class="form-select" required>
                                                        <option value="" hidden disable selected>Choose Setting Type</option>
                                                        <option 
                                                            <c:if test="${type eq 'User Role'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="User Role">User Role
                                                        </option>
                                                        <option 
                                                            <c:if test="${type eq 'Dept Role'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Dept Role">Dept Role
                                                        </option>
                                                        <!--                                                        <option 
                                                        <%--<c:if test="${type eq 'Project Role'}">--%>
                                                            selected="selected"
                                                        <%--</c:if>--%>
                                                        value="Project Role">Project Role
                                                    </option>-->
                                                        <option 
                                                            <c:if test="${type eq 'Business Term'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Business Term">Business Term
                                                        </option>
                                                        <option 
                                                            <c:if test="${type eq 'Defect Severity'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Defect Severity">Defect Severity
                                                        </option>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Value</label>
                                                    <input type="text" class="form-control" name="value" placeholder="Enter the Setting value" 
                                                           value="${value}">
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Priority<span style="color: red;">*</span></label>
                                                    <input type="number" class="form-control" name="priority" placeholder="Enter the Setting priority" 
                                                           value="${priority}" required>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Description</label>
                                                    <textarea class="form-control" name="description" 
                                                              placeholder="Enter the Setting description" rows="3">${description}</textarea>
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

                        <c:if test="${setting != null}">
                            <h1 class="h1 mt-2 mb-3"> Setting Details</h1>
                            <div class="row">

                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-body">

                                            <form action="update-setting" method="post" class="row">
                                                <input type="hidden" name="id" value="${setting.id}"/>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Name<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="name" placeholder="Enter the Setting name" 
                                                           value="${setting.name}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Type<span style="color: red;">*</span></label>
                                                    <select name="type" class="form-select" required>
                                                        <option 
                                                            <c:if test="${setting.type eq 'User Role'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="User Role">User Role
                                                        </option>
                                                        <option 
                                                            <c:if test="${setting.type eq 'Dept Role'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Dept Role">Dept Role
                                                        </option>
                                                        <!--                                                        <option 
                                                        <%--<c:if test="${setting.type eq 'Project Role'}">--%>
                                                            selected="selected"
                                                        <%--</c:if>--%>
                                                        value="Project Role">Project Role
                                                    </option>-->
                                                        <option 
                                                            <c:if test="${setting.type eq 'Business Term'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Business Term">Business Term
                                                        </option>
                                                        <option 
                                                            <c:if test="${setting.type eq 'Defect Severity'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="Defect Severity">Defect Severity
                                                        </option>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Value</label>
                                                    <input type="text" class="form-control" name="value" placeholder="Enter the Value" 
                                                           value="${setting.value}">
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Priority<span style="color: red;">*</span></label>
                                                    <input type="number" class="form-control" name="priority" placeholder="Enter the Priority" 
                                                           value="${setting.priority}" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Status<span style="color: red;">*</span></label>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="status"
                                                               <c:if test="${setting.status eq 'true'}">
                                                                   checked
                                                               </c:if>
                                                               value="true">Active
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="status"
                                                               <c:if test="${setting.status eq 'false'}">
                                                                   checked
                                                               </c:if>
                                                               value="false">Inactive
                                                    </div>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label">Description</label>
                                                    <textarea class="form-control" name="description" 
                                                              placeholder="Enter the Description" rows="3">${setting.description}</textarea>
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