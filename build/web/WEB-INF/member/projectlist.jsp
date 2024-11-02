<%-- 
    Document   : projectlist
    Created on : Oct 17, 2024, 10:29:52 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
    <meta name="author" content="Admin">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />
    <title>Project Management | PMS</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
    <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    <style>
        body {
            opacity: 0;
        }
    </style>
</head>
<body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
    <div class="wrapper">
        <jsp:include page="../component/sidebar-manager.jsp"></jsp:include>
        <div class="main">
            <jsp:include page="../component/header.jsp"></jsp:include>
            <main class="content">
                <div class="container-fluid p-0">
                    <div class="mb-3">
                        <h1 class="h1 d-inline align-middle">Project Management</h1>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-xl-12">
                            <div class="card">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                        <form action="projectlist" method="GET" class="d-flex align-items-center" style="gap: 15px;">
                                            <select name="projectCode" class="form-select" style="width: 160px;" onchange="this.form.submit();">
                                                <option value="">Project Code</option>
                                                <c:forEach var="project" items="${listProjects}">
                                                    <option 
                                                        <c:if test="${project.code eq projectCode}">
                                                            selected="selected"
                                                        </c:if>
                                                        value="${project.code}">${project.code}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="status" class="form-select" style="width: 130px;" onchange="this.form.submit();">
                                                <option value="">All Status</option>
                                                <option value="true" <c:if test="${status == 'true'}">selected="selected"</c:if>>Active</option>
                                                <option value="false" <c:if test="${status == 'false'}">selected="selected"</c:if>>Inactive</option>
                                            </select>
                                            <input type="search" name="keyword" class="form-control" style="width: 270px;" placeholder="Enter Project Name or Code" id="keyword" value="${keyword}">
                                            <button type="submit" class="btn btn-primary">Search</button>
                                        </form>
                                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-project">Add New</a>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table id="datatables-multi" class="table table-striped" style="width:100%; table-layout: auto;">
                                        <thead>
                                            <tr style="text-align: center">
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Code</th>
                                                <th>Biz Term</th>
                                                <th>Type ID</th>
                                                <th>Department ID</th>
                                                <th>Start Date</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listProjects}" var="project">
                                                <tr style="text-align: center">
                                                    <td>${project.id}</td>
                                                    <td>${project.name}</td>
                                                    <td>${project.code}</td>
                                                    <td>${project.settingName}</td>
                                                    <td>${project.typeCode}</td>
                                                    <td>${project.departmentCode}</td>
                                                    <td>${project.startDate}</td>
                                                    <td>
                                                        <c:if test="${project.status}">
                                                            <span class="badge bg-success">Active</span>
                                                        </c:if>
                                                        <c:if test="${!project.status}">
                                                            <span class="badge bg-danger">Inactive</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href="<%=request.getContextPath()%>/edit-project?id=${project.id}" class="btn btn-link text-primary">Edit</a>
                                                        <a href="<%=request.getContextPath()%>/projectconfig?id=${project.id}" class="btn btn-link text-secondary">Config</a>
                                                        <c:if test="${project.status eq 'false'}">
                                                            <a href="<%=request.getContextPath()%>/change-status-project?id=${project.id}&status=${project.status}" class="btn btn-link text-success" onclick="return confirm('Are you sure you want to activate this project?');">Activate</a>
                                                        </c:if>
                                                        <c:if test="${project.status eq 'true'}">
                                                            <a href="<%=request.getContextPath()%>/change-status-project?id=${project.id}&status=${project.status}" class="btn btn-link text-danger" onclick="return confirm('Are you sure you want to deactivate this project?');">Deactivate</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
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
                                <li class="list-inline-item"><a class="text-muted" href="#">Support</a></li>
                                <li class="list-inline-item"><a class="text-muted" href="#">Help Center</a></li>
                                <li class="list-inline-item"><a class="text-muted" href="#">Privacy</a></li>
                                <li class="list-inline-item"><a class="text-muted" href="#">Terms</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <script src="${pageContext.request.contextPath}/js/datatables.js"></script>

    <script>
       
    </script>
</body>
</html>
