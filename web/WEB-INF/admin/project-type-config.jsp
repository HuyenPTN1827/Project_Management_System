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
        <meta name="author" content="PMS">
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

            function openSettingModal(typeId, id = null) {
                let url = '<%=request.getContextPath()%>/add-project-type-setting?typeId=' + typeId; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project-type-setting?typeId=' + typeId + '&id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#settingModal .modal-body').innerHTML = data;
                            document.getElementById('settingModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

            function openPTUserModal(typeId, id = null) {
                let url = '<%=request.getContextPath()%>/add-project-type-user?typeId=' + typeId; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project-type-user?typeId=' + typeId + '&id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#ptUserModal .modal-body').innerHTML = data;
                            document.getElementById('ptUserModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

            function openPhaseModal(typeId, id = null) {
                let url = '<%=request.getContextPath()%>/add-project-phase?typeId=' + typeId; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project-phase?typeId=' + typeId + '&id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#phaseModal .modal-body').innerHTML = data;
                            document.getElementById('phaseModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

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

            function closeSettingModal() {
                document.getElementById('settingModal').style.display = 'none';
            }

            function closeptUserModal() {
                document.getElementById('ptUserModal').style.display = 'none';
            }

            function closePhaseModal() {
                document.getElementById('phaseModal').style.display = 'none';
            }

            function closeptCriteriaModal() {
                document.getElementById('ptCriteriaModal').style.display = 'none';
            }

            function filterFunction(dropdownId, inputId) {
                const input = document.getElementById(inputId);
                const filter = input.value.toUpperCase();
                const ul = document.getElementById(dropdownId);
                const items = ul.getElementsByTagName("a");
                for (let i = 0; i < items.length; i++) {
                    const txtValue = items[i].textContent || items[i].innerText;
                    if (txtValue.toUpperCase().includes(filter)) {
                        items[i].style.display = "";
                    } else {
                        items[i].style.display = "none";
                    }
                }
            }

            function redirectToConfigPage() {
                const selectedId = document.getElementById("projectTypeDropdown").value;
                const action = document.getElementById("action").value;
                if (selectedId) {
                    // Redirect to the project type config page with the selected ID
                    window.location.href = 'project-type-config?id=' + selectedId + '&action=' + action;
                }
            }

            document.querySelectorAll('.nav-link').forEach(tab => {
                tab.addEventListener('click', function () {
                    const url = new URL(window.location);
                    url.searchParams.set('activeTab', this.id.split('-tab')[0]); // Lấy id tab
                    history.replaceState(null, '', url);
                });
            });
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "project-type-management"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <a href="<%=request.getContextPath()%>/project-type-management">Project Type Management > </a>

                        <div class="mt-2 mb-3">
                            <h1 class="h1 d-inline align-middle">Project Type Configs</h1>
                            <input type="hidden" id="action" value="${action}"/>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <!--Project Type Setting Modal--> 
                                <div id="settingModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Project Type Setting Details</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeSettingModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-type-setting-detail.jsp will be loaded via AJAX--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--Project Type Users Modal--> 
                                <div id="ptUserModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">User Details</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeptUserModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-type-user-detail.jsp will be loaded via AJAX--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--Project Phase Modal--> 
                                <div id="phaseModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Project Phase Details</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closePhaseModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-phase-detail.jsp will be loaded via AJAX--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--Project Type Criteria Modal--> 
                                <div id="ptCriteriaModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Project Type Criteria Details</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeptCriteriaModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-type-criteria-detail.jsp will be loaded via AJAX--> 
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card">
                                    <div class="card-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'setting' ? 'active' : ''}" id="project-type-settings-tab" data-bs-toggle="tab" href="#project-type-settings" role="tab" aria-controls="project-type-settings" aria-selected="true">
                                                    Project Type Settings
                                                </a>
                                            </li>
                                            <li class="nav-item" hidden>
                                                <a class="nav-link ${activeTab == 'manager' ? 'active' : ''}" id="project-type-users-tab" data-bs-toggle="tab" href="#project-type-users" role="tab" aria-controls="project-type-users" aria-selected="false">
                                                    Project Type Users
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'phase' ? 'active' : ''}" id="project-phases-tab" data-bs-toggle="tab" href="#project-phases" role="tab" aria-controls="project-phases" aria-selected="false">
                                                    Project Phases
                                                </a>
                                            </li>
                                            <li class="nav-item" hidden>
                                                <a class="nav-link ${activeTab == 'criteria' ? 'active' : ''}" id="eval-criteria-tab" data-bs-toggle="tab" href="#eval-criteria" role="tab" aria-controls="eval-criteria" aria-selected="false">
                                                    Eval Criteria
                                                </a>
                                            </li>
                                            <!-- Dropdown for selecting Project Type -->
                                            <li class="nav-item col-md-3 ms-auto">
                                                <select id="projectTypeDropdown" class="form-select" onchange="redirectToConfigPage()">
                                                    <c:forEach items="${listPjType}" var="pt">
                                                        <option 
                                                            <c:if test="${projectType.id eq pt.id}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="${pt.id}">${pt.name} (${pt.code})
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <!-- Project Type Settings -->
                                            <div class="tab-pane fade ${activeTab == 'setting' ? 'show active' : ''}" id="project-type-settings" role="tabpanel" aria-labelledby="project-type-settings-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <input type="hidden" name="activeTab" value="setting">

                                                        <div class="col-md-3">
                                                            <select name="type" class="form-select">
                                                                <option value="">All Types</option>
                                                                <option 
                                                                    <c:if test="${type eq 'parent'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="parent">Parent Setting
                                                                </option> 
                                                                <c:forEach items="${listType}" var="t">
                                                                    <option 
                                                                        <c:if test="${type eq t.name}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                        value="${t.name}">${t.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <select name="statusSetting" class="form-select">
                                                                <option value="">All Status</option>
                                                                <option 
                                                                    <c:if test="${statusSetting eq 'true'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="true">Active
                                                                </option>
                                                                <option 
                                                                    <c:if test="${statusSetting eq 'false'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="false">Inactive
                                                                </option>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-5">
                                                            <input type="search" name="keywordSetting" class="form-control"
                                                                   placeholder="Enter Setting Name or value" id="keywordSetting" value="${keywordSetting}">
                                                        </div>

                                                        <div>
                                                            <button type="submit" class="btn btn-primary">Search</button>
                                                        </div>
                                                    </form>

                                                    <a class="btn btn-primary" href="javascript:void(0);" onclick="openSettingModal(${projectType.id});">Create new</a>

                                                </div>

                                                <table id="datatables-multi3" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
                                                            <th>ID</th>
                                                            <th>Name</th>
                                                            <th>Type</th>
                                                            <th>Value</th>
                                                            <th>Priority</th>
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
                                                                <td>${type.priority}</td>
                                                                <td>
                                                                    <c:if test="${type.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                    <c:if test="${type.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <a href="javascript:void(0);" class="btn btn-info" 
                                                                       onclick="openSettingModal(${projectType.id}, ${type.id});">
                                                                        <i class="align-middle" data-feather="edit"></i>
                                                                    </a>

                                                                    <c:if test="${type.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-setting?id=${type.id}&status=${type.status}&typeId=${projectType.id}"
                                                                           class="btn btn-success"
                                                                           onclick="return confirm('Are you sure you want to activate this project type setting?');">
                                                                            <i class="fas fa-check"></i>
                                                                        </a>
                                                                    </c:if>

                                                                    <c:if test="${type.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-setting?id=${type.id}&status=${type.status}&typeId=${projectType.id}"
                                                                           class="btn btn-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this project type setting?');">
                                                                            <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                        </a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Project Type Users -->
                                            <div hidden class="tab-pane fade ${activeTab == 'manager' ? 'show active' : ''}" id="project-type-users" role="tabpanel" aria-labelledby="project-type-users-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 10px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <input type="hidden" name="activeTab" value="manager">

                                                        <div class="col-md-3">
                                                            <select name="roleId" class="form-select">
                                                                <option value="">All Project Roles</option>
                                                                <%--<c:forEach items="${ptSetting}" var="s">--%>
                                                                <!--                                                                    <option 
                                                                <%--<c:if test="${roleId eq s.id}">--%>
                                                                    selected="selected"
                                                                <%--</c:if>--%>
                                                                value="${s.id}">${s.name}
                                                            </option>-->
                                                                <%--</c:forEach>--%>

                                                                <option 
                                                                    <c:if test="${roleId eq '2'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="2">PMO Manager
                                                                </option>
                                                                <option 
                                                                    <c:if test="${roleId eq '5'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="5">Project QA
                                                                </option>
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

                                                        <div class="col-md-4">
                                                            <input type="search" name="keywordUser" class="form-control"
                                                                   placeholder="Enter the Full Name" id="keywordUser" value="${keywordUser}">
                                                        </div>

                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                    </form>

                                                    <!--<a class="btn btn-primary" href="<%=request.getContextPath()%>/add-project-type-user?typeId=${projectType.id}">Create new</a>-->
                                                    <!--<a class="btn btn-primary" href="javascript:void(0);" onclick="openPTUserModal(${projectType.id});">Create new</a>-->

                                                    <div class="btn-group ms-4" style="gap: 10px;">
                                                        <button type="button" class="btn btn-primary dropdown-toggle" 
                                                                data-bs-toggle="dropdown" aria-expanded="false">
                                                            Add PMO Manager
                                                        </button>
                                                        <ul id="pmoDropdown" class="dropdown-menu dropdown-menu-end">
                                                            <li>
                                                                <input class="dropdown-item form-control" type="text" 
                                                                       placeholder="Full name/Username/Email..." id="pmoInput" 
                                                                       onkeyup="filterFunction('pmoDropdown', 'pmoInput')">
                                                            </li>
                                                            <c:forEach items="${listPMO}" var="m">
                                                                <li>
                                                                    <hr class="dropdown-divider">
                                                                    <a class="dropdown-item" 
                                                                       href="<%=request.getContextPath()%>/insert-project-type-user?typeId=${projectType.id}&userId=${m.id}&roleId=2">
                                                                        ${m.full_name} (${m.username})
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>

                                                        <button type="button" class="btn btn-primary dropdown-toggle" 
                                                                data-bs-toggle="dropdown" aria-expanded="false">
                                                            Add Project QA
                                                        </button>
                                                        <ul id="qaDropdown" class="dropdown-menu dropdown-menu-end">
                                                            <li>
                                                                <input class="dropdown-item form-control" type="text" 
                                                                       placeholder="Full name/Username/Email..." id="qaInput" 
                                                                       onkeyup="filterFunction('qaDropdown', 'qaInput')">
                                                            </li>
                                                            <c:forEach items="${listQA}" var="m">
                                                                <li>
                                                                    <hr class="dropdown-divider">
                                                                    <a class="dropdown-item" 
                                                                       href="<%=request.getContextPath()%>/insert-project-type-user?typeId=${projectType.id}&userId=${m.id}&roleId=5">
                                                                        ${m.full_name} (${m.username})
                                                                    </a>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>

                                                <table id="datatables-multi" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
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
                                                            <tr style="text-align: center">
                                                                <td hidden>${ptu.id}</td>
                                                                <td>${ptu.user.id}</td>
                                                                <td>${ptu.user.full_name}</td>
                                                                <td>${ptu.setting.name}</td>
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
                                                                    <a href="javascript:void(0);" class="btn btn-info" 
                                                                       onclick="openPTUserModal(${projectType.id}, ${ptu.id});"><i class="align-middle" data-feather="edit"></i></a>

                                                                    <c:if test="${ptu.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?id=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-success"
                                                                           onclick="return confirm('Are you sure you want to activate this user?');">
                                                                            <i class="fas fa-check"></i>
                                                                        </a>
                                                                    </c:if>

                                                                    <c:if test="${ptu.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-user?id=${ptu.id}&status=${ptu.status}&typeId=${projectType.id}"
                                                                           class="btn btn-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this user?');">
                                                                            <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                        </a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Project Phases -->
                                            <div class="tab-pane fade ${activeTab == 'phase' ? 'show active' : ''}" id="project-phases" role="tabpanel" aria-labelledby="project-phases-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <input type="hidden" name="activeTab" value="phase">

                                                        <div class="col-md-4">
                                                            <select name="statusPhase" class="form-select">
                                                                <option value="">All Statuses</option>
                                                                <option 
                                                                    <c:if test="${statusPhase eq 'true'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="true">Active
                                                                </option>
                                                                <option 
                                                                    <c:if test="${statusPhase eq 'false'}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="false">Inactive
                                                                </option>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-7">
                                                            <input type="search" name="keywordPhase" class="form-control"
                                                                   placeholder="Enter the Project Phase" id="keywordPhase" value="${keywordPhase}">
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                    </form>

                                                    <a class="btn btn-primary" href="javascript:void(0);" onclick="openPhaseModal(${projectType.id});">Create new</a>

                                                </div>

                                                <table id="datatables-multi1" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
                                                            <th>ID</th>
                                                            <th>Project Phase</th>
                                                            <th>Priority</th>
                                                            <th>Status</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.phase}" var="p">
                                                            <tr style="text-align: center">
                                                                <td>${p.id}</td>
                                                                <td>${p.name}</td>
                                                                <td>${p.priority}</td>
                                                                <td>
                                                                    <c:if test="${p.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                    <c:if test="${p.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <a href="javascript:void(0);" class="btn btn-info" 
                                                                       onclick="openPhaseModal(${projectType.id}, ${p.id});">
                                                                        <i class="align-middle" data-feather="edit"></i>
                                                                    </a>

                                                                    <c:if test="${p.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-phase?id=${p.id}&status=${p.status}&typeId=${projectType.id}"
                                                                           class="btn btn-success"
                                                                           onclick="return confirm('Are you sure you want to activate this project phase?');">
                                                                            <i class="fas fa-check"></i>
                                                                        </a>
                                                                    </c:if>

                                                                    <c:if test="${p.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-phase?id=${p.id}&status=${p.status}&typeId=${projectType.id}"
                                                                           class="btn btn-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this project phase?');">
                                                                            <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                        </a>
                                                                    </c:if>

                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Eval Criteria -->
                                            <div hidden class="tab-pane fade ${activeTab == 'criteria' ? 'show active' : ''}" id="eval-criteria" role="tabpanel" aria-labelledby="eval-criteria-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="project-type-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${projectType.id}">
                                                        <input type="hidden" name="activeTab" value="criteria">

                                                        <div class="col-md-3">
                                                            <select name="phaseId" class="form-select">
                                                                <option value="">All Project Phase</option>
                                                                <c:forEach items="${listPhase}" var="p">
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

                                                    <a class="btn btn-primary" href="javascript:void(0);" onclick="openPTCriteriaModal(${projectType.id});">Create new</a>
                                                </div>

                                                <table id="datatables-multi2" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
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
                                                            <tr style="text-align: center">
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
                                                                    <a href="javascript:void(0);" class="btn btn-info" 
                                                                       onclick="openPTCriteriaModal(${projectType.id}, ${ptc.id});">
                                                                        <i class="align-middle" data-feather="edit"></i>
                                                                    </a>

                                                                    <c:if test="${ptc.status eq 'false'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-criteria?id=${ptc.id}&status=${ptc.status}&typeId=${projectType.id}"
                                                                           class="btn btn-success"
                                                                           onclick="return confirm('Are you sure you want to activate this criteria?');">
                                                                            <i class="fas fa-check"></i>
                                                                        </a>
                                                                    </c:if>

                                                                    <c:if test="${ptc.status eq 'true'}">
                                                                        <a href="<%=request.getContextPath()%>/change-status-project-type-criteria?id=${ptc.id}&status=${ptc.status}&typeId=${projectType.id}"
                                                                           class="btn btn-danger"
                                                                           onclick="return confirm('Are you sure you want to deactivate this criteria?');">
                                                                            <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                        </a>
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
                        </div>
                    </div>
                </main>

                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col-6 text-start">
                                <p class="mb-0">
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>PMS</strong></a> &copy;
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
                                                                                               previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                                               next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                                           },
                                                                                           info: "_TOTAL_ user(s) found",
                                                                                           infoEmpty: "No user found"
                                                                                       },
                                                                                       dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                       initComplete: function () {
                                                                                           // Add necessary classes for alignment
                                                                                           $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                           $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                                           // Replace Feather icons after DataTable initializes
                                                                                           feather.replace();
                                                                                       }
                                                                                   });

                                                                                   // Replace Feather icons in case of dynamic changes
                                                                                   datatablesMulti.on('draw', function () {
                                                                                       feather.replace();
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
                                                                                           {orderable: false, targets: 4} // Disable sorting on the 'Action' column
                                                                                       ],
                                                                                       language: {
                                                                                           paginate: {
                                                                                               previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                                               next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                                           },
                                                                                           info: "_TOTAL_ phase(s) found",
                                                                                           infoEmpty: "No phase found"
                                                                                       },
                                                                                       dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                       initComplete: function () {
                                                                                           // Add necessary classes for alignment
                                                                                           $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                           $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                                           // Replace Feather icons after DataTable initializes
                                                                                           feather.replace();
                                                                                       }
                                                                                   });

                                                                                   // Replace Feather icons in case of dynamic changes
                                                                                   datatablesMulti.on('draw', function () {
                                                                                       feather.replace();
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
                                                                                               previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                                               next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                                           },
                                                                                           info: "_TOTAL_ criteria(s) found",
                                                                                           infoEmpty: "No criteria found"
                                                                                       },
                                                                                       dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                       initComplete: function () {
                                                                                           // Add necessary classes for alignment
                                                                                           $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                           $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                                           // Replace Feather icons after DataTable initializes
                                                                                           feather.replace();
                                                                                       }
                                                                                   });

                                                                                   // Replace Feather icons in case of dynamic changes
                                                                                   datatablesMulti.on('draw', function () {
                                                                                       feather.replace();
                                                                                   });
                                                                               });

                                                                               document.addEventListener("DOMContentLoaded", function () {
                                                                                   var datatablesMulti = $("#datatables-multi3").DataTable({
                                                                                       responsive: true,
                                                                                       paging: true,
                                                                                       searching: false,
                                                                                       info: true,
                                                                                       order: [[0, 'desc']], // Default sort by ID column in descending order
                                                                                       columnDefs: [
                                                                                           {orderable: false, targets: 6} // Disable sorting on the 'Action' column
                                                                                       ],
                                                                                       language: {
                                                                                           paginate: {
                                                                                               previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                                               next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                                           },
                                                                                           info: "_TOTAL_ setting(s) found",
                                                                                           infoEmpty: "No setting found"
                                                                                       },
                                                                                       dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                                                       initComplete: function () {
                                                                                           // Add necessary classes for alignment
                                                                                           $('.dataTables_info').addClass('text-left fw-bolder');
                                                                                           $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                                           // Replace Feather icons after DataTable initializes
                                                                                           feather.replace();
                                                                                       }
                                                                                   });

                                                                                   // Replace Feather icons in case of dynamic changes
                                                                                   datatablesMulti.on('draw', function () {
                                                                                       feather.replace();
                                                                                   });
                                                                               });
        </script>

    </body>
</html>