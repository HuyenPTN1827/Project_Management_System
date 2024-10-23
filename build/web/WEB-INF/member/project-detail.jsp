<%-- 
    Document   : project-detail
    Created on : Oct 18, 2024, 10:18:04 AM
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
    <meta name="author" content="AdminKit">
    <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />
    <title>Project Type Details | PMS</title>

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
                    <c:if test="${projectType == null}">
                        <h1 class="h1 mb-3"> Create New Project Type</h1>
                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="insert-project-type" method="get" class="row">
                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Project Type Name<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="name" placeholder="Enter project type name" 
                                                       value="${name}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Project Type Code<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="code" placeholder="Enter project type code" 
                                                       value="${code}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Start Date<span style="color: red;">*</span></label>
                                                <input type="date" class="form-control" name="startDate" 
                                                       value="${startDate}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Type<span style="color: red;">*</span></label>
                                                <select class="form-control" name="type" required>
                                                    <option value="">Select Type</option>
                                                    <c:forEach var="typeItem" items="${projectTypes}">
                                                        <option value="${typeItem.id}" <c:if test="${typeItem.id == selectedTypeId}">selected</c:if>>${typeItem.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Department<span style="color: red;">*</span></label>
                                                <select class="form-control" name="department" required>
                                                    <option value="">Select Department</option>
                                                    <c:forEach var="dept" items="${departments}">
                                                        <option value="${dept.id}" <c:if test="${dept.id == selectedDeptId}">selected</c:if>>${dept.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3 col-md-12">
                                                <label class="form-label">Details</label>
                                                <textarea class="form-control" name="details" 
                                                          placeholder="Enter project type details" rows="3">${details}</textarea>
                                            </div>

                                            <div>
                                                <button type="submit" class="btn btn-lg btn-success">Save</button>
                                                <a href="<%=request.getContextPath()%>/project-type-management" class="btn btn-lg btn-light">Cancel</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if> 

                    <c:if test="${projectType != null}">
                        <h1 class="h1 mb-3"> Project Type Details</h1>
                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="update-project-type" method="post" class="row">
                                            <input type="hidden" name="id" value="${projectType.id}"/>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Project Type Name<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="name" placeholder="Enter project type name" 
                                                       value="${projectType.name}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Project Type Code<span style="color: red;">*</span></label>
                                                <input type="text" class="form-control" name="code" placeholder="Enter project type code" 
                                                       value="${projectType.code}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Start Date<span style="color: red;">*</span></label>
                                                <input type="date" class="form-control" name="startDate" 
                                                       value="${projectType.startDate}" required>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Type<span style="color: red;">*</span></label>
                                                <select class="form-control" name="type" required>
                                                    <option value="">Select Type</option>
                                                    <c:forEach var="typeItem" items="${projectTypes}">
                                                        <option value="${typeItem.id}" <c:if test="${typeItem.id == projectType.typeId}">selected</c:if>>${typeItem.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Department<span style="color: red;">*</span></label>
                                                <select class="form-control" name="department" required>
                                                    <option value="">Select Department</option>
                                                    <c:forEach var="dept" items="${departments}">
                                                        <option value="${dept.id}" <c:if test="${dept.id == projectType.departmentId}">selected</c:if>>${dept.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3 col-md-12">
                                                <label class="form-label">Details</label>
                                                <textarea class="form-control" name="details" 
                                                          placeholder="Enter project type details" rows="3">${projectType.details}</textarea>
                                            </div>

                                            <div class="mb-3 col-md-6">
                                                <label class="form-label">Status<span style="color: red;">*</span></label>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="status"
                                                           <c:if test="${projectType.status eq 'true'}">checked</c:if> value="true"> Active
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="status"
                                                           <c:if test="${projectType.status eq 'false'}">checked</c:if> value="false"> Inactive
                                                </div>
                                            </div>

                                            <div>
                                                <button type="submit" class="btn btn-lg btn-success">Save</button>
                                                <button type="reset" class="btn btn-lg btn-primary">Reset</button>
                                                <a href="<%=request.getContextPath()%>/project-type-management" class="btn btn-lg btn-light">Cancel</a>
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
