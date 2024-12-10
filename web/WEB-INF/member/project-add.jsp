<%-- 
    Document   : project-detail
    Created on : Dec 2, 2024, 6:42:14 PM
    Author     : BachHD
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

        <title>Add New Project | PMS</title>

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
                        <form action="insert-project" method="post" class="row">

                            <!-- Project Name Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Project Name</strong> <span style="color: red;">*</span></label>
                                <input type="text" class="form-control" name="name" placeholder="Enter project Name" value="${name}" required>
                            </div>

                            <!-- Project Code Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Project Code</strong> <span style="color: red;">*</span></label>
                                <input type="text" class="form-control" name="code" placeholder="Enter project Code" value="${code}" required>
                            </div>

                            <!--Type Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Project Type</strong> <span style="color: red;">*</span></label>
                                <select class="form-select" name="type" required>
                                    <option value="" hidden disable>Select Type</option>
                                    <c:forEach var="projecttype" items="${listProjectTypes}">
                                        <option value="${projecttype.id}" <c:if test="${projecttype.id == type}">selected</c:if>>
                                            ${projecttype.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>


                            <!-- Biz term Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Biz Term <span style="color: red;">*</span></label>
                                <select class="form-select" name="bizterm" required>
                                    <option value="" hidden disabled>Select Biz Term</option>
                                    <!-- Lặp qua danh sách bizTerms -->
                                    <c:forEach var="bizterm" items="${listBizTerms}">
                                        <option value="${bizterm.id}" 
                                                <c:if test="${bizterm.id == selectedBizTermId}">selected</c:if>>
                                            ${bizterm.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>




                            <!-- Estimated Effort Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Estimated Effort (man-days)</strong> <span style="color: red;">*</span></label>
                                <input type="number" class="form-control" name="estimatedEffort" placeholder="Enter estimated effort" value="${estimatedEffort}" required>
                            </div>


                            <!-- Department Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Department</strong><span style="color: red;">*</span></label>
                                <select class="form-select" name="department" required>
                                    <option value="">Select Department</option>
                                    <c:forEach var="projectdepartment" items="${listDepartments}">
                                        <option value="${projectdepartment.id}" <c:if test="${projectdepartment.id == department}">selected</c:if>>
                                            ${projectdepartment.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>


                            <!-- Start Date Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Start Date</label>
                                <input type="date" class="form-control" name="startDate" value="${startDate}" required>
                            </div>

                            <!-- Project Manager Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>Project Manager</strong> <span style="color: red;">*</span></label>
                                <select class="form-select" name="projectManagerId" required>
                                    <option value="" hidden disable>Select Project Manager</option>
                                    <!-- Populate options dynamically -->
                                    <c:forEach var="manager" items="${listManagers}">
                                        <option value="${manager.id}" <c:if test="${manager.id == projectManagerId}">selected</c:if>>
                                            ${manager.full_name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>



                            <!-- End Date Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label"><strong>End Date</strong></label>
                                <input type="date" class="form-control" name="endDate" value="${endDate}" required>
                            </div>

                            <!-- Descriptions Field -->
                            <div class="mb-3 col-md-12">
                                <label class="form-label"><strong>Description</strong></label>
                                <textarea class="form-control" name="details" placeholder="Enter project type details" rows="3">${details}</textarea>
                            </div>

                            ${errorMessage}

                            <!-- Save and Cancel Buttons -->
                            <div class="d-flex justify-content-end">
                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>
