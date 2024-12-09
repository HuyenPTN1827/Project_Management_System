<%-- 
    Document   : project-phase-detail
    Created on : Nov 22, 2024, 1:23:40 AM
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

        <title>Project Type Criteria Details | PMS</title>

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
                        <c:if test="${phase == null}">
                            <form action="insert-project-phase" method="post" class="row">
                                <input type="hidden" name="typeId" value="${typeId}">

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Project Phase</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Phase name" 
                                           value="${name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" name="priority" placeholder="Enter the Priority" 
                                           value="${priority}" required>
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
                                              placeholder="Enter the Phase details" rows="3">${details}</textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${phase != null}">
                            <c:if test="${action == 'edit'}">
                                <form action="update-project-phase" method="post" class="row">
                                    <input type="hidden" name="typeId" value="${typeId}">
                                    <input type="hidden" name="id" value="${phase.id}">

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="name" placeholder="Enter the Phase name" 
                                               value="${phase.name}" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" name="priority" placeholder="Enter the Priority" 
                                               value="${phase.priority}" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <div class="check mt-1">
                                            <input class="form-check-input" type="radio" name="status"
                                                   <c:if test="${phase.status eq 'true'}">
                                                       checked
                                                   </c:if>
                                                   value="true">&nbsp;Active
                                            <input class="form-check-input ms-4" type="radio" name="status"
                                                   <c:if test="${phase.status eq 'false'}">
                                                       checked
                                                   </c:if>
                                                   value="false">&nbsp;Inactive
                                        </div>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Details</strong></label>
                                        <textarea class="form-control" name="details" 
                                                  placeholder="Enter the Phase details" rows="3">${phase.details}</textarea>
                                    </div>

                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                    </div>
                                </form>
                            </c:if>
                            
                            <c:if test="${action == 'view'}">
                                <form action="update-project-phase" method="post" class="row">
                                    <input type="hidden" name="typeId" value="${typeId}">
                                    <input type="hidden" name="id" value="${phase.id}">

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" name="name" placeholder="Enter the Phase name" 
                                               value="${phase.name}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" name="priority" placeholder="Enter the Priority" 
                                               value="${phase.priority}" readonly>
                                    </div>
                                    
                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <c:if test="${phase.status eq 'true'}">
                                            <input type="text" class="form-control" placeholder="Project Phase Status" 
                                                   value="Active" readonly>
                                        </c:if>
                                        <c:if test="${phase.status eq 'false'}">
                                            <input type="text" class="form-control" placeholder="Project Phase Status" 
                                                   value="Inactive" readonly>
                                        </c:if>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Details</strong></label>
                                        <textarea class="form-control" name="details" readonly
                                                  placeholder="Enter the Phase details" rows="3">${phase.details}</textarea>
                                    </div>
                                </form>
                            </c:if>
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

