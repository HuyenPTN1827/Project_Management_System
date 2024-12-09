<%-- 
    Document   : project-config
    Created on : Dec 2, 2024, 11:59:01 PM
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
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <!--<link rel="canonical" href="tables-datatables-multi.html" />-->

        <title>Project Configs | PMS</title>

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

            document.querySelectorAll('.nav-link').forEach(tab => {
                tab.addEventListener('click', function () {
                    const url = new URL(window.location);
                    url.searchParams.set('activeTab', this.id.split('-tab')[0]); // Lấy id tab
                    history.replaceState(null, '', url);
                });
            });

            // Lắng nghe sự kiện thay đổi trong localStorage
            window.addEventListener('storage', (event) => {
                if (event.key === 'errorNotification' && event.newValue) {
                    const message = event.newValue;
                    showErrorNotification(message);
                    // Xóa thông báo sau khi hiển thị để tránh hiển thị lại
                    localStorage.removeItem('errorNotification');
                }
            });

            // Hàm dùng chung để hiển thị thông báo lỗi
            function showErrorNotification(message) {
                if (window.notyf) {
                    window.notyf.open({
                        type: "error",
                        message: message,
                        duration: 5000, // Thời gian hiển thị
                        ripple: true,
                        dismissible: true,
                        position: {
                            x: "right",
                            y: "top",
                        },
                    });
                } else {
                    console.error("Notyf library is not loaded.");
                }
            }
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <a href="<%=request.getContextPath()%>/projectlist">Project Management > </a>

                        <div class="mt-2 mb-3">
                            <h1 class="h1 d-inline align-middle">Project Configs</h1>
                            <input type="hidden" id="action" value="${action}"/>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'detail' ? 'active' : ''}" id="project-detail-tab" data-bs-toggle="tab" 
                                                   href="#project-detail" role="tab" aria-controls="project-detail" aria-selected="true">
                                                    Project Details
                                                </a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'milestone' ? 'active' : ''}" id="milestone-tab" data-bs-toggle="tab" 
                                                   href="#milestone" role="tab" aria-controls="milestone" aria-selected="false">
                                                    Milestones
                                                </a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'allocation' ? 'active' : ''}" id="allocation-tab" data-bs-toggle="tab" 
                                                   href="#allocation" role="tab" aria-controls="allocation" aria-selected="false">
                                                    Allocations
                                                </a>
                                            </li>

                                            <li class="nav-item" hidden>
                                                <a class="nav-link ${activeTab == 'team' ? 'active' : ''}" id="team-tab" data-bs-toggle="tab" 
                                                   href="#team" role="tab" aria-controls="team" aria-selected="false">
                                                    Teams
                                                </a>
                                            </li>

                                            <li class="nav-item col-md-3 ms-auto">
                                                <form action="${pageContext.request.contextPath}/projectconfig" method="get">
                                                    <input type="hidden" name="activeTab" value="${activeTab}">
                                                    <input type="hidden" name="action" value="${action}"/>
                                                    <select name="id" class="form-select" onchange="this.form.submit()">
                                                        <option value="" hidden disable>Select Project</option>
                                                        <c:forEach var="project" items="${projectList}">
                                                            <option value="${project.id}" ${project.id == param.id ? 'selected' : ''}>
                                                                ${project.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <!-- Project Details -->
                                            <div class="tab-pane fade ${activeTab == 'detail' ? 'show active' : ''}" id="project-detail" role="tabpanel" aria-labelledby="project-detail-tab">
                                                <h3 class="mb-3"> General Information</h3>
                                                <div class="row">
                                                    <div class="col-md-12 col-xl-12">
                                                        <c:if test="${not empty message}">
                                                            <div class="alert alert-success p-3" role="alert">
                                                                ${message}
                                                            </div>
                                                        </c:if>

                                                        <!-- Thông báo lỗi -->
                                                        <c:if test="${not empty error}">
                                                            <div class="alert alert-danger p-3" role="alert">
                                                                ${error}
                                                            </div>
                                                        </c:if>
                                                        ${errors}

                                                        <form action="updateproject" method="post" class="row">
                                                            <c:if test="${action == 'edit'}">
                                                                <!-- Hidden input to include project ID -->
                                                                <input type="hidden" name="projectId" value="${project.id}" />

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectName" class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" id="projectName" name="projectName" placeholder="Project Name" value="${project.name}">
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectCode" class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" id="projectCode" name="projectCode" placeholder="PrjCode" value="${project.code}">
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectType" class="form-label"><strong>Project Type</strong></label>
                                                                    <input type="text" class="form-control" id="projectType" name="projectType" placeholder="Project Type" value="${project.typeName}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="bizTerm" class="form-label"><strong>Biz Term</strong></label>
                                                                    <select class="form-select" id="bizTerm" name="bizTerm">
                                                                        <c:forEach var="term" items="${bizTerms}">
                                                                            <option value="${term.id}" ${term.name == project.bizTermName ? 'selected' : ''}>
                                                                                ${term.name}
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="estimatedEffort" class="form-label"><strong>Estimated Effort (man-days)</strong> <span style="color: red;">*</span></label>
                                                                    <input type="number" class="form-control" id="estimatedEffort" name="estimatedEffort" value="${project.estimatedEffort}">
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="department" class="form-label"><strong>Department</strong> <span style="color: red;">*</span></label>
                                                                    <select class="form-select" id="department" name="department">
                                                                        <!-- Mặc định là phòng ban của dự án hiện tại -->
                                                                        <option value="${project.departmentId}">
                                                                            ${project.departmentName} 
                                                                        </option>

                                                                        <!-- Hiển thị danh sách tất cả các phòng ban -->
                                                                        <c:forEach var="department" items="${departments}">
                                                                            <option value="${department.id}" 
                                                                                    ${department.id == project.departmentId ? 'selected' : ''}>
                                                                                ${department.name} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Start Date</strong> <span style="color: red;">*</span> <strong>- End Date</strong> </label>
                                                                    <div class="input-group">
                                                                        <input type="date" class="form-control" placeholder="dd/MM/yyyy"
                                                                               id="startDate" name="startDate" value="${project.startDate}" required>
                                                                        <span class="input-group-text">&nbsp;&nbsp;&nbsp;&nbsp; to &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                                        <input type="date" class="form-control" placeholder="dd/MM/yyyy"
                                                                               id="endDate" name="endDate" value="${project.endDate}">
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectManager" class="form-label"><strong>Project Manager</strong> <span style="color: red;">*</span></label>
                                                                    <select class="form-select" id="projectManager" name="projectManager">
                                                                        <option value="${project.userId}">Full Name (UserName)</option>
                                                                        <!-- Duyệt qua danh sách userList -->
                                                                        <c:forEach var="user" items="${listManagers}">
                                                                            <option value="${user.id}" ${user.id == project.userId ? 'selected' : ''}>
                                                                                ${user.full_name} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div> 

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="status" class="form-label"><strong>Status</strong></label>
                                                                    <select class="form-select" id="status" name="status">
                                                                        <option value="">Status Name</option>
                                                                        <option value="0" ${project.status == 0 ? 'selected' : ''}>Pending</option>
                                                                        <option value="1" ${project.status == 1 ? 'selected' : ''}>Doing</option>
                                                                        <option value="2" ${project.status == 2 ? 'selected' : ''}>Closed</option>
                                                                        <option value="3" ${project.status == 3 ? 'selected' : ''}>Cancelled</option>
                                                                    </select>
                                                                </div>      

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="lastUpdated" class="form-label"><strong>Last Updated At</strong></label>
                                                                    <input type="date" class="form-control" id="lastUpdated" value="${project.lastUpdated}" readonly>
                                                                </div>

                                                                <div class="col-md-12 mb-3">
                                                                    <label for="description" class="form-label"><strong>Description</strong></label>
                                                                    <textarea class="form-control" id="description" name="description" rows="3">${project.details}</textarea>
                                                                </div>

                                                                <div>
                                                                    <button type="submit" class="btn btn-lg btn-success" onclick="return validateAndSubmitForm(event, ${param.id});">Submit</button>
                                                                </div>
                                                            </c:if>

                                                            <c:if test="${action == 'view'}">
                                                                <!-- Hidden input to include project ID -->
                                                                <input type="hidden" name="projectId" value="${project.id}" />

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectName" class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" id="projectName" name="projectName" placeholder="Project Name" value="${project.name}" readonly>
                                                                </div>
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectCode" class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" id="projectCode" name="projectCode" placeholder="PrjCode" value="${project.code}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectType" class="form-label"><strong>Project Type</strong></label>
                                                                    <input type="text" class="form-control" id="projectType" name="projectType" placeholder="Project Type" value="${project.typeName}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="department" class="form-label"><strong>Department</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" id="department" name="department" placeholder="Department" value="${project.departmentName}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="estimatedEffort" class="form-label"><strong>Estimated Effort (man-days)</strong> <span style="color: red;">*</span></label>
                                                                    <input type="number" class="form-control" id="estimatedEffort" name="estimatedEffort" value="${project.estimatedEffort}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectManager" class="form-label"><strong>Project Manager</strong> <span style="color: red;">*</span></label>
                                                                    <input type="tex6" class="form-control" id="projectManager" name="projectManager" value="${user.full_name}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="startDate" class="form-label"><strong>Start Date</strong> <span style="color: red;">*</span></label>
                                                                    <input type="date" class="form-control" id="startDate" name="startDate" value="${project.startDate}" readonly>
                                                                </div>
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="endDate" class="form-label"><strong>End Date</strong></label>
                                                                    <input type="date" class="form-control" id="endDate" name="endDate" value="${project.endDate}" readonly>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="status" class="form-label"><strong>Status</strong></label>
                                                                    <c:if test="${project.status eq '0'}">
                                                                        <input type="text" class="form-control" placeholder="Project Status" 
                                                                               value="Pending" readonly>
                                                                    </c:if>
                                                                    <c:if test="${project.status eq '1'}">
                                                                        <input type="text" class="form-control" placeholder="Project Status" 
                                                                               value="Doing" readonly>
                                                                    </c:if>
                                                                    <c:if test="${project.status eq '2'}">
                                                                        <input type="text" class="form-control" placeholder="Project Status" 
                                                                               value="Closed" readonly>
                                                                    </c:if>
                                                                    <c:if test="${project.status eq '3'}">
                                                                        <input type="text" class="form-control" placeholder="Project Status" 
                                                                               value="Cancelled" readonly>
                                                                    </c:if>
                                                                </div>

                                                                <div class="col-md-6 mb-3">
                                                                    <label for="lastUpdated" class="form-label"><strong>Last Updated At</strong></label>
                                                                    <input type="date" class="form-control" id="lastUpdated" value="${project.lastUpdated}" readonly>
                                                                </div>

                                                                <div class="col-md-12 mb-3">
                                                                    <label for="description" class="form-label"><strong>Description</strong></label>
                                                                    <textarea class="form-control" id="description" name="description" rows="3" readonly>${project.details}</textarea>
                                                                </div>
                                                            </c:if>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <script>
                                                function validateAndSubmitForm(event, projectId) {
                                                    // Kiểm tra nếu projectId là null hoặc undefined
                                                    if (!projectId) {
                                                        const errorMessage = "Please select a project first!";

                                                        // Lưu thông báo lỗi vào localStorage để đồng bộ giữa các tab
                                                        localStorage.setItem('errorNotification', errorMessage);

                                                        // Hiển thị thông báo lỗi trên tab hiện tại
                                                        showErrorNotification(errorMessage);

                                                        // Ngăn form được submit
                                                        event.preventDefault();
                                                        return false;
                                                    }

                                                    // Nếu projectId hợp lệ, cho phép form tiếp tục được submit
                                                    return true;
                                                }
                                            </script>

                                            <!-- Milestone Tab -->
                                            <div class="tab-pane fade ${activeTab == 'milestone' ? 'show active' : ''}" id="milestone" role="tabpanel" aria-labelledby="milestone-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">

                                                    <!--                                                     Dropdown lọc trạng thái 
                                                                                                        <div class="col-md-4">
                                                                                                            <select class="form-select me-2" name="statusFilter" id="statusFilter" onchange="this.form.submit()">
                                                                                                                <option value="">All Status</option>
                                                                                                                <option value="0" ${param.statusFilter == '0' ? 'selected' : ''}>Pending</option>
                                                                                                                <option value="1" ${param.statusFilter == '1' ? 'selected' : ''}>Doing</option>
                                                                                                                <option value="2" ${param.statusFilter == '2' ? 'selected' : ''}>Closed</option>
                                                                                                                <option value="3" ${param.statusFilter == '3' ? 'selected' : ''}>Cancelled</option>
                                                                                                            </select>
                                                                                                        </div>-->

                                                    <h3 class="h3 d-inline align-middle">Milestones List</h3>
                                                    <c:if test="${action == 'edit'}">
                                                        <a class="btn btn-primary" href="javascript:void(0);" onclick="openMilestoneModal(${param.id});">Create new</a>
                                                    </c:if>
                                                </div>

                                                <table id="datatables-multi" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
                                                            <th>ID</th>
                                                            <th>Milestone</th>
                                                            <th>Parent Milestone</th>
                                                            <th>Priority</th>
                                                            <th>Target Date</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <!-- Hiển thị các milestone có parentMilestone = 0 trước -->
                                                        <c:forEach var="milestone" items="${milestoneList}">
                                                            <c:if test="${milestone.parentMilestone == 0}">
                                                                <tr style="text-align: center; font-weight: bold;">
                                                                    <td>${milestone.id}</td>
                                                                    <td>${milestone.name}</td>
                                                                    <td>${milestone.parentMilestoneName}</td>
                                                                    <td>${milestone.priority}</td>
                                                                    <td>${milestone.targetDate}</td>
                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test="${milestone.status == 0}"><span class="badge bg-secondary">Pending</span></c:when>
                                                                            <c:when test="${milestone.status == 1}"><span class="badge bg-primary">Doing</span></c:when>
                                                                            <c:when test="${milestone.status == 2}"><span class="badge bg-secondary-light">Closed</span></c:when>
                                                                            <c:when test="${milestone.status == 3}"><span class="badge bg-danger">Cancelled</span></c:when>
                                                                            <c:otherwise>Unknown</c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                    <td>
                                                                        <c:if test="${action == 'edit'}">
                                                                            <a href="javascript:void(0);" class="btn btn-info" 
                                                                               onclick="openMilestoneModal(${param.id}, ${milestone.id});">
                                                                                <i class="align-middle" data-feather="edit"></i>
                                                                            </a>
                                                                        </c:if>
                                                                        <c:if test="${action == 'view'}">
                                                                            <a href="javascript:void(0);" class="btn btn-info" 
                                                                               onclick="openMilestoneModal(${param.id}, ${milestone.id});">
                                                                                <i class="align-middle" data-feather="eye"></i>
                                                                            </a>
                                                                        </c:if>
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>

                                                        <!-- Hiển thị các milestone còn lại -->
                                                        <c:forEach var="milestone" items="${milestoneList}">
                                                            <c:if test="${milestone.parentMilestone != 0}">
                                                                <tr style="text-align: center">
                                                                    <td>${milestone.id}</td>
                                                                    <td hidden>${milestone.projectName}</td>
                                                                    <td>${milestone.name}</td>
                                                                    <td>${milestone.parentMilestoneName}</td>
                                                                    <td>${milestone.priority}</td>
                                                                    <td>${milestone.targetDate}</td>
                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test="${milestone.status == 0}"><span class="badge bg-secondary">Pending</span></c:when>
                                                                            <c:when test="${milestone.status == 1}"><span class="badge bg-primary">Doing</span></c:when>
                                                                            <c:when test="${milestone.status == 2}"><span class="badge bg-secondary-light">Closed</span></c:when>
                                                                            <c:when test="${milestone.status == 3}"><span class="badge bg-danger">Cancelled</span></c:when>
                                                                            <c:otherwise>Unknown</c:otherwise>
                                                                        </c:choose>
                                                                    </td>
                                                                    <td>
                                                                        <c:if test="${action == 'edit'}">
                                                                            <a href="javascript:void(0);" class="btn btn-info" 
                                                                               onclick="openMilestoneModal(${param.id}, ${milestone.id});">
                                                                                <i class="align-middle" data-feather="edit"></i>
                                                                            </a>
                                                                        </c:if>
                                                                        <c:if test="${action == 'view'}">
                                                                            <a href="javascript:void(0);" class="btn btn-info" 
                                                                               onclick="openMilestoneModal(${param.id}, ${milestone.id});">
                                                                                <i class="align-middle" data-feather="eye"></i>
                                                                            </a>
                                                                        </c:if>
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!-- Milestone Modal --> 
                                            <div id="milestoneModal" class="modal" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title">Milestone Details</h1>
                                                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeMilestoneModal();"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <!--This is where the milestone-detail.jsp will be loaded via AJAX--> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

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
                                                            info: "_TOTAL_ milestone(s) found",
                                                            infoEmpty: "No milestone found"
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

                                                function openMilestoneModal(projectId, id = null) {
                                                    const action = document.getElementById("action").value;
                                                    if (!projectId) {
                                                        const errorMessage = "Please select a Project first!";
                                                        localStorage.setItem('errorNotification', errorMessage); // Lưu thông báo lỗi
                                                        showErrorNotification(errorMessage); // Hiển thị lỗi tại tab hiện tại
                                                        return; // Thoát hàm nếu projectId không hợp lệ
                                                    }

                                                    let url = '<%=request.getContextPath()%>/add-milestone?projectId=' + projectId + '&action=' + action; // Default cho Create New
                                                    if (id) {
                                                        url = '<%=request.getContextPath()%>/edit-milestone?projectId=' + projectId + '&id=' + id + '&action=' + action; // Cho Edit
                                                    }

                                                    fetch(url)
                                                            .then(response => response.text())
                                                            .then(data => {
                                                                document.querySelector('#milestoneModal .modal-body').innerHTML = data;
                                                                document.getElementById('milestoneModal').style.display = 'block';
                                                            })
                                                            .catch(error => console.log('Error loading the form:', error));
                                                }

                                                function closeMilestoneModal() {
                                                    document.getElementById('milestoneModal').style.display = 'none';
                                                }
                                            </script>

                                            <!--Allocations Tab-->
                                            <div class="tab-pane fade ${activeTab == 'allocation' ? 'show active' : ''}" id="allocation" role="tabpanel" aria-labelledby="allocation-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="projectconfig" method="post" class="d-flex align-items-center" style="gap: 10px;">
                                                        <input type="hidden" name="id" value="${param.id}" />
                                                        <input type="hidden" name="activeTab" value="allocation">
                                                        <input type="hidden" name="action" value="${action}">

                                                        <div class="col-md-2">
                                                            <select name="deptId" class="form-select">
                                                                <option value="">All Departments</option>
                                                                <c:forEach items="${listDept}" var="d">
                                                                    <option 
                                                                        <c:if test="${deptId eq d.id}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                        value="${d.id}">${d.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-2">
                                                            <select name="roleId" class="form-select">
                                                                <option value="">All Roles</option>
                                                                <option  
                                                                    <c:if test="${roleId eq 1}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="1">Project Manager
                                                                </option>
                                                                <c:forEach items="${listRole}" var="s">
                                                                    <option 
                                                                        <c:if test="${roleId eq s.id}">
                                                                            selected="selected"
                                                                        </c:if>
                                                                        value="${s.id}">${s.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="col-md-2">
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
                                                                   placeholder="Full Name/Username/Email" id="keywordUser" value="${keywordUser}">
                                                        </div>
                                                        <div class="col-md-2">
                                                            <button type="submit" class="btn btn-primary">Search</button>
                                                        </div>

                                                    </form>

                                                    <c:if test="${action == 'edit'}">
                                                        <div class="col-md-2 d-flex justify-content-end align-items-end">
                                                            <a class="btn btn-primary" href="javascript:void(0);" onclick="openAllocationModal(${param.id});">Create new</a>
                                                        </div>
                                                    </c:if>
                                                </div>

                                                <table id="datatables-multi2" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
                                                            <th>ID</th>
                                                            <th>Member</th>
                                                            <th>Department</th>
                                                            <th>From Date</th>
                                                            <th>To Date</th>
                                                            <th>Role</th>
                                                            <th>Effort Rate</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.allocation}" var="al">
                                                            <tr style="text-align: center">
                                                                <td>${al.id}</td>
                                                                <td>${al.user.full_name} (${al.user.username})</td>
                                                                <td>${al.dept.code}</td>
                                                                <td>${al.startDate}</td>
                                                                <td>${al.endDate}</td>
                                                                <td>${al.role.name}</td>
                                                                <td>${al.effortRate}</td>
                                                                <td>
                                                                    <c:if test="${al.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                    <c:if test="${al.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <c:if test="${action == 'edit'}">
                                                                        <a href="javascript:void(0);" class="btn btn-info" 
                                                                           onclick="openAllocationModal(${param.id}, ${al.id});">
                                                                            <i class="align-middle" data-feather="edit"></i>
                                                                        </a>

                                                                        <c:if test="${al.status eq 'false'}">
                                                                            <a href="<%=request.getContextPath()%>/change-status-allocation?id=${al.id}&status=${al.status}&projectId=${param.id}&userId=${user.id}"
                                                                               class="btn btn-success"
                                                                               onclick="return confirm('Are you sure you want to activate this allocation?');">
                                                                                <i class="fas fa-check"></i>
                                                                            </a>
                                                                        </c:if>

                                                                        <c:if test="${al.status eq 'true'}">
                                                                            <a href="<%=request.getContextPath()%>/change-status-allocation?id=${al.id}&status=${al.status}&projectId=${param.id}&userId=${user.id}"
                                                                               class="btn btn-danger"
                                                                               onclick="return confirm('Are you sure you want to deactivate this allocation?');">
                                                                                <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                            </a>
                                                                        </c:if>
                                                                    </c:if>

                                                                    <c:if test="${action == 'view'}">
                                                                        <a href="javascript:void(0);" class="btn btn-info" 
                                                                           onclick="openAllocationModal(${param.id}, ${al.id});">
                                                                            <i class="align-middle" data-feather="eye"></i>
                                                                        </a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <!--Allocation Modal--> 
                                            <div id="allocationModal" class="modal" tabindex="-1" role="dialog">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title">
                                                                Allocation Details
                                                            </h1>
                                                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeAllocationModal();"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <!--This is where the allocation-detail.jsp will be loaded via AJAX--> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                    var datatablesMulti = $("#datatables-multi2").DataTable({
                                                        responsive: true,
                                                        paging: true,
                                                        searching: false,
                                                        info: true,
                                                        order: [[0, 'desc']], // Default sort by ID column in descending order
                                                        columnDefs: [
                                                            {orderable: false, targets: 8} // Disable sorting on the 'Action' column
                                                        ],
                                                        language: {
                                                            paginate: {
                                                                previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                            },
                                                            info: "_TOTAL_ member(s) found",
                                                            infoEmpty: "No member found"
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

                                                function openAllocationModal(projectId, id = null) {
                                                    const action = document.getElementById("action").value;
                                                    if (!projectId) {
                                                        const errorMessage = "Please select a Project first!";
                                                        localStorage.setItem('errorNotification', errorMessage); // Lưu thông báo lỗi
                                                        showErrorNotification(errorMessage); // Hiển thị lỗi tại tab hiện tại
                                                        return; // Thoát hàm nếu projectId không hợp lệ
                                                    }

                                                    let url = '<%=request.getContextPath()%>/add-allocation?projectId=' + projectId; // Default cho Create New
                                                    if (id) {
                                                        url = '<%=request.getContextPath()%>/edit-allocation?projectId=' + projectId + '&id=' + id + '&action=' + action; // Cho Edit
                                                    }

                                                    fetch(url)
                                                            .then(response => response.text())
                                                            .then(data => {
                                                                document.querySelector('#allocationModal .modal-body').innerHTML = data;
                                                                document.getElementById('allocationModal').style.display = 'block';
                                                            })
                                                            .catch(error => console.log('Error loading the form:', error));
                                                }


                                                function closeAllocationModal() {
                                                    document.getElementById('allocationModal').style.display = 'none';
                                                }

                                                function redirectToDetailPage(projectId) {
                                                    const deptId = document.getElementById("deptDropdown").value;
                                                    const roleId = document.getElementById("roleId").value;
                                                    const startDate = document.getElementById("fromDate").value;
                                                    const endDate = document.getElementById("toDate").value;
                                                    const effort = document.getElementById("effort").value;
                                                    const description = document.getElementById("descriptionAllocation").value;

                                                    let url = 'add-allocation?projectId=' + projectId;
                                                    if (deptId) {
                                                        url += '&deptId=' + deptId;
                                                    }
                                                    if (roleId) {
                                                        url += '&roleId=' + roleId;
                                                    }
                                                    if (startDate) {
                                                        url += '&fromDate=' + startDate;
                                                    }
                                                    if (endDate) {
                                                        url += '&toDate=' + endDate;
                                                    }
                                                    if (effort) {
                                                        url += '&effort=' + effort;
                                                    }
                                                    if (description) {
                                                        url += '&descriptionAllocation=' + description;
                                                    }

                                                    fetch(url)
                                                            .then(response => response.text())
                                                            .then(data => {
                                                                document.querySelector('#allocationModal .modal-body').innerHTML = data;
                                                                document.getElementById('allocationModal').style.display = 'block';
                                                            })
                                                            .catch(error => console.log('Error loading the form:', error));
                                                }

                                                function validateForm(event) {
                                                    event.preventDefault(); // Ngăn không gửi form ngay lập tức
                                                    const errorContainer = document.getElementById("errorContainer");
                                                    const errorList = document.getElementById("errorList");
                                                    errorList.innerHTML = ""; // Xóa thông báo lỗi cũ
                                                    errorContainer.classList.add("d-none");

                                                    const effort = document.getElementById("effort").value;
                                                    const fromDate = document.getElementById("fromDate").value;
                                                    const toDate = document.getElementById("toDate").value;
                                                    const today = new Date().toISOString().split("T")[0];
                                                    let hasError = false;

                                                    //Kiểm tra Effort Rate
                                                    if (effort < 0 || effort > 100) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>Effort rate must be between 0% and 100%.</li>";
                                                    }

                                                    // Kiểm tra fromDate
                                                    if (!fromDate) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>From Date is required.</li>";
                                                    } else if (fromDate < today) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>From Date cannot be earlier than today.</li>";
                                                    }

                                                    // Kiểm tra toDate
                                                    if (toDate && toDate < fromDate) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>To Date cannot be earlier than From Date.</li>";
                                                    }

                                                    // Hiển thị lỗi nếu có
                                                    if (hasError) {
                                                        errorContainer.classList.remove("d-none");
                                                        return false; // Ngăn gửi form
                                                    }

                                                    // Không có lỗi, gửi form
                                                    document.getElementById("allocationForm").submit();
                                                }

                                                function validateFormEdit(event) {
                                                    event.preventDefault(); // Ngăn không gửi form ngay lập tức
                                                    const errorContainer = document.getElementById("errorContainer");
                                                    const errorList = document.getElementById("errorList");
                                                    errorList.innerHTML = ""; // Xóa thông báo lỗi cũ
                                                    errorContainer.classList.add("d-none");

                                                    const effort = document.getElementById("effort").value;
                                                    const fromDate = document.getElementById("fromDate").value;
                                                    const toDate = document.getElementById("toDate").value;
                                                    const today = new Date().toISOString().split("T")[0];
                                                    let hasError = false;

                                                    //Kiểm tra Effort Rate
                                                    if (effort < 0 || effort > 100) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>Effort Rate must be between 0% and 100%.</li>";
                                                    }

                                                    // Kiểm tra fromDate
                                                    if (!fromDate) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>From Date is required.</li>";
                                                    }

                                                    // Kiểm tra toDate
                                                    if (toDate && toDate < fromDate) {
                                                        hasError = true;
                                                        errorList.innerHTML += "<li>To Date cannot be earlier than From Date.</li>";
                                                    }

                                                    // Hiển thị lỗi nếu có
                                                    if (hasError) {
                                                        errorContainer.classList.remove("d-none");
                                                        return false; // Ngăn gửi form
                                                    }

                                                    // Không có lỗi, gửi form
                                                    document.getElementById("allocationForm").submit();
                                                }
                                            </script>

                                            <!--Team Tab-->
                                            <div hidden class="tab-pane fade ${activeTab == 'team' ? 'show active' : ''}" id="team" role="tabpanel" aria-labelledby="team-tab">
                                                <div class="row">
                                                    <div class="col-md-12 col-xl-12">
                                                        <div class="card">
                                                            <div class="card-header">
                                                                <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                                    <form action="projectconfig" method="get" class="d-flex align-items-center" style="gap: 15px;">
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
                                                                               placeholder="Enter Team Name or Code" id="keyword" value="${param.keyword}">

                                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                                    </form>

                                                                    <a class="btn btn-primary" href="./TeamController?action=add&cuId=${param.id}">Create new</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <table id="datatables-multi" class="table table-striped" style="width:100%">
                                                                    <thead>
                                                                        <tr style="text-align: center">
                                                                            <th>ID</th>
                                                                            <th>Name</th>
                                                                            <th>Topic</th>
                                                                            <th>Project</th>
                                                                            <th>Status</th>
                                                                            <th>Action</th>

                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${teams}" var="tt">
                                                                            <tr style="text-align: center">
                                                                                <td>${tt.id}</td>
                                                                                <td>${tt.name}</td>
                                                                                <td>${tt.topic}</td>
                                                                                <td>${tt.project.name}</td>
                                                                                <td>
                                                                                    <c:if test="${tt.status eq '1'}">
                                                                                        <span class="badge bg-success">Active</span>
                                                                                    </c:if>
                                                                                    <c:if test="${tt.status eq '0'}">
                                                                                        <span class="badge bg-danger">Inactive</span>
                                                                                    </c:if>
                                                                                </td>
                                                                                <td>
                                                                                    <a href="./TeamController?action=edit&id=${tt.id}&cuId=${param.id}" 
                                                                                       class="btn btn-link text-primary">Edit</a>

                                                                                    <c:if test="${tt.status eq '0'}">
                                                                                        <a href="./TeamController?action=changeStatus&id=${tt.id}&status=true&cuId=${param.id}"
                                                                                           class="btn btn-link text-success"
                                                                                           onclick="return confirm('Are you sure you want to activate this project type?');">Activate</a>
                                                                                    </c:if>

                                                                                    <c:if test="${tt.status eq '1'}">
                                                                                        <a href="./TeamController?action=changeStatus&id=${type.id}&status=false&cuId=${param.id}"
                                                                                           class="btn btn-link text-danger"
                                                                                           onclick="return confirm('Are you sure you want to deactivate this project type?');">Deactivate</a>
                                                                                    </c:if>

                                                                                    <a href="./TeamController?action=delete&id=${tt.id}&cuId=${param.id}"
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
                                                                                           window.notyf = new Notyf();
        </script>
    </body>
</html>