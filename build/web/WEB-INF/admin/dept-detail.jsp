<%-- 
    Document   : dept-detail
    Created on : Oct 14, 2024, 4:32:33 PM
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

        <title>Department Details | PMS</title>

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
        <div class="row">

            <div class="col-md-12 col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${department == null}">
                            <form action="insert-department" method="post" class="row">
                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Name <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Department name" 
                                           value="${name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Code <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="code" placeholder="Enter the Department code" 
                                           value="${code}" required>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label">Parent</label>
                                    <select name="parent" class="form-select">
                                        <option value="">Choose Department Parent</option>
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
                                    <label class="form-label">Details</label>
                                    <textarea class="form-control" name="details" 
                                              placeholder="Enter the Department details" rows="3">${details}</textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${department != null}">
                            <form action="update-department" method="post" class="row">
                                <input type="hidden" name="id" value="${department.id}"/>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Name <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Department name" 
                                           value="${department.name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Code <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="code" placeholder="Enter the Department code" 
                                           value="${department.code}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Parent</label>
                                    <select name="parent" class="form-select">
                                        <option value="">Choose Department Parent</option>
                                        <c:forEach items="${dept}" var="d">
                                            <option 
                                                <c:if test="${department.parentId eq d.id}">
                                                    selected="selected"
                                                </c:if>
                                                value=${d.id}>${d.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-6 mt-1">
                                    <label class="form-label">Status</label>
                                    <div class="check">
                                        <input class="form-check-input" type="radio" name="status"
                                               <c:if test="${department.status eq 'true'}">
                                                   checked
                                               </c:if>
                                               value="true"> Active
                                        <input class="form-check-input ms-3" type="radio" name="status"
                                               <c:if test="${department.status eq 'false'}">
                                                   checked
                                               </c:if>
                                               value="false"> Inactive
                                    </div>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label">Details</label>
                                    <textarea class="form-control" name="details" 
                                              placeholder="Enter the Department details" rows="3">${department.details}</textarea>
                                </div>

                                <div class="d-flex justify-content-end">
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