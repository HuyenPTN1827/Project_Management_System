<%-- 
    Document   : project-type-criteria-detail
    Created on : Oct 30, 2024, 3:55:41 AM
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
                        <c:if test="${ptCriteria == null}">
                            <form action="insert-project-type-criteria" method="post" class="row">
                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Name<span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Eval Criteria" 
                                           value="${name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Weight<span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" name="code" placeholder="Enter the Criteria weight" 
                                           value="${weight}" required>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label">Project Phase<span style="color: red;">*</span></label>
                                    <select name="phaseId" class="form-select" required>
                                        <option value="" disable hidden>Choose Project Phase</option>
                                        <c:forEach items="${phase}" var="p">
                                            <option 
                                                <c:if test="${phaseId eq p.id}">
                                                    selected="selected"
                                                </c:if>
                                                value=${p.id}>${p.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-control" name="description" 
                                              placeholder="Enter the Criteria description" rows="3">${description}</textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${ptCriteria != null}">
                            <form action="update-project-type-criteria" method="post" class="row">
                                <input type="hidden" name="id" value="${ptCriteria.id}"/>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Name<span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" name="name" placeholder="Enter the Eval Criteria" 
                                           value="${ptCriteria.name}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Weight<span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" name="code" placeholder="Enter the Criteria weight" 
                                           value="${ptCriteria.weight}" required>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Project Phase<span style="color: red;">*</span></label>
                                    <select name="phaseId" class="form-select" required>
                                        <option value="" disable hidden>Choose Project Phase</option>
                                        <c:forEach items="${phase}" var="p">
                                            <option 
                                                <c:if test="${ptCriteria.pjPhase.id eq p.id}">
                                                    selected="selected"
                                                </c:if>
                                                value=${p.id}>${p.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-6 mt-1">
                                    <label class="form-label">Status<span style="color: red;">*</span></label>
                                    <div class="check">
                                        <input class="form-check-input" type="radio" name="status"
                                               <c:if test="${ptCriteria.status eq 'true'}">
                                                   checked
                                               </c:if>
                                               value="true"> Active
                                        <input class="form-check-input ms-3" type="radio" name="status"
                                               <c:if test="${ptCriteria.status eq 'false'}">
                                                   checked
                                               </c:if>
                                               value="false"> Inactive
                                    </div>
                                </div>
                                
                                <div class="mb-3 col-md-12">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-control" name="description" 
                                              placeholder="Enter the Criteria description" rows="3">${ptCriteria.description}</textarea>
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
