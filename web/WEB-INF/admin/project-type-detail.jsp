<%-- 
    Document   : project-type-detail
    Created on : Oct 14, 2024, 4:48:12 PM
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

        <link rel="canonical" href="pages-profile.html" />

        <title>Project Type Details | PMS</title>

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
                <div class="card">
                    <div class="card-body">
                        <c:if test="${projectType == null}">
                            <form action="insert-project-type" method="post" class="row">
                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Project Type name" 
                                           value="${name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="code" placeholder="Enter the Project Type code" 
                                           value="${code}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Status</strong></label>
                                    <div class="check mt-1">
                                        <input class="form-check-input" type="radio" name="status" checked
                                               value="true">&nbsp;Active
                                        <input class="form-check-input ms-4" type="radio" name="status"
                                               value="false">&nbsp;Inactive
                                    </div>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label"><strong>Details</strong></label>
                                    <textarea class="form-control" name="details" 
                                              placeholder="Enter the Project Type details" rows="3">${details}</textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if>

                        <c:if test="${projectType != null}">
                            <c:if test="${user.role_id == 2}">
                                <form action="update-project-type" method="post" class="row">
                                    <input type="hidden" name="id" value="${projectType.id}">
                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="name" value="${projectType.name}" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="code" value="${projectType.code}" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <div class="check mt-1">
                                            <input class="form-check-input" type="radio" name="status"
                                                   <c:if test="${projectType.status eq 'true'}">
                                                       checked
                                                   </c:if>
                                                   value="true">&nbsp;Active
                                            <input class="form-check-input ms-4" type="radio" name="status"
                                                   <c:if test="${projectType.status eq 'false'}">
                                                       checked
                                                   </c:if>
                                                   value="false">&nbsp;Inactive
                                        </div>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Details</strong></label>
                                        <textarea class="form-control" name="details" rows="3">${projectType.details}</textarea>
                                    </div>

                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                    </div>
                                </form>
                            </c:if>

                            <c:if test="${user.role_id != 2}">
                                <form action="update-project-type" method="post" class="row">
                                    <input type="hidden" name="id" value="${projectType.id}">
                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="name" value="${projectType.name}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="code" value="${projectType.code}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <c:if test="${projectType.status eq 'true'}">
                                            <input type="text" class="form-control" name="status" value="Active" readonly>
                                        </c:if>
                                            
                                        <c:if test="${projectType.status eq 'false'}">
                                            <input type="text" class="form-control" name="status" value="Inactive" readonly>
                                        </c:if>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Details</strong></label>
                                        <textarea class="form-control" name="details" rows="3" readonly>${projectType.details}</textarea>
                                    </div>
                                </form>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>