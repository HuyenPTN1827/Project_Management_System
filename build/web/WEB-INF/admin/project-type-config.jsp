<%-- 
    Document   : project-type-config
    Created on : Oct 25, 2024, 03:06:24 AM
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

        <!--<link rel="canonical" href="tables-datatables-multi.html" />-->

        <title>Project Type Configs | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>
            body {
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

            function openProjectTypeModal(id = null) {
                let url = '<%=request.getContextPath()%>/add-project-type'; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project-type?id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#projectTypeModal .modal-body').innerHTML = data;
                            document.getElementById('projectTypeModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

//            function openPTUserModal(typeId, id = null) {
//                let url = '<%=request.getContextPath()%>/add-project-type-user?typeId=' + typeId; // Default for Create New
//                if (id) {
//                    url = '<%=request.getContextPath()%>/edit-project-type-user?typeId=' + typeId + '&id=' + id; // For Edit
//                }
//
//                fetch(url)
//                        .then(response => response.text())
//                        .then(data => {
//                            document.querySelector('#ptUserModal .modal-body').innerHTML = data;
//                            document.getElementById('ptUserModal').style.display = 'block';
//                        })
//                        .catch(error => console.log('Error loading the form:', error));
//            }

            function openPTCriteriaModal(typeId, id = null) {
                let url = '<%=request.getContextPath()%>/add-project-type-criteria?typeId=' + typeId; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project-type-criteria?typeId=' + typeId + '&id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#ptCriteriaModal .modal-body').innerHTML = data;
                            document.getElementById('ptCriteriaModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

            function closeModal() {
                document.getElementById('projectTypeModal').style.display = 'none';
            }

//            function closeptUserModal() {
//                document.getElementById('ptUserModal').style.display = 'none';
//            }

            function closeptCriteriaModal() {
                document.getElementById('ptCriteriaModal').style.display = 'none';
            }
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "project-type-management"); %>
            <jsp:include page="../component/sidebar-admin.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <a href="<%=request.getContextPath()%>/project-type-management">Project Type Management > </a>

                        <div class="mt-2 mb-3">
                            <h1 class="h1 d-inline align-middle">Project Type Configs</h1>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                            <form action="project-type-management" method="post" class="d-flex align-items-center" style="gap: 15px;">

                                                <div class="col-md-4">
                                                    <label class="form-label">Project Type</label>
                                                    <input type="text" class="form-control" name="name" value="${projectType.name}" readonly>
                                                </div>

                                                <div class="col-md-4">
                                                    <label class="form-label">Code</label>
                                                    <input type="text" class="form-control" name="code" value="${projectType.code}" readonly>
                                                </div>

                                                <div class="col-md-4 mt-4 d-flex align-items-end">
                                                    <a href="javascript:void(0);" class="btn btn-primary mt-1" 
                                                       onclick="openProjectTypeModal(${projectType.id});">View Details</a>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <!-- Project Type Modal -->
                                <div id="projectTypeModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Project Type Details</h1>
                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close" onclick="closeModal();">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <!-- This is where the project-type-detail.jsp will be loaded via AJAX -->
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--                                 Project Type Users Modal 
                                                                <div id="ptUserModal" class="modal" tabindex="-1" role="dialog">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h1 class="modal-title">User Details</h1>
                                                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close" onclick="closeptUserModal();">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                 This is where the project-type-user-list.jsp will be loaded via AJAX 
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>-->

                                <!--Project Type Criteria Modal--> 
                                <div id="ptCriteriaModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Project Type Criteria Details</h1>
                                                <button type="button" class="close btn btn-danger" data-dismiss="modal" aria-label="Close" onclick="closeptCriteriaModal();">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-type-user-list.jsp will be loaded via AJAX--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card">
                                    <div class="card-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="project-type-settings-tab" data-bs-toggle="tab" href="#project-type-settings" role="tab" aria-controls="project-type-settings" aria-selected="true">
                                                    Project Type Settings
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="project-type-users-tab" data-bs-toggle="tab" href="#project-type-users" role="tab" aria-controls="project-type-users" aria-selected="false">
                                                    Project Type Users
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="project-phases-tab" data-bs-toggle="tab" href="#project-phases" role="tab" aria-controls="project-phases" aria-selected="false">
                                                    Project Phases
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="eval-criteria-tab" data-bs-toggle="tab" href="#eval-criteria" role="tab" aria-controls="eval-criteria" aria-selected="false">
                                                    Eval Criteria
                                                </a>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <!-- Project Type Settings -->
                                            <div class="tab-pane fade show active" id="project-type-settings" role="tabpanel" aria-labelledby="project-type-settings-tab">

                                                <div class="row">
                                                    <div class="col-md-12 col-xl-12">
                                                        <div class="card" style="margin: 0px">
                                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                                <form action="project-type-config" method="get" class="d-flex align-items-center" style="gap: 15px;">
                                                                    <select name="statusFilter" class="form-select"  style="width: 130px;">
                                                                        <option value="">All Status</option>
                                                                        <option 
                                                                            <c:if test="${param.status eq 'true'}">
                                                                                selected="selected"
                                                                            </c:if>
                                                                            value="true">Active
                                                                        </option>
                                                                        <option 
                                                                            <c:if test="${param.status eq 'false'}">
                                                                                selected="selected"
                                                                            </c:if>
                                                                            value="false">Inactive
                                                                        </option>
                                                                    </select>

                                                                    <input type="search" name="keyword" class="form-control" style="width: 270px;"
                                                                           placeholder="Enter Project Type Name or Code" id="keyword" value="${param.keyword}">
                                                                    <input type="hidden" name="id" value="${param.id}"/>      
                                                                    <button type="submit" class="btn btn-primary">Search</button>

                                                                </form>

                                                                <a class="btn btn-primary" href="./ProjectTypeSetting?action=add&cuId=${param.id}">Create new</a>
                                                            </div>
                                                        </div>
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <table id="datatables-multi5" class="table table-striped" style="width:100%">
                                                                    <thead>
                                                                        <tr style="text-align: center">
                                                                            <th>ID</th>
                                                                            <th>Name</th>
                                                                            <th>Type</th>
                                                                            <th>Value</th>
                                                                            <th>Status</th>
                                                                            <th>Action</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${requestScope.sl}" var="type">
                                                                            <tr style="text-align: center">
                                                                                <td>${type.id}</td>
                                                                                <td>${type.name}</td>
                                                                                <td>${type.type}</td>
                                                                                <td>${type.value}</td>
                                                                                <td>
                                                                                    <c:if test="${type.status eq 'true'}">
                                                                                        <span class="badge bg-success">Active</span>
                                                                                    </c:if>
                                                                                    <c:if test="${type.status eq 'false'}">
                                                                                        <span class="badge bg-danger">Inactive</span>
                                                                                    </c:if>
                                                                                </td>
                                                                                <td>
                                                                                    <a href="./ProjectTypeSetting?action=edit&id=${type.id}&cuId=${param.id}" 
                                                                                       class="btn btn-link text-primary">Edit</a>

                                                                                    <c:if test="${type.status eq 'false'}">
                                                                                        <a href="./ProjectTypeSetting?action=changeStatus&id=${type.id}&status=true&cuId=${param.id}"
                                                                                           class="btn btn-link text-success"
                                                                                           onclick="return confirm('Are you sure you want to activate this project type?');">Activate</a>
                                                                                    </c:if>

                                                                                    <c:if test="${type.status eq 'true'}">
                                                                                        <a href="./ProjectTypeSetting?action=changeStatus&id=${type.id}&status=false&cuId=${param.id}"
                                                                                           class="btn btn-link text-danger"
                                                                                           onclick="return confirm('Are you sure you want to deactivate this project type?');">Deactivate</a>
                                                                                    </c:if>

                                                                                    <a href="./ProjectTypeSetting?action=delete&id=${type.id}&cuId=${param.id}"
                                                                                       class="btn btn-link text-danger"
                                                                                       onclick="return confirm('Are you sure you want to deactivate this project type?');">Delete</a>
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

                                            <!-- Project Type Users -->
                                            <div class="tab-pane fade" id="project-type-users" role="tabpanel" aria-labelledby="project-type-users-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <div class="col-md-3">
                                                            <select name="roleId" class="form-select">
                                                                <option value="">All Project Roles</option>
                                                                <c:forEach items="${ptSetting}" var="s">
                                                                    <option 
                                                                        <c:if test="${roleId eq s.id}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                        value="${s.id}">${s.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <select name="statusUser" class="form-select">
                                                                <option value="">All Statuses</option>
                                                                <option 
                                                                    <c:if test="${statusUser eq 'true'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="true">Active
                                                                </option>
                                                                <option 
                                                                    <c:if test="${statusUser eq 'false'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="false">Inactive
                                                                </option>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-5">
                                                            <input type="search" name="keywordUser" class="form-control"  style="width: 270px;"
                                                                   placeholder="Enter the Full Name" id="keywordUser" value="${keywordUser}">
                                                        </div>

                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                    </form>

                                                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-project-type-user?typeId=${projectType.id}">Create new</a>
                                                    <!--<a class="btn btn-primary" href="javascript:void(0);" onclick="openPTUserModal(${projectType.id});">Create new</a>-->
                                                </div>

                                                <table id="datatables-multi" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th hidden>ID</th>
                                                            <th>ID</th>
                                                            <th>Full Name</th>
                                                            <th>Project Role</th>
                                                            <th>Start date</th>
                                                            <th>End Date</th>
                                                            <th>Status</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.ptUser}" var="ptu">
                                                            <tr>
                                                                <td hidden>${ptu.id}</td>
                                                                <td>${ptu.user.id}</td>
                                                                <td>${ptu.user.full_name}</td>
                                                                <td>${ptu.ptSetting.name}</td>
                                                                <td>${ptu.start_date}</td>
                                                                <td>${ptu.end_date}</td>
                                                                <td>
                                                                    <c:if test="${ptu.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                    <c:if test="${ptu.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${ptu.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/edit-project-type-user?id=${ptu.id}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-primary">Edit</a>

                                                                        <!--                                                                <a href="javascript:void(0);" class="btn btn-link text-primary" 
                                                                                                                                           onclick="openPTUserModal(${projectType.id}, ${ptu.id});">Edit</a>-->

                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?id=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-success"
                                                                           onclick="return confirm('Are you sure you want to activate this user?');">Activate</a>
                                                                    </c:if>

                                                                    <c:if test="${ptu.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/edit-project-type-user?id=${ptu.id}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-primary">Edit</a>

                                                                        <!--                                                                <a href="javascript:void(0);" class="btn btn-link text-primary" 
                                                                                                                                            onclick="openPTUserModal(${projectType.id}, ${ptu.id});">Edit</a>-->

                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?id=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this user?');">Deactivate</a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Project Phases -->
                                            <div class="tab-pane fade" id="project-phases" role="tabpanel" aria-labelledby="project-phases-tab">
                                                <!-- Content for Project Phases tab -->
                                                <h4>Project Phases Table</h4>
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-user" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">

                                                        <select name="roleId" class="form-select">
                                                            <option value="">All Project Roles</option>
                                                            <c:forEach items="${ptSetting}" var="s">
                                                                <option 
                                                                    <c:if test="${roleId eq s.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="${s.id}">${s.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>

                                                        <select name="status" class="form-select">
                                                            <option value="">All Statuses</option>
                                                            <option 
                                                                <c:if test="${status eq 'true'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="true">Active
                                                            </option>
                                                            <option 
                                                                <c:if test="${status eq 'false'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="false">Inactive
                                                            </option>
                                                        </select>

                                                        <input type="search" name="keyword" class="form-control"  style="width: 270px;"
                                                               placeholder="Enter the Full Name" id="keyword" value="${keyword}">

                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                    </form>

                                                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-project-type-user">Create new</a>
                                                </div>

                                                <table id="datatables-multi1" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th hidden>ID</th>
                                                            <th>ID</th>
                                                            <th>Full Name</th>
                                                            <th>Project Role</th>
                                                            <th>Start date</th>
                                                            <th>End Date</th>
                                                            <th>Status</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.ptUser}" var="ptu">
                                                            <tr>
                                                                <td hidden>${ptu.id}</td>
                                                                <td>${ptu.user.id}</td>
                                                                <td>${ptu.user.full_name}</td>
                                                                <td>${ptu.ptSetting.name}</td>
                                                                <td>${ptu.start_date}</td>
                                                                <td>${ptu.end_date}</td>
                                                                <td>
                                                                    <c:if test="${ptu.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                    <c:if test="${ptu.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${ptu.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/edit-project-type-user?id=${ptu.id}"
                                                                           class="btn btn-link text-primary">Details</a>

                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?recordId=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-success"
                                                                           onclick="return confirm('Are you sure you want to activate this user?');">Activate</a>
                                                                    </c:if>

                                                                    <c:if test="${ptu.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/edit-project-type-user?id=${ptu.id}"
                                                                           class="btn btn-link text-primary">Details</a>

                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?recordId=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-link text-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this user?');">Deactivate</a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Eval Criteria -->
                                            <div class="tab-pane fade" id="eval-criteria" role="tabpanel" aria-labelledby="eval-criteria-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <div class="col-md-3">
                                                        <select name="phaseId" class="form-select">
                                                            <option value="">All Project Phase</option>
                                                            <c:forEach items="${phase}" var="p">
                                                                <option 
                                                                    <c:if test="${phaseId eq p.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="${p.id}">${p.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                </div>

                                                <div class="col-md-3">
                                                    <select name="statusCriteria" class="form-select">
                                                        <option value="">All Statuses</option>
                                                        <option 
                                                            <c:if test="${statusCriteria eq 'true'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="true">Active
                                                        </option>
                                                        <option 
                                                            <c:if test="${statusCriteria eq 'false'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="false">Inactive
                                                        </option>
                                                    </select>
                                                </div>

                                                <div class="col-md-5">
                                                    <input type="search" name="keywordCriteria" class="form-control"
                                                           placeholder="Enter the Eval Criteria" id="keywordCriteria" value="${keywordCriteria}">
                                                </div>
                                                <button type="submit" class="btn btn-primary">Search</button>

                                                </form>

                                                    <!--<a class="btn btn-primary" href="<%=request.getContextPath()%>/add-project-type-criteria?typeId=${projectType.id}">Create new</a>-->
                                                <a class="btn btn-primary" href="javascript:void(0);" onclick="openPTCriteriaModal(${projectType.id});">Create new</a>
                                            </div>

                                            <table id="datatables-multi2" class="table table-striped" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Criteria</th>
                                                        <th>Project Phase</th>
                                                        <th>Weight</th>
                                                        <th>Status</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.ptCriteria}" var="ptc">
                                                        <tr>
                                                            <td>${ptc.id}</td>
                                                            <td>${ptc.name}</td>
                                                            <td>${ptc.pjPhase.name}</td>
                                                            <td>${ptc.weight}</td>
                                                            <td>
                                                                <c:if test="${ptc.status eq 'false'}">
                                                                    <span class="badge bg-danger">Inactive</span>
                                                                </c:if>
                                                                <c:if test="${ptc.status eq 'true'}">
                                                                    <span class="badge bg-success">Active</span>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <!--<a href="<%=request.getContextPath()%>/edit-project-type-criteria?id=${ptc.id}&typeId=${projectType.id}"
                                                                       class="btn btn-link text-primary">Edit</a>-->

                                                                <a href="javascript:void(0);" class="btn btn-link text-primary" 
                                                                   onclick="openPTCriteriaModal(${projectType.id}, ${ptc.id});">Edit</a>

                                                                <c:if test="${ptc.status eq 'false'}">
                                                                    <a href="<%=request.getContextPath()%>/change-status-project-type-criteria?id=${ptc.id}&status=${ptc.status}&typeId=${projectType.id}"
                                                                       class="btn btn-link text-success"
                                                                       onclick="return confirm('Are you sure you want to activate this criteria?');">Activate</a>
                                                                </c:if>

                                                                <c:if test="${ptc.status eq 'true'}">
                                                                    <a href="<%=request.getContextPath()%>/change-status-project-type-criteria?id=${ptc.id}&status=${ptc.status}&typeId=${projectType.id}"
                                                                       class="btn btn-link text-danger"
                                                                       onclick="return confirm('Are you sure you want to deactivate this criteria?');">Deactivate</a>
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

                            <!-- Include Bootstrap JS (CDN or local) -->
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

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

        <script src="${pageContext.request.contextPath}/js/datatables.js"></script>

        <script>
                                                                           document.addEventListener("DOMContentLoaded", function () {
                                                                               var datatablesMulti = $("#datatables-multi").DataTable({
                                                                                   responsive: true,
                                                                                   paging: true,
                                                                                   searching: false,
                                                                                   info: true,
                                                                                   order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                                   columnDefs: [
                                                                                       {orderable: false, targets: 7} // Disable sorting on the 'Action' column
                                                                                   ],
                                                                                   language: {
                                                                                       paginate: {
                                                                                           previous: "&laquo;",
                                                                                           next: "&raquo;"
                                                                                       },
                                                                                       info: "_TOTAL_ user(s) found",
                                                                                       infoEmpty: "No user found"
                                                                                   },
                                                                                   dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                   initComplete: function () {
                                                                                       // Add necessary classes for alignment
                                                                                       $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                       $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes
                                                                                   }
                                                                               });
                                                                           });
                                                                           document.addEventListener("DOMContentLoaded", function () {
                                                                               var datatablesMulti = $("#datatables-multi1").DataTable({
                                                                                   responsive: true,
                                                                                   paging: true,
                                                                                   searching: false,
                                                                                   info: true,
                                                                                   order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                                   columnDefs: [
                                                                                       {orderable: false, targets: 7} // Disable sorting on the 'Action' column
                                                                                   ],
                                                                                   language: {
                                                                                       paginate: {
                                                                                           previous: "&laquo;",
                                                                                           next: "&raquo;"
                                                                                       },
                                                                                       info: "_TOTAL_ user(s) found",
                                                                                       infoEmpty: "No user found"
                                                                                   },
                                                                                   dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                   initComplete: function () {
                                                                                       // Add necessary classes for alignment
                                                                                       $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                       $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes
                                                                                   }
                                                                               });
                                                                           });
                                                                           document.addEventListener("DOMContentLoaded", function () {
                                                                               var datatablesMulti = $("#datatables-multi2").DataTable({
                                                                                   responsive: true,
                                                                                   paging: true,
                                                                                   searching: false,
                                                                                   info: true,
                                                                                   order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                                   columnDefs: [
                                                                                       {orderable: false, targets: 5} // Disable sorting on the 'Action' column
                                                                                   ],
                                                                                   language: {
                                                                                       paginate: {
                                                                                           previous: "&laquo;",
                                                                                           next: "&raquo;"
                                                                                       },
                                                                                       info: "_TOTAL_ criteria(s) found",
                                                                                       infoEmpty: "No criteria found"
                                                                                   },
                                                                                   dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                   initComplete: function () {
                                                                                       // Add necessary classes for alignment
                                                                                       $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                       $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes
                                                                                   }
                                                                               });
                                                                           });
                                                                           document.addEventListener("DOMContentLoaded", function () {
                                                                               var datatablesMulti = $("#datatables-multi5").DataTable({
                                                                                   responsive: true,
                                                                                   paging: true,
                                                                                   searching: false,
                                                                                   info: true,
                                                                                   order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                                   columnDefs: [
                                                                                       {orderable: false, targets: 7} // Disable sorting on the 'Action' column
                                                                                   ],
                                                                                   language: {
                                                                                       paginate: {
                                                                                           previous: "&laquo;",
                                                                                           next: "&raquo;"
                                                                                       },
                                                                                       info: "_TOTAL_ user(s) found",
                                                                                       infoEmpty: "No user found"
                                                                                   },
                                                                                   dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                   initComplete: function () {
                                                                                       // Add necessary classes for alignment
                                                                                       $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                       $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes
                                                                                   }
                                                                               });
                                                                           });
        </script>

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