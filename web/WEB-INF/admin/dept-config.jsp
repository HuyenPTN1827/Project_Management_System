<%-- 
    Document   : dept-config
    Created on : Nov 1, 2024, 3:23:54 AM
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

        <title>Department Configs | PMS</title>

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

            function openDeptUserModal(deptId, id = null) {
                let url = '<%=request.getContextPath()%>/add-department-user?deptId=' + deptId; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-department-user?deptId=' + deptId + '&id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#deptUserModal .modal-body').innerHTML = data;
                            document.getElementById('deptUserModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

            function closedeptUserModal() {
                document.getElementById('deptUserModal').style.display = 'none';
            }

            function redirectToConfigPage() {
                const selectedId = document.getElementById("deptDropdown").value;
                if (selectedId) {
                    // Redirect to the project type config page with the selected ID
                    window.location.href = 'department-config?id=' + selectedId;
                }
            }

            function filterFunction() {
                const input = document.getElementById("myInput");
                const filter = input.value.toUpperCase();
                const ul = document.getElementById("myDropdown");
                const a = ul.getElementsByTagName("a");
                for (let i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "department-management"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <a href="<%=request.getContextPath()%>/department-management">Department Management > </a>

                        <div class="mt-2 mb-3">
                            <h1 class="h1 d-inline align-middle">Department Details</h1>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'dept-detail' ? 'active' : ''}" id="department-detail-tab" data-bs-toggle="tab" 
                                                   href="#department-detail" role="tab" aria-controls="department-detail" aria-selected="${activeTab == 'dept-detail'}">
                                                    Department Details
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link ${activeTab == 'manager' ? 'active' : ''}" id="department-manager-tab" data-bs-toggle="tab" 
                                                   href="#department-manager" role="tab" aria-controls="department-manager" aria-selected="${activeTab == 'manager'}">
                                                    Department Manager
                                                </a>
                                            </li>

                                            <!-- Dropdown for selecting Department -->
                                            <li class="nav-item col-md-3 ms-auto">
                                                <select id="deptDropdown" class="form-select" onchange="redirectToConfigPage()">
                                                    <c:if test="${user.role_id == 2}">
                                                        <c:forEach items="${listDept}" var="d">
                                                            <option 
                                                                <c:if test="${dept.id eq d.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${d.id}">${d.name} (${d.code})
                                                            </option>
                                                        </c:forEach>
                                                    </c:if>

                                                    <c:if test="${user.role_id != 2}">
                                                        <c:forEach items="${listActiveDept}" var="d">
                                                            <option 
                                                                <c:if test="${dept.id eq d.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${d.id}">${d.name} (${d.code})
                                                            </option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="card-body">
                                        <div class="tab-content" id="myTabContent">
                                            <!-- Department Details -->
                                            <div class="tab-pane fade ${activeTab == 'dept-detail' ? 'show active' : ''}" id="department-detail" role="tabpanel" aria-labelledby="department-detail-tab">
                                                <div class="row">
                                                    <div class="col-md-12 col-xl-12">

                                                        <c:if test="${user.role_id == 2}">
                                                            <form action="update-department" method="post" class="row">
                                                                <input type="hidden" name="id" value="${dept.id}"/>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" name="name" placeholder="Enter the Department name" 
                                                                           value="${dept.name}" required>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" name="code" placeholder="Enter the Department code" 
                                                                           value="${dept.code}" required>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Parent</strong></label>
                                                                    <select name="parent" class="form-select">
                                                                        <option value="">Choose Department Parent</option>
                                                                        <c:forEach items="${parent}" var="d">
                                                                            <option 
                                                                                <c:if test="${dept.parentId eq d.id}">
                                                                                    selected="selected"
                                                                                </c:if>
                                                                                value=${d.id}>${d.name}
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Status</strong></label>
                                                                    <div class="check mt-1">
                                                                        <input class="form-check-input" type="radio" name="status"
                                                                               <c:if test="${dept.status eq 'true'}">
                                                                                   checked
                                                                               </c:if>
                                                                               value="true">&nbsp;Active
                                                                        <input class="form-check-input ms-4" type="radio" name="status"
                                                                               <c:if test="${dept.status eq 'false'}">
                                                                                   checked
                                                                               </c:if>
                                                                               value="false">&nbsp;Inactive
                                                                    </div>
                                                                </div>

                                                                <div class="mb-3 col-md-12">
                                                                    <label class="form-label"><strong>Details</strong></label>
                                                                    <textarea class="form-control" name="details" 
                                                                              placeholder="Enter the Department details" rows="3">${dept.details}</textarea>
                                                                </div>

                                                                <div>
                                                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                                </div>
                                                            </form>
                                                        </c:if>

                                                        <c:if test="${user.role_id != 2}">
                                                            <form action="update-department" method="post" class="row">
                                                                <input type="hidden" name="id" value="${dept.id}"/>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Name</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" name="name" placeholder="Enter the Department name" 
                                                                           value="${dept.name}" readonly>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Code</strong> <span style="color: red;">*</span></label>
                                                                    <input type="text" class="form-control" name="code" placeholder="Enter the Department code" 
                                                                           value="${dept.code}" readonly>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Parent</strong></label>
                                                                    <input type="text" class="form-control" placeholder="Choose Department Parent" 
                                                                           value="${dept.parentName}" readonly>
                                                                </div>

                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label"><strong>Status</strong></label>
                                                                    <c:if test="${dept.status eq 'true'}">
                                                                        <input type="text" class="form-control" placeholder="Department Status" 
                                                                               value="Active" readonly>
                                                                    </c:if>
                                                                    <c:if test="${dept.status eq 'false'}">
                                                                        <input type="text" class="form-control" placeholder="Department Status" 
                                                                               value="Inactive" readonly>
                                                                    </c:if>
                                                                </div>

                                                                <div class="mb-3 col-md-12">
                                                                    <label class="form-label"><strong>Details</strong></label>
                                                                    <textarea class="form-control" name="details" readonly
                                                                              placeholder="Enter the Department details" rows="3">${dept.details}</textarea>
                                                                </div>

                                                            </form>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Department Manager -->
                                            <div class="tab-pane fade ${activeTab == 'manager' ? 'show active' : ''}" id="department-manager" role="tabpanel" aria-labelledby="department-manager-tab">
                                                <div class="d-flex justify-content-between align-items-center" style="margin-bottom: 15px;">
                                                    <form action="department-config" method="post" class="d-flex align-items-center" style="gap: 15px;">
                                                        <input type="hidden" name="id" value="${dept.id}">

                                                        <div class="col-md-4">
                                                            <select name="status" class="form-select">
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

                                                        <div class="col-md-7">
                                                            <input type="search" name="keyword" class="form-control"
                                                                   placeholder="Enter the Full Name" id="keyword" value="${keyword}">
                                                        </div>

                                                        <div>
                                                            <button type="submit" class="btn btn-primary">Search</button>
                                                        </div>
                                                    </form>
                                                    <c:if test="${user.role_id == 2}">
                                                        <div class="btn-group">
                                                            <button type="button" class="btn btn-primary dropdown-toggle" 
                                                                    data-bs-toggle="dropdown" aria-expanded="false">
                                                                Add Manager &ensp;
                                                            </button>
                                                            <ul id="myDropdown" class="dropdown-menu dropdown-menu-end">
                                                                <li>
                                                                    <input class="dropdown-item form-control" type="text" 
                                                                           placeholder="Full name/Username/Email..." id="myInput" onkeyup="filterFunction()">
                                                                </li>
                                                                <c:forEach items="${listManager}" var="m">
                                                                    <li>
                                                                        <hr class="dropdown-divider">
                                                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/insert-department-user?deptId=${dept.id}&userId=${m.id}&roleId=3">
                                                                            ${m.full_name} (${m.username})
                                                                        </a>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:if>
                                                </div>

                                                <table id="datatables-multi" class="table table-striped" style="width:100%">
                                                    <thead>
                                                        <tr style="text-align: center">
                                                            <th>ID</th>
                                                            <th hidden>ID</th>
                                                            <th>Full Name</th>
                                                            <!--<th>Project Role</th>-->
                                                            <th>Start date</th>
                                                            <th>End Date</th>
                                                            <th>Status</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.deptUser}" var="du">
                                                            <tr style="text-align: center">
                                                                <td>${du.id}</td>
                                                                <td hidden>${du.user.id}</td>
                                                                <td>${du.user.full_name}</td>
                                                                <!--<td>${du.setting.name}</td>-->
                                                                <td>${du.start_date}</td>
                                                                <td>${du.end_date}</td>
                                                                <td>
                                                                    <c:if test="${du.status eq 'false'}">
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:if>
                                                                    <c:if test="${du.status eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <a href="javascript:void(0);" class="btn btn-info" 
                                                                       onclick="openDeptUserModal(${dept.id}, ${du.id});">
                                                                        <i class="align-middle" data-feather="eye"></i>
                                                                    </a>
                                                                    <c:if test="${user.role_id == 2}">
                                                                        <c:if test="${du.status eq 'false'}">
                                                                            <a href="<%=request.getContextPath()%>/change-status-department-user?id=${du.id}&status=${du.status}&deptId=${dept.id}"
                                                                               class="btn btn-success"
                                                                               onclick="return confirm('Are you sure you want to activate this Department manager?');">
                                                                                <i class="fas fa-check"></i>
                                                                            </a>
                                                                        </c:if>

                                                                        <c:if test="${du.status eq 'true'}">
                                                                            <a href="<%=request.getContextPath()%>/change-status-department-user?id=${du.id}&status=${du.status}&deptId=${dept.id}"
                                                                               class="btn btn-danger"
                                                                               onclick="return confirm('Are you sure you want to deactivate this Department manager?');">
                                                                                <i class="fas fa-times" style="padding-left: 2px; padding-right: 2px"></i>
                                                                            </a>
                                                                        </c:if>
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

                                <!-- Department Users Modal --> 
                                <div id="deptUserModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">User Details</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closedeptUserModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!--This is where the project-type-user-list.jsp will be loaded via AJAX--> 
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
                                                                {orderable: false, targets: 6} // Disable sorting on the 'Action' column
                                                            ],
                                                            language: {
                                                                paginate: {
                                                                    previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                    next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                },
                                                                info: "_TOTAL_ manager(s) found",
                                                                infoEmpty: "No manager found"
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

        <script>
            document.addEventListener("DOMContentLoaded", function (event) {
                // Check URL for 'success' parameter
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.get('update') === 'success') {
                    // Show success notification
                    window.notyf.open({
                        type: "success",
                        message: "Department details updated successfully.",
                        duration: 5000, // Adjust duration as needed
                        ripple: true,
                        dismissible: true,
                        position: {
                            x: "right",
                            y: "top"
                        }
                    });
                }
            });
        </script>
    </body>
</html>
