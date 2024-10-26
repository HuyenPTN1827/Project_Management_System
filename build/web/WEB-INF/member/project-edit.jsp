<%-- 
    Document   : project-edit
    Created on : Oct 22, 2024, 10:17:10 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Bootstrap 5 Admin & Dashboard Template">
        <title>Project Type Details | PMS</title>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>
                    <main class="content">
                        <div class="container-fluid p-0">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">
                                ${errorMessage}
                            </div>
                        </c:if>

                        <c:if test="${project != null}">
                            <h1 class="h1 mb-3">Project Edit</h1>
                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="update-project" method="post" class="row">
                                                <input type="hidden" name="id" value="${project.id}"/>

                                                <!-- Project Biz term -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Biz Term<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="name" value="${project.settingName}" required>
                                                </div>

                                                <!-- Project Name -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Project Name<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="name" value="${project.name}" required>
                                                </div>

                                                <!-- Project Code -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Project Code<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="code" value="${project.code}" required>
                                                </div>

                                                <!-- Type ID -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Type ID<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="typeId" value="${project.typeCode}" required>
                                                </div>

                                                <!-- Department ID -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Department ID<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="departmentId" value="${project.departmentCode}" required>
                                                </div>

                                                <!-- Start Date -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Start Date<span style="color: red;">*</span></label>
                                                    <input type="date" class="form-control" name="startDate" value="${project.startDate}" required>
                                                </div>

                                                <!-- Details -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Details</label>
                                                    <textarea class="form-control" name="details" rows="3">${project.details}</textarea>
                                                </div>

                                                <!-- Status -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Status<span style="color: red;">*</span></label>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="status" value="active" 
                                                               <c:if test="${project.status == true}">checked</c:if> />
                                                               <label class="form-check-label" style="color: green;">Active</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status" value="inactive" 
                                                            <c:if test="${project.status == false}">checked</c:if> />
                                                            <label class="form-check-label" style="color: red;">Inactive</label>
                                                        </div>
                                                    </div>


                                                    <!-- Display error message below form -->
                                                <c:if test="${not empty errorMessage}">
                                                    <div class="alert alert-danger mt-3">${errorMessage}</div>
                                                </c:if>

                                                <!-- Save and Cancel Buttons -->
                                                <div>
                                                    <button type="submit" class="btn btn-lg btn-success">Save</button>
                                                    <a href="${pageContext.request.contextPath}/project-management" class="btn btn-lg btn-light">Cancel</a>
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
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted">AdminKit</a> - 
                                    <a href="#" class="text-muted">Documentation</a>
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Support</a>
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
    </body>
</html>

