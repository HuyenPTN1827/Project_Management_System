<%-- 
    Document   : project-type-user-edit
    Created on : Oct 23, 2024, 8:30:22 AM
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
        <meta name="author" content="PMS">
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
        <div class="row">

            <div class="col-md-12 col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <form action="update-project-type-user" method="post" class="row">
                            <input type="hidden" name="id" value="${ptUser.id}">
                            <input type="hidden" name="userId" value="${ptUser.user.id}">
                            <input type="hidden" name="typeId" value="${ptUser.pjType.id}">

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Full Name</label>
                                <input type="text" class="form-control" name="fullname" placeholder="Full name" 
                                       value="${ptUser.user.full_name}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Username</label>
                                <input type="text" class="form-control" name="username" placeholder="Username" 
                                       value="${ptUser.setting.name}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Phone</label>
                                <input type="text" class="form-control" name="mobile" placeholder="Phone number"
                                       value="${ptUser.user.mobile}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Email</label>
                                <input type="text" class="form-control" name="email" placeholder="Email address" 
                                       value="${ptUser.user.email}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Role</label>
                                <select name="pjRole" class="form-select" required>
                                    <option value="" disable hidden>Choose Project Role</option>
                                    <%--<c:forEach items="${ptSetting}" var="r">--%>
                                    <!--                                                        <option 
                                    <%--<c:if test="${ptUser.ptSetting.id eq r.id}">--%>
                                        selected="selected"
                                    <%--</c:if>--%>
                                    value=${r.id}>${r.name}
                                </option>-->
                                    <%--</c:forEach>--%>

                                    <option 
                                        <c:if test="${ptUser.setting.id eq '2'}">
                                            selected="selected"
                                        </c:if>
                                        value="2">PMO Manager
                                    </option>
                                    <option 
                                        <c:if test="${ptUser.setting.id eq '5'}">
                                            selected="selected"
                                        </c:if>
                                        value="5">Project QA
                                    </option>
                                </select>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Status</label>
                                <div class="check">
                                    <input class="form-check-input" type="radio" name="status"
                                           <c:if test="${ptUser.status eq 'true'}">
                                               checked
                                           </c:if>
                                           value="true"> Active
                                    <input class="form-check-input ms-3" type="radio" name="status"
                                           <c:if test="${ptUser.status eq 'false'}">
                                               checked
                                           </c:if>
                                           value="false"> Inactive
                                </div>
                            </div>

                            <div class="d-flex justify-content-end">
                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

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
